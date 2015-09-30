package com.example.john.vehicle_mon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by john on 15-9-9.
 */
public class login_activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        TextView reg_label = (TextView) findViewById(R.id.reg_label);
        reg_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(login_activity.this, register_activity.class);
                startActivity(intent);

            }
        });
        Button loginbtn = (Button) findViewById(R.id.login_btn);
           loginbtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent=new Intent(login_activity.this,Map_activity.class);
                   startActivity(intent);
               }
           });
    }
}
