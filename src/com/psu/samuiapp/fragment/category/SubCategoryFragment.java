package com.psu.samuiapp.fragment.category;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

import android.R.string;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.psu.samuiapp.R;
import com.psu.samuiapp.activity.category.SubCatActivity;
import com.psu.samuiapp.activity.category.SubCatFragmentActivity;
import com.psu.samuiapp.activity.category.SubCategoryDetailActivity;
import com.psu.samuiapp.api.ApiService;
import com.psu.samuiapp.fragment.category.MainCategoryFragment.CategoryListAdapter;
import com.psu.samuiapp.fragment.category.MainCategoryFragment.NameListAdapter;
import com.psu.samuiapp.fragment.category.MainCategoryFragment.NameListAdapter.ViewHolder;
import com.psu.samuiapp.model.CategoryModel;
import com.psu.samuiapp.model.NameModel;
import com.psu.samuiapp.model.PlacenameModel;

public class SubCategoryFragment extends Fragment {

	Button btn;
	private View v;
	private int index = 0;
	EditText edittext;
	private ListView categoryListView;
	String Url = "http://kohsamui.ictte-project.com/android_connect";

	public SubCategoryFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		v = inflater.inflate(R.layout.fragment_category, container, false);
		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 

		index = getArguments().getInt("index");
		categoryListView = (ListView) v.findViewById(R.id.lv_maincat);
	/*	edittext = (EditText)v.findViewById(R.id.et_search);
		btn = (Button)v.findViewById(R.id.btn_search);
		
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
			}
		}); */
		
		setView();
		return v;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setView() {
		
		try {
			GsonBuilder builder = new GsonBuilder();
			RestAdapter rest = new RestAdapter.Builder()
					.setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(Url)
					.setConverter(new GsonConverter(builder.create())).build();
			ApiService retrofit = rest.create(ApiService.class);

			retrofit.getPlaceNameByIdWithCallback(index,new Callback<List<PlacenameModel>>() {

				@Override
				public void success(List<PlacenameModel> category, Response respone) {
					categoryListView.setAdapter(new CategoryListAdapter(category));
				}

				@Override
				public void failure(RetrofitError error) {
					Log.d("List", "error");

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		categoryListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View v,
					int position, long id) {

				
				Intent in = new Intent(getActivity(), SubCategoryDetailActivity.class);
				in.putExtra("category_id", index);
				in.putExtra("detail_id", position+1);
				
		

				Log.d("door",""+categoryListView.getItemAtPosition(position));
				startActivity(in);
			}
		});

	}
	
	public class CategoryListAdapter extends BaseAdapter {

		List<PlacenameModel> Category = new List<PlacenameModel>() {
			
			@Override
			public <T> T[] toArray(T[] array) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object[] toArray() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<PlacenameModel> subList(int start, int end) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public PlacenameModel set(int location, PlacenameModel object) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean retainAll(Collection<?> collection) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean removeAll(Collection<?> collection) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean remove(Object object) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public PlacenameModel remove(int location) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ListIterator<PlacenameModel> listIterator(int location) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ListIterator<PlacenameModel> listIterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int lastIndexOf(Object object) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Iterator<PlacenameModel> iterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public int indexOf(Object object) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public PlacenameModel get(int location) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsAll(Collection<?> collection) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean contains(Object object) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean addAll(int location,
					Collection<? extends PlacenameModel> collection) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean addAll(Collection<? extends PlacenameModel> collection) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void add(int location, PlacenameModel object) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean add(PlacenameModel object) {
				// TODO Auto-generated method stub
				return false;
			}
		};

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
			PlacenameModel ct = Category.get(position);
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
