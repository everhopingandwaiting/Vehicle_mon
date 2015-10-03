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

import java.io.Serializable;

import util.ONHttpCallBack;
import util.net_util;

/**
 * Created by john on 15-9-30.
 */
public class failure_check extends Activity  implements Serializable {
    private TextView light_stat_value,
            turn_stat_value,
            voice_stat_value,
            battery_stat_value,
            break_stat_value,
            contral_stat_value_fc,
            power_driven_stat_value;
    private Button check_button;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.failure_check);
        initView();

     check_button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             requestForRealData();

             handleFailureData();
         }
     });
    }
    void requestForRealData() {

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

                context = failure_check.this;
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
                Toast.makeText(failure_check.this, "信息为空", Toast.LENGTH_SHORT).show();
            }
        });

    }
    void handleFailureData() {
        {
            context = failure_check.this;
            SharedPreferences preferences = context.getSharedPreferences("RealData", MODE_PRIVATE);
            String str = preferences.getString("Fault", "error");
            char[] ch = str.toCharArray();
            for (int i = 0; i <ch.length ; i++) {
                System.out.println(ch[i]);
            }
//        char ch[] = new char[]{0, 0, 0, 0, 0, 0, 0, 0};
            System.out.println("str"+str+ ch.toString() + "@@@@@@@@@@@@@@@@@@鸡巴鬼 啊  @@@@@@@@@@@@@@@@@@@@@" + "\n" );
            if ("0".equals(ch[1])) {
                light_stat_value.setText("正常");
                light_stat_value.setTextColor(Color.GREEN);

            } else {
                light_stat_value.setText("故障");
                light_stat_value.setTextColor(Color.RED);

            }
            if ("0".equals(ch[2])) {
                voice_stat_value.setText("正常");
                voice_stat_value.setTextColor(Color.GREEN);
            } else {
                voice_stat_value.setText("故障");
                voice_stat_value.setTextColor(Color.RED);
            }
            if ("0".equals(ch[3])) {
                break_stat_value.setText("正常");
                break_stat_value.setTextColor(Color.GREEN);
            } else {
                break_stat_value.setText("故障");
                break_stat_value.setTextColor(Color.RED);

            }
            if ("0".equals(ch[4])) {
                turn_stat_value.setText("正常");
                turn_stat_value.setTextColor(Color.GREEN);
            } else {
                turn_stat_value.setText("故障");
                turn_stat_value.setTextColor(Color.RED);

            }
            if ("0".equals(ch[5])) {
                contral_stat_value_fc.setText("正常");
                contral_stat_value_fc.setTextColor(Color.GREEN);

            } else {
                contral_stat_value_fc.setText("故障");
                contral_stat_value_fc.setTextColor(Color.RED);

            }
            if ("0".equals(ch[6])) {
                battery_stat_value.setText("正常");
                battery_stat_value.setTextColor(Color.GREEN);

            } else {
                battery_stat_value.setTextColor(Color.RED);
                battery_stat_value.setText("故障");
            }
            if ("0".equals(ch[7])) {
                power_driven_stat_value.setText("正常");
                power_driven_stat_value.setTextColor(Color.GREEN);

            } else {
                power_driven_stat_value.setTextColor(Color.RED);
                power_driven_stat_value.setText("故障");
            }



        }
    }

    void initView() {

        light_stat_value        = (TextView) findViewById(R.id.light_stat_value);
        turn_stat_value         = (TextView) findViewById(R.id.turn_stat_value);
        voice_stat_value        = (TextView) findViewById(R.id.voice_stat_value);
        battery_stat_value      = (TextView) findViewById(R.id.battery_stat_value);

        break_stat_value        = (TextView) findViewById(R.id.break_stat_value);

        contral_stat_value_fc           = (TextView) findViewById(R.id.contral_stat_value_fc);
        power_driven_stat_value     = (TextView) findViewById(R.id.power_driven_stat_value);
        check_button                = (Button) findViewById(R.id.check_button);

    }
}
