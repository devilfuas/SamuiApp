package com.psu.samuiapp.slidingmenu;



import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import com.psu.samuiapp.R;

public class CategoryFragment extends Fragment /*implements OnClickListener*/{
	
	View view;
	ImageButton imgbtn;
	
	// ImageButton imgbtn = (ImageButton)findViewById(R.id.imgbtn);
	
	public CategoryFragment(){}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
/*		imgbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),Page_Beach.class);
				
			}
		});             */
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        /*  setView();  */
        return rootView;
        
        
     
   /*     String[] str = { "Beach", "Waterfall", "Temple", "Adventure", "Market",
				"View Point", "Other" };

		ListView listView1 = (ListView) findViewById(R.id.listView1);
		listView1.setAdapter(new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, str));
		listView1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent;
				switch (arg2) {
				case 0:// beach
					intent = new Intent(getApplicationContext(),
							Page_Beach.class);
					startActivity(intent);
					break;
				case 1:// waterfall
					intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
					break;
				case 2:// temple
					intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
					break;
				case 3:// adventure
					intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
					break;
				case 4:// market
					intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
					break;
				case 5:// view point
					intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
					break;
				case 6:// other
					intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
					break;
				}

			}
		});*/
    }

	/*private void setView() {
		// TODO Auto-generated method stub
		 imgbtn = (ImageButton)view.findViewById(R.id.imgbtn);
		 imgbtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(imgbtn.equals(v))
		{
			Intent myIntent = new Intent(getActivity(), Page_Beach.class);
            startActivity(myIntent);
		}
	}*/
	   
	
}
		
