package in.org.celesta2k17.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import in.org.celesta2k17.R;

/**
 * Created by manish on 2/10/17.
 */

public class SocialActivity extends AppCompatActivity {

    final String facebookUrl = "https://www.facebook.com/CelestaIITP/";
    final String twitterUrl = "https://twitter.com/celesta_iitp";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_main);

        ImageView twitterImageView = findViewById(R.id.imageViewtwt);
        twitterImageView.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(twitterUrl));
            startActivity(intent);
        });

        ImageView fbImageView = findViewById(R.id.imageViewfb);
        fbImageView.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(facebookUrl));
            startActivity(intent);

        });
    }
}
