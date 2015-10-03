package com.example.john.vehicle_mon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.john.global.Aging_test;

import java.io.Serializable;

/**
 * Created by john on 15-9-30.
 */
public class person_info extends Activity  implements Serializable{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.person_info);
        Button logout_sys = (Button) findViewById(R.id.logout_sys);
        logout_sys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aging_test.logout(person_info.this);
                Intent intent = new Intent(person_info.this, login_activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
                finish();

            }
        });

    }
}
