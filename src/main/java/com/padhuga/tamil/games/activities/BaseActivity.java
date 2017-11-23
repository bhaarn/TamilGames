package com.padhuga.tamil.games.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.padhuga.tamil.games.R;
import com.padhuga.tamil.games.models.ParentModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;


public class BaseActivity extends AppCompatActivity {

    private String appName;
    public ParentModel parentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appName = getPackageName();
        parentModel = readJSONFromAssetsAndConvertTogson();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_app_rate:
                rateApp();
                return true;
            case R.id.action_feature_help:
                showHelp();
                return true;
            case R.id.action_app_share:
                shareApp();
                return true;
            case R.id.action_more_apps:
                moreApps();
                return true;
            case R.id.action_about:
                aboutUs();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ParentModel readJSONFromAssetsAndConvertTogson() {
        ParentModel parentModel = null;
        try {
            InputStream is = getAssets().open(getResources().getString(R.string.json_file_name));
            int size = is.available();
            byte[] buffer = new byte[size];
            int bytesRead = is.read(buffer);
            is.close();
            String json = new String(buffer, getResources().getString(R.string.json_open_file_format));
            Gson gson = new Gson();
            parentModel  = gson.fromJson(json, ParentModel.class);
            Log.d("Json Bytes Read", Integer.toString(bytesRead));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return parentModel;
    }

    private void shareApp() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message)+appName);
        startActivity(Intent.createChooser(sharingIntent, "Share the application"));
    }

    private void moreApps() {
        startActivity(new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(getString(R.string.play_more_apps))));
    }

    private void aboutUs() {
        Fragment fragment = new AboutFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    private void rateApp() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.old_play_store)
                            + appName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.new_play_store)
                            + appName)));
        }
    }

    private void showHelp() {
        Fragment fragment = new HelpFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
}
