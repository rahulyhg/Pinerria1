package sweet.home.homesweethome.Fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sweet.home.homesweethome.Activity.MainActivitie;
import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.MyPrefrences;

/**
 * A simple {@link Fragment} subclass.
 */
public class RemarkAssessment extends Fragment {


    public RemarkAssessment() {
        // Required empty public constructor
    }
    private TabLayout tabLayout;
    private ViewPager viewPager;

    RelativeLayout relat;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_remark_assessment, container, false);

        MainActivitie.mTopToolbar.setVisibility(View.GONE);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        relat = (RelativeLayout) view.findViewById(R.id.relat);

        Log.d("StudentId",getArguments().getString("sId"));

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        ImageView textBack= view.findViewById(R.id.textBack);
        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Profile();
                android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });

        if (MyPrefrences.getColorGender(getActivity()).equals("male")){
            Log.d("dfggfgdg","Male");
            relat.setBackgroundResource(R.drawable.redius_img_in);
            tabLayout.setBackgroundResource(R.drawable.redius_img_in);
        }
        else  if (MyPrefrences.getColorGender(getActivity()).equals("female")){
            Log.d("dfggfgdg","Female");
            relat.setBackgroundResource(R.drawable.redius_img_in_male);
            tabLayout.setBackgroundResource(R.drawable.redius_img_in_male);

        }

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        //        adapter.addFrag(new DeshAll(), "All"+"("+jsonCount.optString("totalAdv")+")");
        adapter.addFrag(TeachRemark.NewInstance(getArguments().getString("sId")),"Remarks");
        adapter.addFrag(Assessments.NewInstance(getArguments().getString("sId")),"Assessments");


        viewPager.setAdapter(adapter);
        setCustomFont();

    }
    public void setCustomFont() {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    //Put your font in assests folder
                    //assign name of the font here (Must be case sensitive)
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "font1.xml"));
                }
            }
        }
    }


    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }


    }


}
