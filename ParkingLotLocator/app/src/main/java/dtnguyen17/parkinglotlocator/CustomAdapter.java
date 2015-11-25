package dtnguyen17.parkinglotlocator;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomAdapter extends BaseAdapter {

    private List<Response.CarparksEntity> mCarparkItem = null;
    private ArrayList<Response.CarparksEntity> arraylist;
    private Context mContext;
    private LayoutInflater inflater;

    public CustomAdapter(Context mContext, List<Response.CarparksEntity> mCarparkItem) {
        inflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mCarparkItem = mCarparkItem;
        this.arraylist = new ArrayList<Response.CarparksEntity>();
        this.arraylist.addAll(mCarparkItem);
    }

    @Override
    public int getCount() {
        return mCarparkItem.size();
    }

    @Override
    public Response.CarparksEntity getItem(int position) {
        return mCarparkItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        view = inflater.inflate(R.layout.each_list_item, null);
        TextView address = (TextView) view.findViewById(R.id.address);
        TextView rate = (TextView) view.findViewById(R.id.rate);
        TextView type = (TextView) view.findViewById(R.id.type);
        TextView capacity = (TextView) view.findViewById(R.id.capacity);
        TextView options = (TextView) view.findViewById(R.id.options);

        address.setText("\t" + mCarparkItem.get(position).getAddress());
        rate.setText("\t" + mCarparkItem.get(position).getRate());
        type.setText("\t" + mCarparkItem.get(position).getCarpark_type_str());
        capacity.setText("\t" + mCarparkItem.get(position).getCapacity());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < mCarparkItem.get(position).getPayment_methods().size(); i++)
        {
            sb.append("\t" + mCarparkItem.get(position).getPayment_methods().get(i) + "\n");
        }
        options.setText(sb);

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleItemView.class);
                intent.putExtra("lng", (mCarparkItem.get(position).getLng()));
                intent.putExtra("lat", (mCarparkItem.get(position).getLat()));
                intent.putExtra("address", (mCarparkItem.get(position).getAddress()));
                intent.putExtra("rate", (mCarparkItem.get(position).getRate()));
                intent.putExtra("carparktype", (mCarparkItem.get(position).getCarpark_type_str()));
                intent.putExtra("capacity", (mCarparkItem.get(position).getCapacity()));
                intent.putStringArrayListExtra("options", (ArrayList<String>) mCarparkItem.get(position).getPayment_methods());
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mCarparkItem.clear();
        if (charText.length() == 0) {
            mCarparkItem.addAll(arraylist);
        }
        else
        {
            for (Response.CarparksEntity wp : arraylist)
            {
                if (wp.getAddress().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    mCarparkItem.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
