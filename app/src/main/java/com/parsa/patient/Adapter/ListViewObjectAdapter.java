package com.parsa.patient.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by ashkan on 11/15/2014.
 */
public class ListViewObjectAdapter<T> extends ArrayAdapter<T> {




    private Context context;
    private ArrayList<T> items;

    public ListViewObjectAdapter(Context context, ArrayList<T> items) {
        super(context, 0);
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (items.get(position) instanceof IListViewItem) {
            IListViewItem item = (IListViewItem) items.get(position);
            return item.getView(context, view);
        }
        return null;
    }

    public interface IListViewItem {

        View getView(Context context, View oldView);

    }

}