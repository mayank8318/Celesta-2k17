package in.org.celesta2k17.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import in.org.celesta2k17.R;
import in.org.celesta2k17.adapters.ClubsRecyclerViewAdapter;

public class EventsActivity extends AppCompatActivity implements ClubsRecyclerViewAdapter.ListCardClick {

    RecyclerView recyclerView;
    ClubsRecyclerViewAdapter clubsRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_events);

        recyclerView = findViewById(R.id.rv_events);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        Resources resources = getResources();

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorEvents)));

        clubsRecyclerViewAdapter = new ClubsRecyclerViewAdapter(getApplicationContext(), this, resources.getStringArray(R.array.array_event_headers),
                resources.getStringArray(R.array.array_event_text),
                resources.getStringArray(R.array.array_event_intent),
                resources.obtainTypedArray(R.array.array_event_images));
        recyclerView.setAdapter(clubsRecyclerViewAdapter);
    }

    @Override
    public void onListClick(String intent) throws ClassNotFoundException {
        Intent intentNew = new Intent(this, Class.forName(intent));
        startActivity(intentNew);
    }
}
