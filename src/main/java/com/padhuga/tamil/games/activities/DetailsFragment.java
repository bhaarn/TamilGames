package com.padhuga.tamil.games.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.padhuga.tamil.games.R;
import com.padhuga.tamil.games.models.Data;
import com.padhuga.tamil.games.utilities.Constants;
import com.padhuga.tamil.games.utilities.ItemGenerator;


public class DetailsFragment extends Fragment {

    public static DetailsFragment newInstance(int position, int childPosition) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_POSITION, position);
        args.putInt(Constants.ARG_CHILD_POSITION, childPosition);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_details, container, false);
        setData(rootView);
        return rootView;
    }

    private void setData(ViewGroup rootView) {
        int position = getArguments().getInt(Constants.ARG_SECTION_POSITION);
        int childPosition = getArguments().getInt(Constants.ARG_CHILD_POSITION);
        Data data = ((BaseActivity) getActivity()).parentModel.getData().getType().get(position).getType().get(childPosition);

        Constants.parentCount = Integer.parseInt(data.getParentCount());
        Constants.layoutType = data.getParentType();
        Constants.childItems = data.getItems();
        Constants.parentHeading = data.getParentHeading();
        Constants.results = data.getResult();
        new ItemGenerator(getActivity()).init(rootView, Constants.parentCount, Constants.layoutType, Constants.parentHeading, Constants.childItems, Constants.results);
        if (Constants.parentCount != 2) {
            LinearLayout adParentView = rootView.findViewById(R.id.ad_parent_view);
            adParentView.setVisibility(View.VISIBLE);
            //  initializeAds();
        }
    }
}
