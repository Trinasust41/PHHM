package com.example.phhm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class Retrievedata extends AppCompatActivity implements View.OnClickListener , NavigationView.OnNavigationItemSelectedListener{
    private Button chooseButton, saveButton, displayButton;
    private ImageView imageView;
    private EditText imageNameEditText;
    private Uri imageUri;
    private ProgressBar progressBar;
    private static final int IMAGE_REQUEST = 1;
    private DrawerLayout mdrawer;
    private ActionBarDrawerToggle mtoggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setLogo(R.drawable.menu);
        setContentView(R.layout.activity_retrievedata);
        //Toast.makeText(MainActivity.this,"FIREBASE",Toast.LENGTH_LONG).show();
        NavigationView navigationView=findViewById(R.id.navigationid);
        mdrawer=(DrawerLayout)findViewById(R.id.drawerlayout);

        mtoggle=new ActionBarDrawerToggle(this,mdrawer, R.string.Open, R.string.Close);
        mdrawer.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        chooseButton = findViewById(R.id.chooseImageButton);
        saveButton = findViewById(R.id.saveImageButton);
        displayButton = findViewById(R.id.displayImageButton);
        progressBar = findViewById(R.id.progressbarId);
        imageView = findViewById(R.id.imageViewId);
        imageNameEditText = findViewById(R.id.imageNameEditTextId);
        saveButton.setOnClickListener(this);
        chooseButton.setOnClickListener(this);
        displayButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chooseImageButton:
                openFileChooser();
                break;
            case R.id.saveImageButton:
                break;
            case R.id.displayImageButton:
                break;

        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && requestCode == RESULT_OK && data != null && data.getData() != null) {

            imageUri = data.getData();
            Picasso.get().load(imageUri).into(imageView);
        }

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.nav_account){
            Intent intent=new Intent(this,Myprofile.class);
            startActivity(intent);
        }
        if(menuItem.getItemId()==R.id.nav_logout){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if(menuItem.getItemId()==R.id.nav_home){
            Intent intent=new Intent(this,Patienthome.class);
            startActivity(intent);
        }
        return false;
    }
}

