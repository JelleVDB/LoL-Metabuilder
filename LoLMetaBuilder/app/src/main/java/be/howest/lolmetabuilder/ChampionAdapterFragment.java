package be.howest.lolmetabuilder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.gson.Gson;

import be.howest.lolmetabuilder.data.models.Champion;

/**
 * Created by jelle on 13/12/2014.
 */
public class ChampionAdapterFragment extends FragmentPagerAdapter {

    Champion champion;

    public ChampionAdapterFragment(FragmentManager fm, Champion champion)
    {
        super(fm);
        this.champion = champion;
    }

    @Override
    public Fragment getItem(int i) {
        Bundle args = new Bundle();
        args.putString("Champion", new Gson().toJson(champion));

        switch(i)
        {
            case 0:
                Fragment champoverview = ChampionOverviewFragment.newInstance();
                champoverview.setArguments(args);
                return champoverview;
            case 1:
                Fragment champskin = SkinFragment.newInstance();
                champskin.setArguments(args);
                return champskin;
            case 2:
                Fragment champlore = LoreFragment.newInstance();
                champlore.setArguments(args);
                return champlore;
            case 3:
                Fragment champabilities = AbilitiesFragment.newInstance();
                champabilities.setArguments(args);
                return champabilities;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
