package com.example.john.vehicle_mon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainSlideActivity extends Activity
{
	private SlidingMenu mMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.silde_main);
		mMenu = (SlidingMenu) findViewById(R.id.id_menu);

	}

	public void toggleMenu(View view)
	{
		mMenu.toggle();
	}
}
