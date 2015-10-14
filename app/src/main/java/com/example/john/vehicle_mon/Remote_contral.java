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

import com.example.john.global.Request_all_info;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 15-9-26.
 */
public class Remote_contral extends Activity implements Serializable{
    private Button                    lookcar_toggle_btn1,
            electricity_toggle_btn2,
            set_lock_toggle_btn3,
            hold_toggle_btn4,  lookcar_toggle_btn12,
            electricity_toggle_btn22, set_lock_toggle_btn32, hold_toggle_btn42;



    private TextView StandbyElectricity_info_value_rc, Electricity_info_value_rc;
    private Context context;
  private    setOrder order;
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
        order = new setOrder();
        lookcar_toggle_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setLOOKCAR(true);
                Toast.makeText(context, "寻车不开启", Toast.LENGTH_SHORT
                ).show();
                initTbnStatus();
            }

        });
        lookcar_toggle_btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setLOOKCAR(false);
                Toast.makeText(context, "开启一次", Toast.LENGTH_SHORT
                ).show();
                initTbnStatus();
            }
        });
      electricity_toggle_btn2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              order.setELDoor(true);
              Toast.makeText(context, "电门断开",  Toast.LENGTH_SHORT
              ).show();
              initTbnStatus();
          }
      });
        electricity_toggle_btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setELDoor(false);
                Toast.makeText(context, "电门接通", Toast.LENGTH_SHORT
                ).show();
                initTbnStatus();
            }
        });
        set_lock_toggle_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setLOCK(true);
                Toast.makeText(context, "设防开启", Toast.LENGTH_SHORT
                ).show();
                initTbnStatus();
            }
        });
        set_lock_toggle_btn32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setLOCK(false);
                Toast.makeText(context, "设防解锁", Toast.LENGTH_SHORT
                ).show();
                initTbnStatus();
            }
        });
    }



    void initTbnStatus() {
        Request_all_info.requestForRealData(context);
        SharedPreferences preferences = context.getSharedPreferences("RealData", MODE_PRIVATE);
         String str = preferences.getString("MonitoringInfo", "errorhhh");
        System.out.println(str+"!@#$%((*&^^$@#%R&T^$&^%^*$^$#^*%&^&*(&(*)*&(*^&*$%^");
      Electricity_info_value_rc.setText(preferences.getString("Electricity",null)+"V  ");
        StandbyElectricity_info_value_rc.setText(preferences.getString("StandbyElectricity",null)+"V");



  char[] ch = str.toCharArray();
        if ('1'==(ch[5])) {
            lookcar_toggle_btn1.setEnabled(true);
            lookcar_toggle_btn1.setTextColor(Color.GREEN);
            lookcar_toggle_btn12.setEnabled(false);
            lookcar_toggle_btn12.setTextColor(Color.RED);

        } else if ('0'==ch[5]){
            lookcar_toggle_btn1.setEnabled(false);
            lookcar_toggle_btn1.setTextColor(Color.RED);
            lookcar_toggle_btn12.setEnabled(true);
            lookcar_toggle_btn12.setTextColor(Color.GREEN);

        }
        if ('1'==(ch[6])) {
            electricity_toggle_btn2.setEnabled(true);
            electricity_toggle_btn2.setTextColor(Color.GREEN);
            electricity_toggle_btn22.setEnabled(false);
            electricity_toggle_btn22.setTextColor(Color.RED);
        } else if ('0'==ch[6]){
            electricity_toggle_btn2.setEnabled(false);
            electricity_toggle_btn2.setTextColor(Color.RED);
            electricity_toggle_btn22.setEnabled(true);
            electricity_toggle_btn22.setTextColor(Color.GREEN);

        }
        if ('1'==(ch[7])) {
            set_lock_toggle_btn3.setEnabled(true);
            set_lock_toggle_btn3.setTextColor(Color.GREEN);
            set_lock_toggle_btn32.setEnabled(false);
            set_lock_toggle_btn32.setTextColor(Color.RED);

        } else if ('0'==ch[7]){
            set_lock_toggle_btn3.setEnabled(false);
            set_lock_toggle_btn3.setTextColor(Color.RED);
            set_lock_toggle_btn32.setEnabled(true);
            set_lock_toggle_btn32.setTextColor(Color.GREEN);

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
        lookcar_toggle_btn1                 =(Button) findViewById(R.id.lookcar_toggle_btn1)                ;
          electricity_toggle_btn2           =(Button) findViewById(R.id.electricity_toggle_btn2)            ;
                set_lock_toggle_btn3        =(Button) findViewById(R.id.set_lock_toggle_btn3)               ;
                hold_toggle_btn4            =(Button) findViewById(R.id.hold_toggle_btn4)                   ;
     StandbyElectricity_info_value_rc       =(TextView)     findViewById(R.id.StandbyElectricity_info_value_rc)   ;
             Electricity_info_value_rc      =(TextView)     findViewById(R.id.Electricity_info_value_rc)          ;
                  lookcar_toggle_btn12                 =(Button)findViewById(R.id.lookcar_toggle_btn12)                ;
                    electricity_toggle_btn22           =(Button)findViewById(R.id.electricity_toggle_btn22);
        set_lock_toggle_btn32                   =(Button)findViewById(R.id.set_lock_toggle_btn32)  ;
        hold_toggle_btn42                    =(Button)findViewById(R.id.hold_toggle_btn42)                   ;
    }
    private  class  setOrder{
        private synchronized void setLOOKCAR(boolean b) {
            JsonObject object;
            if (b) {
                object = orderList().get("XCBKQ");
            } else {
                object = orderList().get("XCKQ");
            }
            Request_all_info.requestOrderCallback(getApplicationContext(), object);


        }
        private synchronized void setELDoor(boolean b) {
            JsonObject object ;
            if (b) {
                object= orderList().get("DMDK");
            } else {
                object = orderList().get("DMJT");
            }
            Request_all_info.requestOrderCallback(getApplicationContext() , object);


        }
        private synchronized void setLOCK(boolean b) {
            JsonObject object ;
            if (b) {
                object= orderList().get("SF");
            } else {
                object = orderList().get("JF");
            }
            Request_all_info.requestOrderCallback(getApplicationContext() , object);

        }
    }
}
