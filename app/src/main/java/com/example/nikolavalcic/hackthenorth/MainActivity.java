package com.example.nikolavalcic.hackthenorth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    Uri outputFileUri;
    ImageView imageView;
    public static final int REQUEST_IMAGE_CAPTURE=177;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCamera=(Button)findViewById(R.id.btnCamera);
        imageView=(ImageView)findViewById(R.id.imageView);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageFilePath=Environment.getExternalStorageDirectory().getAbsolutePath()+"/pic.jpg";
                File imageFile = new File(imageFilePath);

                Uri imageFileUri= FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName()+".provider",imageFile);

                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //Log.i("path", file.getAbsolutePath());

                //outputFileUri=Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);

                startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Log("YOooooooo");
        Log.i("code", "yoooooooooo");
        //Log.i("code", data.getExtras().get("data").toString());
        super.onActivityResult(requestCode, resultCode, data);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        Bitmap bitmap = BitmapFactory.decodeFile("file:/storage/emulated/0/DCIM/Camera/12345.jpg", options);
        //Bitmap bitmap= (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }
}
