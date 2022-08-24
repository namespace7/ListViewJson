package com.namespace.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ItemsModel extends ArrayAdapter<String> {
    private final Context context;
    private final String str_id;
    private final String[] str_Fname;
    private final String[] str_Lname;
    private final String[] str_Email;

    public ItemsModel(@NonNull Context context, int resource, Context context1, String str_id, String[] str_Fname, String[] str_Lname, String[] str_Email) {
        super(context, resource);
        this.context = context1;
        this.str_id = str_id;
        this.str_Fname = str_Fname;
        this.str_Lname = str_Lname;
        this.str_Email = str_Email;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_items, parent, false);
        TextView textView_Id = (TextView) rowView.findViewById(R.id.textView_id);
        /*
        TextView textView_Fname = (TextView) rowView.findViewById(R.id.textView_firstname);
        TextView textView_Lname = (TextView) rowView.findViewById(R.id.textView_lastname);
        TextView textView_email = (TextView) rowView.findViewById(R.id.textView_email);
        textView_Id.setText(str_id[position]);
        textView_Fname.setText(str_Fname[position]);
        textView_Lname.setText(str_Lname[position]);
        textView_email.setText(str_Email[position]);
*/
        return super.getView(position, convertView, parent);

    }
}
