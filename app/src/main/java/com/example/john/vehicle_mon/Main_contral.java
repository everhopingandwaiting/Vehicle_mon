package com.example.john.vehicle_mon;

import android.app.Activity;
import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by john on 15-9-26.
 */
public class Main_contral extends Activity implements Serializable {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_contral_view);

    }
}
