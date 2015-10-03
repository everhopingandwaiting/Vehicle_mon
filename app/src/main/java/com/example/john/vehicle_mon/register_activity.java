package com.example.john.vehicle_mon;

import android.app.Activity;
import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by john on 15-9-9.
 */
public class register_activity  extends Activity  implements Serializable{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.register_activity);

    }
}
