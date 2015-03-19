package com.psu.samuiapp.activity.category;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import android.R.string;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.GsonBuilder;
import com.psu.samuiapp.R;
import com.psu.samuiapp.activity.map.MapsFragmentActivity;
import com.psu.samuiapp.api.ApiService;
import com.psu.samuiapp.fragment.category.MainCategoryFragment;
import com.psu.samuiapp.fragment.home.HomeFragment;
import com.psu.samuiapp.fragment.news.NewsFragment;
import com.psu.samuiapp.fragment.weather.WeatherFragment;
import com.psu.samuiapp.model.AttractionDetailModel;
import com.psu.samuiapp.slidingmenu.PagesFragment;
import com.squareup.picasso.Picasso;

public class SubCategoryDetailActivity extends Activity {
	
	Button buttonIntent;
	
	float lon;
	float lat;

	private ImageView image;
	private TextView title;
	private TextView detail;
	private TextView address;
	private TextView openingtimes;
	private TextView servicecharge;
	private TextView contact;

	private String Url = "http://kohsamui.ictte-project.com/android_connect";
	private int category_id = 1;
	private int detail_id = 1;
	private String name;
	private AttractionDetailModel data;
	private String baseImgLink = "http://kohsamui.ictte-project.com/uploads/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_category_detail);
		setView();
	}

	private void setView() {
		
		  

		image = (ImageView) findViewById(R.id.imv_detail);
		title = (TextView) findViewById(R.id.tv_title);
		detail = (TextView) findViewById(R.id.tv_detail);
		address = (TextView) findViewById(R.id.tv_address);
		openingtimes = (TextView) findViewById(R.id.tv_openingtimes);
		servicecharge = (TextView) findViewById(R.id.tv_servicecharge);
		contact = (TextView) findViewById(R.id.tv_contact);

		Bundle b = getIntent().getExtras();
		category_id = b.getInt("category_id");
		detail_id = b.getInt("detail_id");
		// name = b.getString("name");
		Log.d("category_id", "category_id:" + category_id + " detail_id:"
				+ detail_id);

		GsonBuilder builder = new GsonBuilder();
		RestAdapter rest = new RestAdapter.Builder()
				.setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(Url)
				.setConverter(new GsonConverter(builder.create())).build();
		ApiService retrofit = rest.create(ApiService.class);

		retrofit.getAttractionDetailByIdWithCallback(category_id,detail_id,
				new Callback<List<AttractionDetailModel>>() {

					@Override
					public void success(List<AttractionDetailModel> category,
							Response respone) {
						Log.d("category", "" + category);
						data = category.get(0);
						Picasso.with(SubCategoryDetailActivity.this)
								.load(baseImgLink + data.getPicture())
								.into(image);

						title.setText(data.getName());
						detail.setText(data.getDetail());
						if (data.getAddress() != null) {
							address.setText(data.getAddress());
						}
						if (data.getOpeningTime() != null) {
							openingtimes.setText(data.getOpeningTime());
						}
						if (data.getServiceCharge() != null) {
							servicecharge.setText(data.getServiceCharge());
						}
						if (data.getContact() != null) {
							contact.setText(data.getContact());
						}
						  lat = data.getLatitude();
						  lon =data.getLongitude();

					}

					@Override
					public void failure(RetrofitError error) {
						Toast.makeText(SubCategoryDetailActivity.this,
								"error wa", Toast.LENGTH_LONG).show();
						Log.d("List", "List data");

					}
				});
		
		buttonIntent = (Button)findViewById(R.id.buttonIntent);
        buttonIntent.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("geo:"+lat+","+lon+"?z=18");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
               
                startActivity(Intent.createChooser(intent, "View map with"));
                
            }
        });
	}

}
