package com.padhuga.tamil.games.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.padhuga.tamil.games.R;
import com.padhuga.tamil.games.listeners.ItemBaseDragListener;
import com.padhuga.tamil.games.models.Item;
import com.padhuga.tamil.games.models.ItemBaseViewHolder;

import java.util.List;

public class ItemBaseAdapter extends BaseAdapter {

    private final Context context;
    private final List<Item> list;
    private final String layoutType;

    public ItemBaseAdapter(Context context, List<Item> list, String layoutType){
        this.context = context;
        this.list = list;
        this.layoutType = layoutType;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Item> getList(){
        return list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        final ViewGroup nullParent = null;

        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.row, nullParent);

            ItemBaseViewHolder viewHolder = new ItemBaseViewHolder();
            viewHolder.text = rowView.findViewById(R.id.rowTextView);
            rowView.setTag(viewHolder);
        }

        ItemBaseViewHolder holder = (ItemBaseViewHolder) rowView.getTag();
        switch (layoutType) {
            case "Icon Text":
                holder.text.setCompoundDrawablesWithIntrinsicBounds(list.get(position).ItemDrawable, null, null, null);
                holder.text.setText(list.get(position).ItemString);
                break;
            case "Icon":
                holder.text.setCompoundDrawablesWithIntrinsicBounds(list.get(position).ItemDrawable, null, null, null);
                break;
            case "Text":
                holder.text.setText(list.get(position).ItemString);
                break;
            default:
                break;
        }

        rowView.setOnDragListener(new ItemBaseDragListener(list.get(position), context));

        return rowView;
    }

}
