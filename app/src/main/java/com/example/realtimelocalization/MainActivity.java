package com.example.realtimelocalization;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        button=findViewById(R.id.changelanguage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showchangelanguagedialog();

            }
        });


    }

    private void showchangelanguagedialog() {

        final String[] listitems={"Arabic","Hindi"};
        final AlertDialog.Builder mbuilder=new AlertDialog.Builder(MainActivity.this);
        mbuilder.setTitle("Change Language");
        mbuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i==0){
                    setLocal("ar");
                    recreate();
                }
                 else if (i==1){
                    setLocal("hi");
                    recreate();
                }
                 dialog.dismiss();

            }
        });

        AlertDialog mdialog=mbuilder.create();
        mdialog.show();
    }

    private void setLocal(String ar) {
        Locale locale = new Locale(ar);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Setting", MODE_PRIVATE).edit();
        editor.putString("My Language", ar);
        editor.apply();
    }


    public void loadlocale()
    {
        SharedPreferences preferences=getSharedPreferences("Settings",MODE_PRIVATE);
        String language=preferences.getString("My Language","");
        setLocal(language);
    }


}
