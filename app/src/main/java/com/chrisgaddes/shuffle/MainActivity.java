package com.chrisgaddes.shuffle;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_card;
    private Button btn_random_card;
    private Button btn_pick_image;
    private ImagePicker imagePicker;

    private int resID;
    private int rndInt;
    private Random rand;
    private TypedArray imgs;
    private final static int EXTERNAL_STORAGE_PERMISSION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestExternalStoragePermission();

        imgs = getResources().obtainTypedArray(R.array.cards);
        rand = new Random();


        iv_card = (ImageView) findViewById(R.id.iv_card);
        btn_random_card = (Button) findViewById(R.id.btn_random_card);
        btn_pick_image = (Button) findViewById(R.id.btn_pick_image);

       btn_random_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rndInt = rand.nextInt(imgs.length());
                resID = imgs.getResourceId(rndInt, 0);
                iv_card.setImageResource(resID);

            }
        });





        imagePicker = new ImagePicker(MainActivity.this);
        imagePicker.setImagePickerCallback(new ImagePickerCallback(){
                                               @Override
                                               public void onImagesChosen(List<ChosenImage> images) {
                                                   // Display images
                                               }

                                               @Override
                                               public void onError(String message) {
                                                   // Do error handling
                                               }


                                           }
        );
// imagePicker.allowMultiple(); // Default is false
// imagePicker.shouldGenerateMetadata(false); // Default is true
// imagePicker.shouldGenerateThumbnails(false); // Default is true


        btn_pick_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imagePicker.pickImage();

            }
        });

    }


    private void requestExternalStoragePermission() {
        if ((ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS},
                    EXTERNAL_STORAGE_PERMISSION_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

}
