package com.padhuga.tamil.games.listeners;


import android.view.DragEvent;
import android.view.View;
import android.widget.AbsListView;

import com.padhuga.tamil.games.activities.LinearLayoutAbsListView;
import com.padhuga.tamil.games.adapters.ItemBaseAdapter;
import com.padhuga.tamil.games.models.Item;
import com.padhuga.tamil.games.models.PassObject;
import com.padhuga.tamil.games.utilities.AddOrRemove;

import java.util.List;

public class ItemMyDragListener implements View.OnDragListener {

    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                PassObject passObj = (PassObject)event.getLocalState();
                View view = passObj.view;
                Item passedItem = passObj.item;
                List<Item> srcList = passObj.srcList;
                AbsListView oldParent = (AbsListView)view.getParent();
                ItemBaseAdapter srcAdapter = (ItemBaseAdapter)(oldParent.getAdapter());

                LinearLayoutAbsListView newParent = (LinearLayoutAbsListView)v;
                ItemBaseAdapter destAdapter = (ItemBaseAdapter)(newParent.absListView.getAdapter());
                List<Item> destList = destAdapter.getList();

                AddOrRemove addOrRemove = new AddOrRemove();
                if(addOrRemove.removeItemToList(srcList, passedItem)){
                    addOrRemove.addItemToList(destList, passedItem);
                }

                srcAdapter.notifyDataSetChanged();
                destAdapter.notifyDataSetChanged();

                //smooth scroll to bottom
                newParent.absListView.smoothScrollToPosition(destAdapter.getCount()-1);

                break;
            case DragEvent.ACTION_DRAG_ENDED:
            default:
                break;
        }

        return true;
    }
}
