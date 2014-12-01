package be.howest.lolmetabuilder;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import be.howest.lolmetabuilder.data.Champion;

public class ChampionFragment extends Fragment {

    public GridView gvChamps;

    private OnFragmentInteractionListener mListener;

    public static ChampionFragment newInstance() {
        ChampionFragment fragment = new ChampionFragment();

        return fragment;
    }
    public ChampionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_champion, container, false);

        gvChamps = (GridView) view.findViewById(R.id.gvChampions);
        gvChamps.setAdapter(new ChampionAdapter());

        return view;
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

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }


    class ChampionAdapter extends ArrayAdapter<Champion>
    {
        private ArrayList<Champion> champions = new ArrayList<Champion>();

        public ChampionAdapter(){
            super(getActivity(), R.layout.cel_champ, R.id.txtChampName);

            //champions die op de mainactivity worden opgehaald gebruiken
            champions = MyActivity.champions;


            //hardcoded champion objecten
            /*//map => link tussen string & drawable image
            map = new HashMap<String, Integer>();
            map.put("gnar", R.drawable.gnar);
            map.put("aatrox", R.drawable.aatrox);
            map.put("ahri", R.drawable.ahri);
            map.put("akali", R.drawable.akali);
            map.put("alistar", R.drawable.alistar);
            map.put("amumu", R.drawable.amumu);
            map.put("anivia", R.drawable.anivia);

            //TODO code voor maken champion is niet correct. Zitten geen prijs & image values meer in!
            //Ik heb jouw code wat moeten aanpassen omdat ik iets veranderd heb in de Champion model

            Champion champ1 = new Champion(0, "Gnar", "gnar", "", 0, 0, 0, 0, "", "");
            champ1.setPriceIP(6300);

            Champion champ2 = new Champion(0, "Aatrox", "aatrox", "", 0, 0, 0, 0, "", "");
            champ2.setPriceIP(6300);

            Champion champ3 = new Champion(0, "Ahri", "ahri", "", 0, 0, 0, 0, "", "");
            champ3.setPriceIP(6300);

            Champion champ4 = new Champion(0, "Akali", "akali", "", 0, 0, 0, 0, "", "");
            champ4.setPriceIP(6300);

            Champion champ5 = new Champion(0, "Alistar", "alistar", "", 0, 0, 0, 0, "", "");
            champ5.setPriceIP(6300);

            Champion champ6 = new Champion(0, "Amumu", "amumu", "", 0, 0, 0, 0, "", "");
            champ6.setPriceIP(6300);

            Champion champ7 = new Champion(0, "Anivia", "anivia", "", 0, 0, 0, 0, "", "");
            champ7.setPriceIP(6300);

            champions.add(champ1);
            champions.add(champ2);
            champions.add(champ3);
            champions.add(champ4);
            champions.add(champ5);
            champions.add(champ6);
            champions.add(champ7);
            champions.add(champ1);
            champions.add(champ2);
            champions.add(champ3);
            champions.add(champ4);
            champions.add(champ5);
            champions.add(champ6);
            champions.add(champ7);
            champions.add(champ1);
            champions.add(champ2);
            champions.add(champ3);
            champions.add(champ4);
            champions.add(champ5);
            champions.add(champ6);
            champions.add(champ7);
            champions.add(champ1);
            champions.add(champ2);
            champions.add(champ3);
            champions.add(champ4);
            champions.add(champ5);
            champions.add(champ6);
            champions.add(champ7);
            champions.add(champ1);
            champions.add(champ2);
            champions.add(champ3);
            champions.add(champ4);
            champions.add(champ5);
            champions.add(champ6);
            champions.add(champ7);
            champions.add(champ1);
            champions.add(champ2);
            champions.add(champ3);
            champions.add(champ4);
            champions.add(champ5);
            champions.add(champ6);
            champions.add(champ7);
            champions.add(champ1);
            champions.add(champ2);
            champions.add(champ3);
            champions.add(champ4);
            champions.add(champ5);
            champions.add(champ6);
            champions.add(champ7);
            champions.add(champ1);
            champions.add(champ2);
            champions.add(champ3);
            champions.add(champ4);
            champions.add(champ5);
            champions.add(champ6);
            champions.add(champ7);
            champions.add(champ1);
            champions.add(champ2);
            champions.add(champ3);
            champions.add(champ4);
            champions.add(champ5);
            champions.add(champ6);
            champions.add(champ7);
            champions.add(champ1);
            champions.add(champ2);
            champions.add(champ3);
            champions.add(champ4);
            champions.add(champ5);
            champions.add(champ6);
            champions.add(champ7);
            champions.add(champ1);
            champions.add(champ2);
            champions.add(champ3);
            champions.add(champ4);
            champions.add(champ5);
            champions.add(champ6);
            champions.add(champ7);
            champions.add(champ1);
            champions.add(champ2);
            champions.add(champ3);
            champions.add(champ4);
            champions.add(champ5);
            champions.add(champ6);
            champions.add(champ7);*/

            this.addAll(champions);
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

            final Champion champ = champions.get(position);

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

            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Openen champion detail met fragment

                    //gekozen champion met de fragment meesturen
                    Fragment fragment = new ChampionOverviewFragment();
                    Bundle args = new Bundle();
                    args.putString("Champion", new Gson().toJson(champ));
                    fragment.setArguments(args);

                    //openen fragment
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .addToBackStack(null)
                            .commit();

                    //Toast.makeText(v.getContext(), "Champion: " + champ.getName(), Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }

        private Drawable getDrawableResourceByName(String name) {
            String packageName = getActivity().getPackageName();
            int resId = getResources().getIdentifier( name, "drawable", packageName);
            return getResources().getDrawable(resId);
        }
    }
}
