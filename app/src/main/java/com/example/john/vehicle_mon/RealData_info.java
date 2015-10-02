package com.example.john.vehicle_mon;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.john.domain.RealData;
import com.example.john.global.Aging_test;
import com.google.gson.JsonObject;

import util.ONHttpCallBack;
import util.net_util;

/**
 * Created by john on 15-10-2.
 */
public class RealData_info  extends Activity{
    private TextView is_lock_value,is_vibrate_value,is_electrify_value,
            is_lookcar_value, is_lockdoor_value,   contral_stat_value,
            StandbyElectricity_info_value, Electricity_info_value;

    private Button check_rt_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realdata_info);
        initView();
        requestForRealData();
    }


    void requestForRealData() {
          SharedPreferences preferences = getSharedPreferences(Aging_test.getAgingTest(this).userSharePref, MODE_PRIVATE);

        JsonObject object = new JsonObject();
        object.addProperty("action", "rtdata");

          object.addProperty("user", preferences.getString("userName", "admin"));

         System.out.println(preferences.getString("userName", "admin") + "------------------*******************测试******************--------");
        net_util.goFor(net_util.RealTimeDataBl, new ONHttpCallBack() {
            @Override
            public void onHttpCallBack(JsonObject jsonObject) {
                if (jsonObject == null) {
                    showErrorToast();
                    return;
                }
                RealData data = new RealData();
                data.setUnitnumber(jsonObject.get("unitnumber").getAsString());
                data.setMonitoringInfo(jsonObject.get("MonitoringInfo").getAsString());
                data.setFault(jsonObject.get("Fault").getAsString());
                data.setStandbyElectricity(jsonObject.get("StandbyElectricity").getAsString());
                data.setElectricity(jsonObject.get("Electricity").getAsString());
               /*
               *     处理数据
               *
               * */

                handleData(data);

            }
        }, object);

  }

    void showErrorToast(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RealData_info.this, "信息为空", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void handleData(RealData data ) {


    }
    void initView() {
        is_lock_value = (TextView) findViewById(R.id.is_lock_value);
        is_vibrate_value = (TextView) findViewById(R.id.is_vibrate_value);
        is_electrify_value = (TextView) findViewById(R.id.is_electrify_value);


        is_lookcar_value = (TextView) findViewById(R.id.is_lookcar_value);
        is_lockdoor_value = (TextView) findViewById(R.id.is_lockdoor_value);

        contral_stat_value = (TextView) findViewById(R.id.contral_stat_value);
        StandbyElectricity_info_value = (TextView) findViewById(R.id.StandbyElectricity_info_value);

        Electricity_info_value = (TextView) findViewById(R.id.Electricity_info_value);

        check_rt_button = (Button) findViewById(R.id.check_rt_button);
    }
}
