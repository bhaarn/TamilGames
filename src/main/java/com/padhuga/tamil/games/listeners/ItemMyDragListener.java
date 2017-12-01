package com.padhuga.tamil.games.listeners;


import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.AbsListView;

import com.padhuga.tamil.games.activities.LinearLayoutAbsListView;
import com.padhuga.tamil.games.adapters.ItemBaseAdapter;
import com.padhuga.tamil.games.models.Item;
import com.padhuga.tamil.games.models.PassObject;
import com.padhuga.tamil.games.models.Results;
import com.padhuga.tamil.games.utilities.AddOrRemove;
import com.padhuga.tamil.games.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

public class ItemMyDragListener implements View.OnDragListener {
    private ArrayList<Results> results;

    public ItemMyDragListener(ArrayList<Results> results) {
        this.results = results;
    }

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

                if(srcAdapter.getList().size() == 0) { //oldParent.getTag().equals("listView1") &&
                    Constants.droppedItems = new ArrayList<>();
                    for(int i=0;i<destList.size();i++) {
                        Constants.droppedItems.add(destList.get(i).ItemPlaceValue);
                    }
                    ArrayList<Integer> a = new ArrayList<>();
                    a = Constants.droppedItems;
                    if(results.get(0).getParent1() != null && Constants.droppedItems.size() == results.get(0).getParent1().size() && Constants.droppedItems.removeAll(results.get(0).getParent1()) && Constants.droppedItems.size() == 0) {
                        Log.d("Bharani", "Success");
                    } else if(results.get(0).getParent2() != null && a.size() == results.get(0).getParent2().size() && a.removeAll(results.get(0).getParent2()) && a.size() == 0) {
                        Log.d("Bharani", "Success");
                    } else {
                        Log.d("Bharani", "Failure");
                    }
                }

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
