package com.psu.samuiapp.activity.category;

import com.psu.samuiapp.R;
import com.psu.samuiapp.fragment.category.SubCategoryFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class SubCatFragmentActivity extends FragmentActivity{
	 String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subcategory);
		
		Bundle b = getIntent().getExtras();
		url = b.getString("url");
		
		Fragment fragment = null;
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragment = new SubCategoryFragment();
		fragment.setArguments(b);
		fragmentManager.beginTransaction()
				.replace(R.id.fl_subcat_container, fragment).commit();
	}

}
