package com.psu.samuiapp.fragment.category;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.gson.GsonBuilder;
import com.psu.samuiapp.R;
import com.psu.samuiapp.activity.category.SubCatActivity;
import com.psu.samuiapp.activity.category.SubCatFragmentActivity;
import com.psu.samuiapp.api.ApiService;
import com.psu.samuiapp.model.CategoryModel;
import com.psu.samuiapp.model.NameModel;

public class MainCategoryFragment extends Fragment {

	// private View v;
	ListView categoryListView;
	EditText edittext;
	Button btn;
	String Url = "http://kohsamui.ictte-project.com/android_connect";

	public MainCategoryFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_maincategory, container,false);
		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
		
		categoryListView = (ListView) rootView.findViewById(R.id.lv_maincat);
	/*	edittext = (EditText)rootView.findViewById(R.id.et_search);
		btn = (Button)rootView.findViewById(R.id.btn_search);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					GsonBuilder builder = new GsonBuilder();
					RestAdapter rest = new RestAdapter.Builder()
							.setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(Url)
							.setConverter(new GsonConverter(builder.create())).build();
					ApiService retrofit = rest.create(ApiService.class);

					retrofit.getNameWithCallback(edittext.getText().toString(), new Callback<List<NameModel>>() {

						@Override
						public void success(List<NameModel> category, Response respone) {
							List<NameModel> cm = category;
							Log.d("List", "List data");
							categoryListView.setAdapter(new NameListAdapter(cm));
						}

						@Override
						public void failure(RetrofitError error) {
							Toast.makeText(getActivity(), "error wa "+error.getMessage(), Toast.LENGTH_LONG)
									.show();
							Log.d("check_fail", ""+error.getMessage());

						}
					});
				} catch (Exception e) {
					e.getStackTrace();
				}
				

				categoryListView.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> adapter, View v,
							int position, long id) {
						Intent in = new Intent(getActivity(), SubCatFragmentActivity.class);
						in.putExtra("index", position+1);
			
						startActivity(in);
					}
				}); 
			}
		}); */

/*		GsonBuilder builder = new GsonBuilder();
		RestAdapter rest = new RestAdapter.Builder()
				.setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(Url)
				.setConverter(new GsonConverter(builder.create())).build();
		ApiService retrofit = rest.create(ApiService.class);

		retrofit.getCategoryWithCallback(new Callback<List<CategoryModel>>() {

			@Override
			public void success(List<CategoryModel> category, Response respone) {
				List<CategoryModel> cm = category;
				Log.d("List", "List data");
				categoryListView.setAdapter(new CategoryListAdapter(cm));
			}

			@Override
			public void failure(RetrofitError error) {
				Toast.makeText(getActivity(), "error wa", Toast.LENGTH_LONG)
						.show();
				Log.d("List", "List data");

			}
		});

		categoryListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View v,
					int position, long id) {
				Intent in = new Intent(getActivity(), SubCatActivity.class);
				in.putExtra("index", position);
				startActivity(in);
			}
		});   */
		
		setView();
		return rootView;
	}

	private void setView() {
		try {
			
			GsonBuilder builder = new GsonBuilder();
			RestAdapter rest = new RestAdapter.Builder()
					.setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(Url)
					.setConverter(new GsonConverter(builder.create())).build();
			ApiService retrofit = rest.create(ApiService.class);

			retrofit.getCategoryWithCallback(new Callback<List<CategoryModel>>() {

				@Override
				public void success(List<CategoryModel> category, Response respone) {
					List<CategoryModel> cm = category;
					Log.d("List", "List data");
					categoryListView.setAdapter(new CategoryListAdapter(cm));
				}

				@Override
				public void failure(RetrofitError error) {
					Toast.makeText(getActivity(), "error wa", Toast.LENGTH_LONG)
							.show();
					Log.d("List", "List data");

				}
			});

			categoryListView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> adapter, View v,
						int position, long id) {
					Intent in = new Intent(getActivity(), SubCatFragmentActivity.class);
					in.putExtra("index", position+1);
		
					startActivity(in);
				}
			}); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	public class CategoryListAdapter extends BaseAdapter {

		List<CategoryModel> Category;

		public CategoryListAdapter(List<CategoryModel> ct) {
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

		public class ViewHolder {
			TextView catName;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			LayoutInflater inflater = getActivity().getLayoutInflater();

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.category_column,
						parent, false);
				holder = new ViewHolder();
				holder.catName = (TextView) convertView
						.findViewById(R.id.catName);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			CategoryModel ct = Category.get(position);
			holder.catName.setText("" + ct.getName());
			return convertView;
		}
	}
	
	public class NameListAdapter extends BaseAdapter {

		List<NameModel> Category;

		public NameListAdapter(List<NameModel> ct) {
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

		public class ViewHolder {
			TextView catName;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			LayoutInflater inflater = getActivity().getLayoutInflater();

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.category_column,
						parent, false);
				holder = new ViewHolder();
				holder.catName = (TextView) convertView
						.findViewById(R.id.catName);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			NameModel ct = Category.get(position);
			holder.catName.setText("" + ct.getName());
			return convertView;
		}
	}
}
