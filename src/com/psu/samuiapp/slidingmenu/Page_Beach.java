package com.psu.samuiapp.slidingmenu;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Intent;
import com.psu.samuiapp.R;
import com.psu.samuiapp.activity.category.MainActivity;

public class Page_Beach extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.page_beach);
		String[] str = { "Chaweng Beach", 
				"Bo Phut Beach","Lipa Noi Beach",
				"Choengmon Beach","Taling Ngam Beach",
				"Thong Krut Beach","Bang Kao Beach",
				"Hua Thanon Beach","Lamai Beach",
				"Bang Rak Beach","Laem Sor Beach",
				"Laem Yai Beach","Leam Set Beach",
				"Plai Leam Beach"};

		ListView listView1 = (ListView) findViewById(R.id.listView1);
		listView1.setAdapter(new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, str));
		listView1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent;
				switch (arg2) {
				case 0:// Chaweng
					intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
					break;
/*				case 1:// Bo phut
					intent = new Intent(getApplicationContext(),
							Designlayout.class);
					startActivity(intent);
					break;
				case 2:// Bo phut
					intent = new Intent(getApplicationContext(),
							Test.class);
					startActivity(intent);
					break;                                    */
				}

			}
		});
	}
}