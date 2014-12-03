package be.howest.lolmetabuilder;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import be.howest.lolmetabuilder.data.Champion;
import be.howest.lolmetabuilder.data.FreeChamp;
import be.howest.lolmetabuilder.data.Item;
import be.howest.lolmetabuilder.data.Leaf;
import be.howest.lolmetabuilder.data.MasteryTree;
import be.howest.lolmetabuilder.data.Rune;
import be.howest.lolmetabuilder.json.api_ophalen;


public class MyActivity extends Activity implements ChampionFragment.OnFragmentInteractionListener,
                                                    ItemFragment.OnFragmentInteractionListener,
                                                    BuildsFragment.OnFragmentInteractionListener,
                                                    SimulateFragment.OnFragmentInteractionListener,
                                                    SettingsFragment.OnFragmentInteractionListener,
                                                    ChampionOverviewFragment.OnFragmentInteractionListener{
    private ProgressDialog pDialog;
    private static Boolean isInGeladen = false;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private String[] layers;
    private ActionBarDrawerToggle drawerToggle;
    private int currentFragment = 0;

    public static ArrayList<FreeChamp> freeChamps = new ArrayList<FreeChamp>();

    public static ArrayList<Champion> champions = new ArrayList<Champion>();
    public static ArrayList<Item> items = new ArrayList<Item>();
    public static ArrayList<Leaf> leafs = new ArrayList<Leaf>();
    public static ArrayList<Rune> runes = new ArrayList<Rune>();
    public static ArrayList<MasteryTree> masteryTrees = new ArrayList<MasteryTree>();

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class GetChampionTask extends AsyncTask<String, Void, List<String>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(MyActivity.this);
            pDialog.setMessage("Loading data...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected List<String> doInBackground(String... params) {
            try {
                String PACKAGE_NAME = getApplicationContext().getPackageName();
                ApplicationInfo appInfo = getPackageManager().getApplicationInfo(PACKAGE_NAME, PackageManager.GET_META_DATA);

                // Als je ze wilt testen moet je ze 1 per 1 uit commentaar halen
                // En freeChamps bijvoorbeeld naar collection hernoemen

                ArrayList<String> dc = new ArrayList<String>();

                champions = api_ophalen.champions(appInfo);
                items = api_ophalen.items(appInfo);
                leafs = api_ophalen.leafs(appInfo);
                runes = api_ophalen.runes(appInfo);
                masteryTrees = api_ophalen.masteryTrees(appInfo);

                ArrayList<FreeChamp> temp = api_ophalen.freechampRotation(appInfo);

                if (champions != null) {
                    for (Champion c : champions) {
                        for (FreeChamp fc : temp) {
                            if (c.getId() == fc.getId()) {
                                FreeChamp freeChamp = new FreeChamp(fc.getId());
                                freeChamp.setChampion(c);

                                freeChamps.add(freeChamp);
                            }
                        }
                    }
                }

                if (freeChamps.size() == 10) {
                    dc.add("Complete");
                }
                else {
                    dc.add("Fail");
                }

                return dc;
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        // Zoeken van een index van een bepaald object in den array
        // Om daarna specifiek data van een object te kunnen testen
        /*private String filterObjects(ArrayList<String> collection, String id) {
            String result = "Nothing";

            for (String o : collection) {
                if (o.equals(id)) { //
                    result = id + " (" + collection.indexOf(o) + ")";
                }
            }

            return result;
        }*/

        @Override
        protected void onPostExecute(List<String> result) {

            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }

            super.onPostExecute(result);

            ArrayList<String> dc = (ArrayList<String>) result;

            //String o = filterObjects(ao, "4112"); // Fury -> result: 5

            //System.out.println(ao.get(5));

            Toast.makeText(getBaseContext(), "" + dc.get(0), Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(!isInGeladen) {
            GetChampionTask task = new GetChampionTask();
            task.execute();

            isInGeladen = true;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle((Activity) this, drawerLayout, R.drawable.ic_drawer, 0, 0) {
            public void onDrawerClosed(View view) {
                // TODO: Titel veranderen naar activity name
                getActionBar().setTitle(R.string.app_name);


            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(R.string.app_name);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        layers = getResources().getStringArray(R.array.titles_array);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, layers));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        // Eerste item van navigation default selecteren
        drawerList.setItemChecked(0, true);
        drawerList.setSelection(0);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    /*public static class PlaceholderFragment extends Fragment {
        private GridView gvFreeChamps;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_my, container, false);

            gvFreeChamps = (GridView) view.findViewById(R.id.gvFreeChamps);
            gvFreeChamps.setAdapter(new FreeChampionAdapter());

            return view;
        }

        class FreeChampionAdapter extends ArrayAdapter<FreeChamp> {
            public FreeChampionAdapter() {
                super(getActivity(), R.layout.cel_champ, R.id.txtChampName);

                this.addAll(MyActivity.freeChamps);
            }

            class ViewHolder {
                RelativeLayout imgChamp;
                TextView txtChampName;
                TextView txtChampPrice;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                //View row = super.getView(position, convertView, parent);
                ViewHolder viewHolder = new ViewHolder();

                final Champion champ = freeChamps.get(position).getChampion();

                if(convertView == null)
                {
                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    convertView = inflater.inflate(R.layout.cel_champ, null);
                    viewHolder = new ViewHolder();

                    viewHolder.imgChamp = (RelativeLayout) convertView.findViewById(R.id.imgChamp);
                    viewHolder.txtChampName = (TextView) convertView.findViewById(R.id.txtChampName);
                    viewHolder.txtChampPrice = (TextView) convertView.findViewById(R.id.txtChampPrice);

                    convertView.setTag(viewHolder);
                }else{
                    viewHolder = (ViewHolder) convertView.getTag();
                }

                viewHolder.txtChampName.setText(champ.getName());
                viewHolder.txtChampPrice.setText("" + champ.getPriceIP());
                viewHolder.imgChamp.setBackground(getDrawableResourceByName(champ.getImage().toLowerCase()));

                return convertView;
            }

            private Drawable getDrawableResourceByName(String name) {
                String packageName = getActivity().getPackageName();
                int resId = getResources().getIdentifier( name, "drawable", packageName);
                return getResources().getDrawable(resId);
            }
        }
    }*/

    public class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Fragment fragment = null;
            currentFragment = position;

            switch(position)
            {
                case 0:
                    fragment = new PlaceholderFragment();
                    break;
                case 1:
                    fragment = new ChampionFragment().newInstance();
                    break;
                case 2:
                    fragment = new ItemFragment().newInstance();
                    break;
                case 3:
                    fragment = new BuildsFragment().newInstance();
                    break;
                case 4:
                    fragment = new SimulateFragment().newInstance();
                    break;
                case 5:
                    fragment = new SettingsFragment().newInstance();
                    break;
                default:
                    break;
            }

            if(fragment != null)
            {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction trans = fragmentManager.beginTransaction();
                trans.replace(R.id.container, fragment);
                trans.commit();
                fragmentManager.popBackStack();

                drawerLayout.closeDrawers();
            }

        }
    }
}
