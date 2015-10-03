package com.example.john.vehicle_mon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.io.Serializable;

public class MainSlideActivity extends Activity  implements Serializable
{
	private SlidingMenu mMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.silde_main);
//		mMenu = (SlidingMenu) findViewById(R.id.id_menu);
		TextView location_vehicle = (TextView) findViewById(R.id.location_vehicle);

		location_vehicle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainSlideActivity.this, Map_activity.class);
				startActivity(intent);
			}
		});

		TextView remote_contral = (TextView) findViewById(R.id.remote_contral);
		remote_contral.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainSlideActivity.this, Remote_contral.class);
				startActivity(intent);

			}
		});
		TextView failure_checck_ = (TextView) findViewById(R.id.failure_check_);
		failure_checck_.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainSlideActivity.this, failure_check.class);
				startActivity(intent);
			}
		});
		TextView person_info_ = (TextView) findViewById(R.id.person_info_view);
		person_info_.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainSlideActivity.this, person_info.class);
				startActivity(intent);
			}
		});
		TextView vehicle_info_view = (TextView) findViewById(R.id.vehicle_info_view);
		vehicle_info_view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainSlideActivity.this, vehicle_info.class);
				startActivity(intent);

			}
		});
		TextView vehicle_realdata = (TextView) findViewById(R.id.vehicle_realdata);
		vehicle_realdata.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainSlideActivity.this, RealData_info.class);
				startActivity(intent);

			}
		});
	}

	public void toggleMenu(View view)
	{
		mMenu.toggle();
	}
}
