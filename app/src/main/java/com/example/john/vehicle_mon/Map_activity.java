package com.example.john.vehicle_mon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.baidu.lbsapi.BMapManager;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.nplatform.comapi.map.MapController;

/**
 * Created by john on 15-9-10.
 */
public class Map_activity extends Activity {

    private MapView mv = null;
    private Toast toast;
    private BMapManager bMapManager;
    private MyLocationData myLocationData;

   // private LocationOverlay locationOverlay;
    private MapController mapController = null;
    private BaiduMap baiduMap=null;

    boolean isFirstLoc = true;
  private    LocationClient locationClient = null;
    private MyLocationConfiguration.LocationMode locationMode;
    private BitmapDescriptor bitmapDescriptor;

    private Button requestButton;
    public MyLocationListemer myListener = new MyLocationListemer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.map_activity);



        requestButton = (Button) findViewById(R.id.button1);
        locationMode = MyLocationConfiguration.LocationMode.NORMAL;
        requestButton.setText("Normal view");
         requestButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 switch (locationMode) {
                     case NORMAL:
                         requestButton.setText("Follow me!hha ");
                         locationMode = MyLocationConfiguration.LocationMode.FOLLOWING;
                         baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(locationMode, true, bitmapDescriptor));
                         break;
                     case COMPASS:
                         requestButton.setText("Normal !");
                         locationMode = MyLocationConfiguration.LocationMode.NORMAL;
                         baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(locationMode, true, bitmapDescriptor));
                         break;
                     case FOLLOWING:
                         requestButton.setText("Compass ...");
                         locationMode= MyLocationConfiguration.LocationMode.COMPASS;
                         baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(locationMode, true, bitmapDescriptor));
                         break;

                 }


             }
         });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.defaulticon) {
                    bitmapDescriptor = null;
                    baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(locationMode, true, null));
                }
                if (checkedId == R.id.customicon) {
                    bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.rpi1);
                    baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(locationMode, true, bitmapDescriptor));

                }
            }
        });
        mv = (MapView) findViewById(R.id.location_vehicle);
        mv.showScaleControl(true);

        baiduMap = mv.getMap();
        baiduMap.setMyLocationEnabled(true);//允许定位图层
        //initiliase
        locationClient = new LocationClient(this);
        locationClient.registerLocationListener(myListener);
        //register
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setOpenGps(true);
        locationClientOption.setCoorType("bd09ll");

        locationClientOption.setScanSpan(3000);
        locationClient.setLocOption(locationClientOption);

        locationClient.start();


    }

    public class MyLocationListemer implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null || mv == null) {
                return;

            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(0)
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());// get 经纬度
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
                baiduMap.animateMapStatus(mapStatusUpdate);
                // 以上设置新地图位置
            }
        }

        public void onReceivePoi(BDLocation bdLocation) {

        }
    }

    private void showPopupOverrlay(BDLocation location) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mv.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mv.onPause();
    }

    @Override
    protected void onDestroy() {
        locationClient.stop();
        baiduMap.setMyLocationEnabled(false);


        mv.onDestroy();
        mv = null;
        super.onDestroy();

    }
}
