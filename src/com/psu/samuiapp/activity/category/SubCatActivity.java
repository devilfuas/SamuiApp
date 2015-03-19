package com.psu.samuiapp.activity.category;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.psu.samuiapp.R;
import com.psu.samuiapp.api.ApiService;
import com.psu.samuiapp.fragment.category.SubCategoryFragment;
import com.psu.samuiapp.model.CategoryModel;
import com.psu.samuiapp.model.PlacenameModel;

public class SubCatActivity extends FragmentActivity {
	ListView supcategoryListView;
	View rootView;
	String Url = "http://kohsamui.ictte-project.com/android_connect";
	String id;
	Bundle extras;
	String newString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_subcat);
		
		 GsonBuilder builder = new GsonBuilder();
			RestAdapter rest = new RestAdapter.Builder()
						.setLogLevel(RestAdapter.LogLevel.FULL)
						.setEndpoint(Url)
						.setConverter(new GsonConverter(builder.create()))
						.build();
			ApiService retrofit = rest.create(ApiService.class);
			 extras = getIntent().getExtras();
			 id= extras.getString("index");
			retrofit.getPlaceNameByIdWithCallback(Integer.parseInt(id), new Callback<List<PlacenameModel>>() {
				
				@Override
				public void success(List<PlacenameModel> subcategory, Response respone) {
					// TODO Auto-generated method stub
					List<PlacenameModel> cm = subcategory;
					Log.d("List","List data");
					supcategoryListView.setAdapter(new CategoryListAdapter(cm));
				}
				
				@Override
				public void failure(RetrofitError error) {
					Toast.makeText(SubCatActivity.this, "error wa", Toast.LENGTH_LONG).show();
					
				}
			});
			
			supcategoryListView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
					Intent in = new Intent(SubCatActivity.this, SubCatActivity.class);
					in.putExtra("index", position);
					startActivity(in);
				}
			});
		
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		Fragment nextStep;
		nextStep = new SubCategoryFragment();
		nextStep.setArguments(getIntent().getExtras());
		fragmentTransaction.replace(R.id.fl_subcat_container, nextStep);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}
	public class CategoryListAdapter extends BaseAdapter {

        List<PlacenameModel> Category;
        public CategoryListAdapter(List<PlacenameModel> ct) {
            Category = ct;
        }
        @Override
        public int getCount() {
            return Category.size();
        }

        @Override
        public Object getItem(int position) {
            return Category.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            TextView catName;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder  holder;
            LayoutInflater inflater = getLayoutInflater();

            if(convertView == null){
                convertView = inflater.inflate(R.layout.category_column, parent,false);
                holder = new ViewHolder();
                holder.catName=(TextView)convertView.findViewById(R.id.catName);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }
            PlacenameModel ct = Category.get(position);
            holder.catName.setText("" + ct.getName());
            return convertView;
        }
    }
}
