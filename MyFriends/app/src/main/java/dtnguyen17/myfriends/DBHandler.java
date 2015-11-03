package dtnguyen17.myfriends;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "MyFriends.db";
    private static String DB_PATH = "";
    private final Context myContext;
    private boolean createDatabase = false;

    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        myContext = context;
        DB_PATH = myContext.getDatabasePath(DATABASE_NAME).getAbsolutePath();
    }


    public void initializeDataBase()
    {
        getWritableDatabase();

        if (createDatabase) {
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private void copyDataBase() throws IOException
    {
        close();
        InputStream fromFile = myContext.getAssets().open(DATABASE_NAME);
        OutputStream toFile = new FileOutputStream(DB_PATH);

        byte[] buffer = new byte[1024];
        int length;

        try {
            while ((length = fromFile.read(buffer)) > 0) {
                toFile.write(buffer, 0, length);
            }
        }
        finally {
            try {
                if (toFile != null) {
                    try {
                        toFile.flush();
                    } finally {
                        toFile.close();
                    }
                }
            } finally {
                if (fromFile != null) {
                    fromFile.close();
                }
            }
        }
        getWritableDatabase().close();
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        createDatabase = true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS myfriends");
        onCreate(db);
    }


    // Getting All Contacts
    public List<Friends> getAllFriendsNames()
    {
        List<Friends> friendList = new ArrayList<>();
        String query = "SELECT * FROM 'myfriends'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Friends friends = new Friends();
                friends.setID(Integer.parseInt(cursor.getString(0)));
                friends.setName(cursor.getString(1));
                friends.setPhone(cursor.getString(2));
                friends.setPhone(cursor.getString(2));
                friends.setEmail(cursor.getString(3));

                String name = cursor.getString(1);
                Friend_list.arrayOfNames.add(name);
                friendList.add(friends);

            } while (cursor.moveToNext());
        }

        return friendList;
    }

}
