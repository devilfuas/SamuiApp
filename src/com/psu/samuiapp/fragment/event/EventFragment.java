package com.psu.samuiapp.fragment.event;

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
import com.psu.samuiapp.activity.event.WebViewActivity;
import com.psu.samuiapp.api.ApiService;
import com.psu.samuiapp.fragment.category.SubCategoryFragment;
import com.psu.samuiapp.model.CategoryModel;
import com.psu.samuiapp.model.EventModel;
import com.psu.samuiapp.model.NameModel;

public class EventFragment extends Fragment {

	// private View v;
	EventModel ct;
	ListView categoryListView;

	String Url = "http://kohsamui.ictte-project.com/android_connect";

	public EventFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_event, container,
				false);
		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		categoryListView = (ListView) rootView.findViewById(R.id.lv_maincat);

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

			retrofit.getEventWithCallback(new Callback<List<EventModel>>() {

				@Override
				public void success(List<EventModel> category, Response respone) {
					List<EventModel> cm = category;
					Log.d("List", "List data");
					categoryListView.setAdapter(new EventListAdapter(cm));
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
					
						Intent in = new Intent(getActivity(), WebViewActivity.class);
						in.putExtra("url", ct.getLink());
						startActivity(in);
			
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public class EventListAdapter extends BaseAdapter {

		List<EventModel> Category;

		public EventListAdapter(List<EventModel> ct) {
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
			TextView date;
			TextView link;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			LayoutInflater inflater = getActivity().getLayoutInflater();

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.event_column,
						parent, false);
				holder = new ViewHolder();
				holder.catName = (TextView) convertView.findViewById(R.id.catName);
				holder.date = (TextView) convertView.findViewById(R.id.date);
				holder.link = (TextView) convertView.findViewById(R.id.link);
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			 ct = Category.get(position);
			holder.catName.setText("" + ct.getName());
			holder.date.setText("" + ct.getDate());
			holder.link.setText("" + ct.getLink());
			return convertView;
		}
	}

}
