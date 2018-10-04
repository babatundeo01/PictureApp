package com.example.consultants.pictureapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button snapBTN;
    private ImageView imageView;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        snapBTN = findViewById(R.id.snapBTN);
        snapBTN.setOnClickListener(this);
        imageView = findViewById(R.id.imgView);
    }

    @Override
    public void onClick(View itemView) {
        switch (itemView.getId()){
            case R.id.snapBTN:
                //here we process  action relevant to id
                processImage();
                break;
        }
    }

    private void processImage() {
        Intent imgIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (imgIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(imgIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle imgBundle = data.getExtras();
            Bitmap imgBitmap = (Bitmap) imgBundle.get("data");
            imageView.setImageBitmap(imgBitmap);
        }
    }
}
