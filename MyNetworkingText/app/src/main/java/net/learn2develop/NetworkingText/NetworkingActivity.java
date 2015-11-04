package net.learn2develop.NetworkingText;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.util.Xml;
import android.widget.TextView;
import android.os.AsyncTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.learn2develop.Networking.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class NetworkingActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // ---access a Web Service using GET---
        new AccessWebServiceTask().execute("apple");
    }

    private InputStream OpenHttpConnection(String urlString)
            throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }
        return in;
    }

    public class XmlParser
    {
        public class Definition {
            public final String WordDefinition;

            private Definition(String WordDefinition) {
                this.WordDefinition = WordDefinition;
            }
        }

        private final String ns = null;

        public List parse(InputStream in) throws XmlPullParserException, IOException {
            try {
                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                parser.nextTag();
                return readFeed(parser);
            } finally {
                in.close();
            }
        }

        private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
            List entries = new ArrayList();

            parser.require(XmlPullParser.START_TAG, ns, "Definitions");
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                // Starts by looking for the entry tag
                if (name.equals("Definition")) {
                    entries.add(readEntry(parser));
                } else {
                    skip(parser);
                }
            }
            return entries;
        }


        // Parses the contents of an entry. If it encounters a WordDefinition, summary, or link tag, hands them off
        // to their respective "read" methods for processing. Otherwise, skips the tag.
        private Definition readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
            parser.require(XmlPullParser.START_TAG, ns, "Definition");
            String WordDefinition = null;

            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                if (name.equals("WordDefinition")) {
                    WordDefinition = readWordDefinition(parser);
                } else {
                    skip(parser);
                }
            }
            return new Definition(WordDefinition);
        }

        // Processes WordDefinition tags in the feed.
        private String readWordDefinition(XmlPullParser parser) throws IOException, XmlPullParserException {
            parser.require(XmlPullParser.START_TAG, ns, "WordDefinition");
            String WordDefinition = readText(parser);
            parser.require(XmlPullParser.END_TAG, ns, "WordDefinition");
            return WordDefinition;
        }

        // For the tags WordDefinition extracts their text values.
        private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
            String result = "";
            if (parser.next() == XmlPullParser.TEXT) {
                result = parser.getText();
                parser.nextTag();
            }
            return result;
        }

        private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                throw new IllegalStateException();
            }
            int depth = 1;
            while (depth != 0) {
                switch (parser.next()) {
                    case XmlPullParser.END_TAG:
                        depth--;
                        break;
                    case XmlPullParser.START_TAG:
                        depth++;
                        break;
                }
            }
        }

    }



    private String WordDefinition(String word) {
        InputStream in = null;
        XmlParser xmlParser = new XmlParser();
        String strDefinition = "";
        try {
            in = OpenHttpConnection("http://services.aonaware.com/DictService/DictService.asmx/Define?word="
                    + word);

            xmlParser.parse(in);


            /*
            Document doc = null;
            DocumentBuilderFactory dbf = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder db;
            try {
                db = dbf.newDocumentBuilder();
                doc = db.parse(in);
            } catch (ParserConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            doc.getDocumentElement().normalize();

            // ---retrieve all the <Definition> elements---
            NodeList definitionElements = doc
                    .getElementsByTagName("Definition");

            // ---iterate through each <Definition> elements---
            for (int i = 0; i < definitionElements.getLength(); i++) {
                Node itemNode = definitionElements.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    // ---convert the Definition node into
                    // an Element---
                    Element definitionElement = (Element) itemNode;

                    // ---get all the <WordDefinition>
                    // elements under
                    // the <Definition> element---
                    NodeList wordDefinitionElements = (definitionElement)
                            .getElementsByTagName("WordDefinition");

                    strDefinition = "";
                    // ---iterate through each
                    // <WordDefinition> elements---
                    for (int j = 0; j < wordDefinitionElements
                            .getLength(); j++) {
                        // ---convert a <WordDefinition>
                        // node into an Element---
                        Element wordDefinitionElement = (Element) wordDefinitionElements
                                .item(j);

                        // ---get all the child nodes
                        // under the
                        // <WordDefinition> element---
                        NodeList textNodes = ((Node) wordDefinitionElement)
                                .getChildNodes();

                        strDefinition += ((Node) textNodes
                                .item(0))
                                .getNodeValue()
                                + ". \n";
                    }

                }
            }
            */
        } catch (IOException e1) {
            Log.d("NetworkingActivity", e1.getLocalizedMessage());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        // ---return the definitions of the word---
        return strDefinition;
    }


    private class AccessWebServiceTask extends
            AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return WordDefinition(urls[0]);
        }

        protected void onPostExecute(String result) {
            TextView tv = (TextView) findViewById(R.id.textView2);
            tv.setText(result);
        }
    }

}