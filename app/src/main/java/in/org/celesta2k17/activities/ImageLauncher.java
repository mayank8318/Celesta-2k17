package in.org.celesta2k17.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


import java.io.File;
import java.util.Objects;

import in.org.celesta2k17.R;

/**
 * Created by manish on 6/9/17.
 */

public class ImageLauncher extends AppCompatActivity {

    File picture;
    private String LOG_TAG = getClass().toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview);


        picture = (File) Objects.requireNonNull(getIntent().getExtras()).get("imageV");

        assert picture != null;
        if (picture.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(picture.getAbsolutePath());
            ImageView myImage = findViewById(R.id.launcher);
            myImage.setImageBitmap(myBitmap);
        }


    }
}
