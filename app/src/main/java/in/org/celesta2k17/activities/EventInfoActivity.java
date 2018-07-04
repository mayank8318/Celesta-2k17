package in.org.celesta2k17.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import in.org.celesta2k17.R;

public class EventInfoActivity extends AppCompatActivity {

    public static final String EXTRA_HEADER = "Header",
            EXTRA_DESCRIPTION = "Text",
            EXTRA_RULES = "Rules",
            EXTRA_VENUE = "Venue",
            EXTRA_DATE_TIME = "DateTime",
            EXTRA_IMAGE_ID = "ImageId",
            EXTRA_ORGANIZERS = "Organizers",
            EXTRA_CONTACTS = "Contacts";

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(getApplicationContext()).clearMemory();
        Glide.get(getApplicationContext()).trimMemory(TRIM_MEMORY_COMPLETE);
        System.gc();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_tb_event_info);

        // Get the Intent that started this activity and extract the strings needed
        Intent intent = getIntent();
        final String header = intent.getStringExtra(EXTRA_HEADER);
        String text = intent.getStringExtra(EXTRA_DESCRIPTION);
        String rules = intent.getStringExtra(EXTRA_RULES);
        final String dateTime = intent.getStringExtra(EXTRA_DATE_TIME);
        String venue = intent.getStringExtra(EXTRA_VENUE);
        final int imageId = intent.getIntExtra(EXTRA_IMAGE_ID, -1);

        AppBarLayout appBarLayout = findViewById(R.id.appbar_event_info);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(header);
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        ((TextView) findViewById(R.id.event_info_name)).setText(header);
        if (imageId != -1)
            Glide.with(this)
                    .load(imageId)
                    .into((ImageView) findViewById(R.id.event_info_imageview));

        if (text.equals("-1"))
            findViewById(R.id.event_info_textview).setVisibility(View.GONE);
        else {
            ((TextView) findViewById(R.id.event_info_textview)).setText(text);
        }

        ((TextView)findViewById(R.id.event_info_textview)).setMovementMethod(LinkMovementMethod.getInstance());
        final String finalText = text.equals("-1") ? "Keep checking the app and website for updates." : text;

        TextView rulesTextView = findViewById(R.id.event_rules_textview);
        if (rules.equals("-1")) {
            rulesTextView.setVisibility(View.GONE);
            (findViewById(R.id.rules_header)).setVisibility(View.GONE);
        } else {
            rulesTextView.setText(rules);
        }
        String organizers = intent.getStringExtra(EXTRA_ORGANIZERS);
        final String contacts = intent.getStringExtra(EXTRA_CONTACTS);
        if (organizers.equals("-1"))
            ((TextView) findViewById(R.id.event_organizers)).setVisibility(View.GONE);
        else
            ((TextView) findViewById(R.id.event_organizers)).setText(organizers);

        if (contacts.equals("-1"))
            ((TextView) findViewById(R.id.event_contact)).setVisibility(View.GONE);
        else
            ((TextView) findViewById(R.id.event_contact)).setText(contacts);

        if (!dateTime.equals("-1"))
            ((TextView) findViewById(R.id.event_date_time)).setText(dateTime);
        else
            ((TextView) findViewById(R.id.event_date_time)).setVisibility(View.GONE);

        if (!venue.equals("-1"))
            ((TextView) findViewById(R.id.event_venue)).setText(venue);
        else
            ((TextView) findViewById(R.id.event_venue)).setVisibility(View.GONE);

        FloatingActionButton fab = findViewById(R.id.fab_share_event);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources resources = getResources();
                String shareString = resources.getText(R.string.share_message) + "\n"
                        + resources.getText(R.string.name) + ": " + header + "\n"
                        + resources.getText(R.string.date_time) + ": " + (dateTime.equals("-1") ? "Keep checking the app and website for updates." : dateTime) + "\n"
                        + finalText;
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareString);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, resources.getText(R.string.share_to)));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
