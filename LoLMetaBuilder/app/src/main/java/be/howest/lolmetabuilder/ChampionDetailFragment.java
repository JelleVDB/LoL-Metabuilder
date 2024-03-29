package be.howest.lolmetabuilder;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;

import be.howest.lolmetabuilder.data.models.Build;
import be.howest.lolmetabuilder.data.models.Champion;

public class ChampionDetailFragment extends Fragment implements ActionBar.TabListener {

    private OnFragmentInteractionListener mListener;
    ActionBar actionBar;
    ViewPager viewPager;
    ChampionAdapterFragment ft;
    private Champion selectedChampion;

    public static ChampionDetailFragment newInstance() {
        ChampionDetailFragment fragment = new ChampionDetailFragment();

        return fragment;
    }
    public ChampionDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_my_champion_overview, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()){
            case R.id.action_add:
                openGenerateBuild();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_champion_detail, container, false);

        setHasOptionsMenu(true);

        //get selected champion
        Bundle bundle = getArguments();
        Champion champion = new Gson().fromJson(bundle.getString("Champion"), Champion.class);
        selectedChampion = champion;

        viewPager = (ViewPager) view.findViewById(R.id.pager);
        ft = new ChampionAdapterFragment(getChildFragmentManager());

        actionBar = getActivity().getActionBar();
        viewPager.setAdapter(ft);

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        String[] tabTitles = getResources().getStringArray(R.array.titles_champion_tabs);
        for(String tabTitle : tabTitles)
        {
            actionBar.addTab(actionBar.newTab().setText(tabTitle).setTabListener(this));
        }

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                // When swiping between pages, select the
                // corresponding tab.
                actionBar.setSelectedNavigationItem(position);
                viewPager.setCurrentItem(position);
            }
        });

        return view;
    }

    public void openGenerateBuild(){
        //champion in build object steken
        MainActivity.championBuild = new Build(selectedChampion);

        //gekozen champion met de fragment meesturen
        Fragment fragment = GeneratedBuildFragment.newInstance();
        Bundle args = new Bundle();
        args.putString("Champion", new Gson().toJson(selectedChampion));
        args.putString("From", "Builds");
        fragment.setArguments(args);

        //openen fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("GenerateBuild")
                .commit();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
