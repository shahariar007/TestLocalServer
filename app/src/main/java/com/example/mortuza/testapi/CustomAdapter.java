package com.example.mortuza.testapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Mortuza on 11/9/2015.
 */
public class CustomAdapter extends BaseAdapter {
    ArrayList<UserModel> ar = new ArrayList<UserModel>();
    Context context;

    public CustomAdapter(ArrayList<UserModel> ar, Context context) {
        this.ar = ar;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ar.size();
    }

    @Override
    public Object getItem(int position) {
        return ar.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.customlayout, parent, false);
        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView phone = (TextView) convertView.findViewById(R.id.phone);
        TextView email = (TextView) convertView.findViewById(R.id.email);
        UserModel u = (UserModel) getItem(position);
        id.setText(u.getId().toString());
        name.setText(u.getName().toString());
        phone.setText(u.getPhone().toString());
        email.setText(u.getEmail().toString());
        return convertView;
    }
}
