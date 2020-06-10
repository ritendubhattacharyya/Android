package com.example.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class customAdapter extends ArrayAdapter {
    List data = new ArrayList();
    static class dataHandler {
        ImageView images;
        TextView names;
    }
    public customAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        data.add(object);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        dataHandler dataHandler;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_list, parent, false);
            dataHandler = new dataHandler();
            dataHandler.images = (ImageView) view.findViewById(R.id.image);
            dataHandler.names = (TextView) view.findViewById(R.id.name);
            view.setTag(dataHandler);
        } else {
            dataHandler = (dataHandler)view.getTag();
        }
        dataProvider dataProvider;
        dataProvider = (dataProvider) this.getItem(position);
        dataHandler.images.setImageResource(dataProvider.getImages());
        dataHandler.names.setText(dataProvider.getNames());

        return view;
    }
}
