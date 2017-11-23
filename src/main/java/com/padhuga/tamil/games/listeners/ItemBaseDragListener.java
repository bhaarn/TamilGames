package com.padhuga.tamil.games.listeners;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.DragEvent;
import android.view.View;
import android.widget.AbsListView;

import com.padhuga.tamil.games.adapters.ItemBaseAdapter;
import com.padhuga.tamil.games.models.Item;
import com.padhuga.tamil.games.models.PassObject;
import com.padhuga.tamil.games.utilities.AddOrRemove;

import java.util.List;

public class ItemBaseDragListener implements View.OnDragListener {

    private final Item me;
    private final Context context;

    public ItemBaseDragListener(Item i, Context context){
        me = i;
        this.context = context;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        //Used to resume original color in drop ended/exited
        int resumeColor = ContextCompat.getColor(context, android.R.color.background_light);
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackgroundColor(0x30000000);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackgroundColor(resumeColor);
                break;
            case DragEvent.ACTION_DROP:
                PassObject passObj = (PassObject)event.getLocalState();
                View view = passObj.view;
                Item passedItem = passObj.item;
                List<Item> srcList = passObj.srcList;
                AbsListView oldParent = (AbsListView)view.getParent();
                ItemBaseAdapter srcAdapter = (ItemBaseAdapter)(oldParent.getAdapter());

                AbsListView newParent = (AbsListView)v.getParent();
                ItemBaseAdapter destAdapter = (ItemBaseAdapter)(newParent.getAdapter());
                List<Item> destList = destAdapter.getList();

                int removeLocation = srcList.indexOf(passedItem);
                int insertLocation = destList.indexOf(me);
				/*
				 * If drag and drop on the same list, same position,
				 * ignore
				 */
                if(srcList != destList || removeLocation != insertLocation){
                    AddOrRemove addOrRemove = new AddOrRemove();
                    if(addOrRemove.removeItemToList(srcList, passedItem)){
                        destList.add(insertLocation, passedItem);
                    }

                    srcAdapter.notifyDataSetChanged();
                    destAdapter.notifyDataSetChanged();
                }

                v.setBackgroundColor(resumeColor);

                break;
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackgroundColor(resumeColor);
            default:
                break;
        }

        return true;
    }

}
