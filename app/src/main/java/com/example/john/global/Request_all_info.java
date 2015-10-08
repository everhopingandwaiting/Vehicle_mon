package com.example.john.global;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonObject;

import java.io.Serializable;

import util.ONHttpCallBack;
import util.net_util;

/**
 * Created by john on 15-10-3.
 */
public class Request_all_info implements Serializable {
  public   static void requestForRealData( final Context context) {
        JsonObject object = new JsonObject();
        object.addProperty("action", "rtdata");

      object.addProperty("unitnumber", context.getSharedPreferences("RealData", Context.MODE_PRIVATE).getString("unitnumber", null));
        net_util.goFor(net_util.RealTimeDataBl, new ONHttpCallBack() {
            @Override
            public void onHttpCallBack(JsonObject jsonObject) {
                if (jsonObject == null) {

                    return;
                }
                SharedPreferences preferences = context.getSharedPreferences("RealData", Context.MODE_PRIVATE);
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

    public static void requestOrderCallback(final Context context, final JsonObject object) {
                 net_util.goFor(net_util.order_info, new ONHttpCallBack() {
                     @Override
                     public void onHttpCallBack(JsonObject jsonObject) {
                     context.getSharedPreferences("OrderStatus", Context.MODE_PRIVATE).edit().putString("state",jsonObject.get("state").getAsString()).apply();

                     }
                 },object);
    }
}
