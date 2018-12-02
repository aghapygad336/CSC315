package edu.uncw.seahawktours;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.widget.TextView;

import java.util.regex.Pattern;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        invalidateOptionsMenu();

        TextView authorText = findViewById(R.id.authorText);
        TextView aboutText = findViewById(R.id.archiveText);

        authorText.setText(R.string.author_text);
        aboutText.setText(R.string.archives_text);

        Pattern pattern = Pattern.compile("https://library.uncw.edu/archives_special/archives");

        Linkify.addLinks(aboutText, pattern, "https://");
    }

}
