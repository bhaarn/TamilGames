package com.padhuga.tamil.games.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.padhuga.tamil.games.R;
import com.padhuga.tamil.games.utilities.Constants;

import java.util.ArrayList;

public class MainFragment extends ListFragment {
    private int parentPosition;

    public static MainFragment newInstance(int position) {
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_POSITION, position);
        mainFragment.setArguments(args);
        return mainFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        parentPosition = getArguments().getInt(Constants.ARG_SECTION_POSITION);
        initializeList(rootView);
        return rootView;
    }

    private void initializeList(View rootView) {
        ArrayList<String> listData = new ArrayList<>();
        for (int i = 0; i < ((BaseActivity)getActivity()).parentModel.getData().getType().get(parentPosition).getType().size(); i++) {
            listData.add(((BaseActivity)getActivity()).parentModel.getData().getType().get(parentPosition).getType().get(i).getTitle());
        }

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listData);
        setListAdapter(listViewAdapter);

      /*  listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                setupUI(position);
            }
        });*/
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        setupUI(position);
    }

    private void setupUI(int childPosition) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(Constants.ARG_SECTION_POSITION, parentPosition);
        intent.putExtra(Constants.ARG_CHILD_POSITION, childPosition);
        startActivity(intent);
    }
}
