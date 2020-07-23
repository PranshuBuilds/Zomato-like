package com.crazy.recipies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OptionActivity extends AppCompatActivity {

    ImageView imageView;
    TextView mainTitle,discription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        imageView=findViewById(R.id.imageView);
        mainTitle=findViewById(R.id.main);
        discription=findViewById(R.id.disc);
        Intent i =getIntent();
        int image=i.getIntExtra("image",0);
        String maint=i.getStringExtra("textmain");
        String disc=i.getStringExtra("discription");
        mainTitle.setText(maint);
        discription.setText(disc);
        imageView.setImageResource(image);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
