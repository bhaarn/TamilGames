package com.padhuga.tamil.games.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.padhuga.tamil.games.activities.DetailsFragment;
import com.padhuga.tamil.games.models.ParentModel;


public class SectionDetailAdapter extends FragmentPagerAdapter {

    private final ParentModel parentModel;
    private final int position;

    public SectionDetailAdapter(FragmentManager fm, ParentModel parentModel, int position) {
        super(fm);
        this.parentModel = parentModel;
        this.position = position;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailsFragment.newInstance(this.position, position);
    }

    @Override
    public int getCount() {
        return parentModel.getData().getType().get(position).getType().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.parentModel.getData().getType().get(position).getTitle();
    }
}
