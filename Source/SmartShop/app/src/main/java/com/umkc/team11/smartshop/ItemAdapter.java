package com.umkc.team11.smartshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CJ on 3/4/2018.
 */

public class ItemAdapter extends ArrayAdapter
{
    //ImageView iv = new ImageView(conext);
    //iv.setImageResource(R.drawable.**smiley.666**);

    // holds context
    private final Context context;

    // holds list of values
    private final ArrayList<ItemData> values;

    // Constructor
    public ItemAdapter(Context context, ArrayList<ItemData> values)
    {
        super(context, R.layout.item_layout, values);
        this.context = context;
        this.values = values;
    } // end constructor

    // Sets items to correct view element
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // inflates layout
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // gets the layout
        View rowView = inflater.inflate(R.layout.item_layout, parent, false);

        // finds the correct element and assigns data
        /*TextView nameView = rowView.findViewById(R.id.item_name);
        nameView.setText(values.get(position).getName());

        // finds the correct element and assigns data
        TextView quantityView = rowView.findViewById(R.id.item_quantity);
        quantityView.setText(String.valueOf(values.get(position).getQuantity()));*/

        // returns completed view
        return rowView;
    } // end getView
} // end class ItemAdapter
