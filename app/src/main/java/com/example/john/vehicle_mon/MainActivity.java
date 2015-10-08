package com.example.john.vehicle_mon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.umeng.analytics.MobclickAgent;

import java.io.Serializable;

public class MainActivity extends Activity implements Serializable{


    private EditText unitnumber_text;
    private Context context ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.buttonstr);
        unitnumber_text = (EditText) findViewById(R.id.unitnumber_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login_activity.class);
                Bundle bundle = new Bundle();
//                bundle.putString("unitNumber", unitnumber_text.getText().toString().trim().toLowerCase());
//                intent.putExtras(bundle);
                context = getApplicationContext();
                SharedPreferences preferences = context.getSharedPreferences("RealData", Context.MODE_PRIVATE);
                preferences.edit().clear().apply();
                preferences.edit().putString("unitnumber", unitnumber_text.getText().toString().trim().toLowerCase()).apply();
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this
        );

    }
}
