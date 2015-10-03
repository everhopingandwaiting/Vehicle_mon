package com.example.john.vehicle_mon;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.john.global.Request_all_info;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 15-9-26.
 */
public class Remote_contral extends Activity{
    private ToggleButton                    lookcar_toggle_btn1,
            electricity_toggle_btn2,
            set_lock_toggle_btn3,
            hold_toggle_btn4;
    private TextView   StandbyElectricity_info_value_rc, Electricity_info_value_rc;
    private Context context;
             
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote_contral);
        context = getApplicationContext();
        initView();
        initTbnStatus();
        initClick();



    }

    private void initClick() {
        lookcar_toggle_btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setLOOKCAR(true);
                    Toast.makeText(context, "寻车不开启", Toast.LENGTH_SHORT
                    ).show();
                    initTbnStatus();
                } else {
                    setLOOKCAR(false);
                    Toast.makeText(context, "开启一次", Toast.LENGTH_SHORT
                    ).show();
                    initTbnStatus();
                }
            }

            private void setLOOKCAR(boolean b) {
                JsonObject object;
                if (b) {
                    object = orderList().get("XCBKQ");
                } else {
                    object = orderList().get("XCKQ");
                }
                Request_all_info.requestOrderCallback(getApplicationContext(), object);


            }
        });
           electricity_toggle_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if (isChecked&&set_lock_toggle_btn3.isChecked()) {
                       setELDoor(true);
                       Toast.makeText(context, "电门断开",  Toast.LENGTH_SHORT
                       ).show();
                       initTbnStatus();
                   }
                   else if(set_lock_toggle_btn3.isChecked()&&!isChecked) {
                       setELDoor(false);
                       Toast.makeText(context, "电门接通",  Toast.LENGTH_SHORT
                       ).show();
                       initTbnStatus();
                   }
               }

               private void setELDoor(boolean b) {
                   JsonObject object ;
                   if (b) {
                       object= orderList().get("DMDK");
                   } else {
                       object = orderList().get("DMJT");
                   }
                   Request_all_info.requestOrderCallback(getApplicationContext() , object);


               }
           });
        set_lock_toggle_btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setLOCK(true);
                    Toast.makeText(context, "设防开启",  Toast.LENGTH_SHORT
                    ).show();
                    initTbnStatus();
                } else {
                    setLOCK(false);
                    Toast.makeText(context, "设防解锁",  Toast.LENGTH_SHORT
                    ).show();
                    initTbnStatus();
                }
            }

            private void setLOCK(boolean b) {
                JsonObject object ;
                if (b) {
                    object= orderList().get("SF");
                } else {
                    object = orderList().get("JF");
                }
                Request_all_info.requestOrderCallback(getApplicationContext() , object);

            }
        });

    }

    void initTbnStatus() {
        Request_all_info.requestForRealData(this);
        SharedPreferences preferences = context.getSharedPreferences("RealData", MODE_PRIVATE);
         String str = preferences.getString("MonitoringInfo", "error");
      Electricity_info_value_rc.setText(preferences.getString("Electricity",null)+"V  ");
        StandbyElectricity_info_value_rc.setText(preferences.getString("StandbyElectricity",null)+"V");



  char[] ch = str.toCharArray();
        if ("1".equals(ch[5])) {
            lookcar_toggle_btn1.setChecked(true);

        } else {
            lookcar_toggle_btn1.setChecked(false);

        }
        if ("1".equals(ch[6])) {
            electricity_toggle_btn2.setChecked(true);
        } else {
            electricity_toggle_btn2.setChecked(false);

        }
        if ("0".equals(ch[7])) {
            set_lock_toggle_btn3.setChecked(true);

        } else {
            set_lock_toggle_btn3.setChecked(false);

        }


    }
    Map<String, JsonObject> orderList() {

        Map<String, JsonObject> jsonObjectMap = new HashMap<>();
        String action = "action";
        String unitnumber = "unitnumber";
        String listOrder[] = new String[]{"SF", "JF", "DMDK", "DMJT", "XCBKQ", "XCKQ", "DWXXBSB", "DWXXSB", "DWZQ0", "DWZQ1", "DWZQ2", "DWZQ3"
                , "DWZQ4", "DWZQ5", "DWZQ6", "DWZQ7"};
        SharedPreferences preferences = context.getSharedPreferences("RealData", MODE_PRIVATE);
        String num = preferences.getString("unitnumber", "error");
        for (int i = 0; i < listOrder.length; i++) {

            JsonObject object = new JsonObject();
            object.addProperty(action, listOrder[i]);
            object.addProperty(unitnumber, num);
            jsonObjectMap.put(listOrder[i], object);
        }

        return jsonObjectMap;
    }



    void initView() {
        lookcar_toggle_btn1                =(ToggleButton) findViewById(R.id.lookcar_toggle_btn1)                ;
                    electricity_toggle_btn2   =(ToggleButton) findViewById(R.id.electricity_toggle_btn2)            ;
                set_lock_toggle_btn3       =(ToggleButton) findViewById(R.id.set_lock_toggle_btn3)               ;
                hold_toggle_btn4           =(ToggleButton) findViewById(R.id.hold_toggle_btn4)                   ;
     StandbyElectricity_info_value_rc      =(TextView)     findViewById(R.id.StandbyElectricity_info_value_rc)   ;
             Electricity_info_value_rc           =(TextView)     findViewById(R.id.Electricity_info_value_rc)                ;


    }
}
