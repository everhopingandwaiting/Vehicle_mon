package com.example.john.vehicle_mon;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realdata_info);

        context = getApplicationContext();
        initView();
         check_rt_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 requestForRealData();
                 handleData();

             }
         });
    }


    void requestForRealData() {
        //  SharedPreferences preferences = getSharedPreferences(Aging_test.getAgingTest(this).userSharePref, MODE_PRIVATE);

        JsonObject object = new JsonObject();
        object.addProperty("action", "rtdata");

        //  object.addProperty("user", preferences.getString("userName", "admin"));
        object.addProperty("unitnumber", "123456789012345");
      //   System.out.println(preferences.getString("user", "admin") + "------------------*******************测试******************--------");
        net_util.goFor(net_util.RealTimeDataBl, new ONHttpCallBack() {
            @Override
            public void onHttpCallBack(JsonObject jsonObject) {
                if (jsonObject == null) {
                    showErrorToast();
                    return;
                }
//              RealData   dataR = new RealData();
//                dataR.setUnitnumber(jsonObject.get("unitnumber").getAsString());
//                dataR.setMonitoringInfo(jsonObject.get("MonitoringInfo").getAsString());
//                dataR.setFault(jsonObject.get("Fault").getAsString());
//                dataR.setStandbyElectricity(jsonObject.get("StandbyElectricity").getAsString());
//                dataR.setElectricity(jsonObject.get("Electricity").getAsString());
               /*
               *     处理数据
               *
               * */


                SharedPreferences preferences = context.getSharedPreferences("RealData", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("unitnumber", jsonObject.get("unitnumber").getAsString())
                        .putString("MonitoringInfo", jsonObject.get("MonitoringInfo").getAsString())
                        .putString("Fault", jsonObject.get("Fault").getAsString())
                        .putString("StandbyElectricity", jsonObject.get("StandbyElectricity").getAsString())
                        .putString("Electricity", jsonObject.get("Electricity").getAsString())
                        .apply();


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

    void handleData() {

        SharedPreferences preferences = context.getSharedPreferences("RealData", MODE_PRIVATE);
        String str = preferences.getString("MonitoringInfo", "error");
        char[] ch = str.toCharArray();
        for (int i = 0; i <ch.length ; i++) {
             System.out.println(ch[i]);
        }
//        char ch[] = new char[]{0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println("str"+str+ ch.toString() + "@@@@@@@@@@@@@@@@@@鸡巴鬼 啊  @@@@@@@@@@@@@@@@@@@@@" + "\n" );
        if ("0".equals(ch[2])) {
            is_lock_value.setText("正常");
            is_lock_value.setTextColor(Color.GREEN);
        } else {
            is_lock_value.setText("报警");
            is_lock_value.setTextColor(Color.RED);
        }
        if ("0".equals(ch[3])) {
            is_vibrate_value.setText("未震动");
            is_vibrate_value.setTextColor(Color.GREEN);
        } else {
            is_vibrate_value.setText("报警");
            is_vibrate_value.setTextColor(Color.RED);

        }
        if ("0".equals(ch[4])) {
            is_electrify_value.setText("掉电");
            is_electrify_value.setTextColor(Color.RED);
        } else {
            is_electrify_value.setText("在线");
            is_electrify_value.setTextColor(Color.GREEN);

        }
        if ("0".equals(ch[5])) {
            is_lookcar_value.setText("不开启");
            is_lookcar_value.setTextColor(Color.GREEN);

        } else {
            is_lookcar_value.setText("开启一次");
            is_lookcar_value.setTextColor(Color.RED);

        }
        if ("0".equals(ch[6])) {
            is_lockdoor_value.setText("断开");
            is_lockdoor_value.setTextColor(Color.RED);

        } else {
            is_lockdoor_value.setTextColor(Color.GREEN);
            is_lockdoor_value.setText("接通");
        }
        if ("0".equals(ch[7])) {
            contral_stat_value.setText("设防");
            contral_stat_value.setTextColor(Color.GREEN);

        } else {
            contral_stat_value.setTextColor(Color.RED);
            contral_stat_value.setText("解防");
        }
        StandbyElectricity_info_value.setText(preferences.getString("StandbyElectricity",null)+"V  ");
        Electricity_info_value.setText(preferences.getString("Electricity",null)+"V");


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
//        requestForRealData();

    }

}
