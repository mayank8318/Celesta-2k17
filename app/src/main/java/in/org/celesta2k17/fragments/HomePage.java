package in.org.celesta2k17.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import in.org.celesta2k17.activities.ScheduleActivity;
import in.org.celesta2k17.activities.SocialActivity;

import in.org.celesta2k17.R;
import in.org.celesta2k17.activities.DevelopersActivity;
import in.org.celesta2k17.activities.EventsActivity;
import in.org.celesta2k17.activities.TeamActivity;
import in.org.celesta2k17.adapters.EventsAdapter;
import in.org.celesta2k17.listeners.ViewPagerCustomDuration;


import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mayank on 26/5/17.
 */

public class HomePage extends android.support.v4.app.Fragment {

    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 2000; // time in milliseconds between successive task executions.
    LinearLayout eventsLinearLayout;
    //    LinearLayout galleryLinearLayout;
    LinearLayout aboutFrameLayout;
    LinearLayout scheduleLinearLayout;
    LinearLayout sponsorsLinearLayout;
    LinearLayout teamLinearLayout;
    LinearLayout devLinearLayout;

    LinearLayout socialLinearLayout ;
    Toast comingSoonToast;

    private int currentPage = 0;
    Timer timer ;
    private int NUM_PAGES = 6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        final ViewPagerCustomDuration viewPagerCustomDuration = rootView.findViewById(R.id.events_pager);
        viewPagerCustomDuration.setScrollDuration(900);
        EventsAdapter eventsAdapter = new EventsAdapter(getContext(),
                getResources().obtainTypedArray(R.array.array_home_slide_show));

        viewPagerCustomDuration.setAdapter(eventsAdapter);
        viewPagerCustomDuration.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /*Adding automatic swap to the images
        * */
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == NUM_PAGES) {
                currentPage = 0;
            }
            viewPagerCustomDuration.setCurrentItem(currentPage++, true);
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);


        comingSoonToast = Toast.makeText(getContext(), getResources().getString(R.string.coming_soon), Toast.LENGTH_SHORT);

        FloatingActionButton fab = rootView.findViewById(R.id.fab_maps);
        fab.setOnClickListener(view -> {
           // String uri = "https://www.google.com/maps/@?api=1&map_action=map&center=25.535752,84.851065&zoom=16&basemap=satellite";
            String uri="https://www.google.com/maps/d/viewer?mid=1Tub6_KM_0Tv8UHkh97SP9Tehv78HBv1e&usp=sharingax&basemap=satellite";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            Objects.requireNonNull(getContext()).startActivity(intent);
        });

        eventsLinearLayout = rootView.findViewById(R.id.events);
        eventsLinearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(rootView.getContext(), EventsActivity.class);
            startActivity(intent);

        });

        aboutFrameLayout = rootView.findViewById(R.id.about);
        aboutFrameLayout.setOnClickListener(v -> {
//                Intent intent = new Intent(rootView.getContext(), AboutActivity.class);
//                startActivity(intent);
            comingSoonToast.show();
        });

        scheduleLinearLayout = rootView.findViewById(R.id.schedule);
        scheduleLinearLayout.setOnClickListener(v -> {
//                comingSoonToast.show();
            Intent intent = new Intent(rootView.getContext(), ScheduleActivity.class);
            startActivity(intent);
        });

        devLinearLayout = rootView.findViewById(R.id.developers);
        devLinearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(rootView.getContext(), DevelopersActivity.class);
            startActivity(intent);
        });

        sponsorsLinearLayout = rootView.findViewById(R.id.sponsors_menu_item);
        sponsorsLinearLayout.setOnClickListener(view -> comingSoonToast.show());

        teamLinearLayout = rootView.findViewById(R.id.team);
        teamLinearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(rootView.getContext(), TeamActivity.class);
            startActivity(intent);

        });
        socialLinearLayout= rootView.findViewById(R.id.social);
        socialLinearLayout.setOnClickListener(v -> {
            Intent intent= new Intent(rootView.getContext(), SocialActivity.class) ;
            startActivity(intent);
        });
        return rootView;
    }
}
