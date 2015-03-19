package com.psu.samuiapp.activity.map;

import java.util.List;

import org.w3c.dom.Document;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import app.akexorcist.gdaplibrary.GoogleDirection;
import app.akexorcist.gdaplibrary.GoogleDirection.OnDirectionResponseListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.gson.GsonBuilder;
import com.psu.samuiapp.R;
import com.psu.samuiapp.api.ApiService;
import com.psu.samuiapp.fragment.category.MainCategoryFragment.CategoryListAdapter;
import com.psu.samuiapp.fragment.category.MainCategoryFragment.CategoryListAdapter.ViewHolder;
import com.psu.samuiapp.model.CategoryModel;
import com.psu.samuiapp.model.PinDetailModel;

public class MapsFragmentActivity extends FragmentActivity {
	private GoogleMap mMap;
	private Marker mMarker;
	private LocationManager lm;
	private double lat, lng;
	private LatLng tempLatLng;
	private Marker tempMarker;
	private Polyline polyline;
	private OnInfoWindowElemTouchListener infoButtonListener;
	private int numOfPin;
	private PinDetailModel data;
	
	String Url = "http://kohsamui.ictte-project.com/android_connect";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_maps);
		init();
	}

	private void init() {

		final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		final MapWrapperLayout mapWrapperLayout = (MapWrapperLayout) findViewById(R.id.map_relative_layout);
		mMap = mapFragment.getMap();

		mMap.getUiSettings().setMapToolbarEnabled(false);

		mapWrapperLayout.init(mMap, getPixelsFromDp(this, 39 + 20));

		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
				new LatLng(Double.parseDouble("9.511370"), Double
						.parseDouble("99.995670")), 12.0f));

//		mMap.addMarker(new MarkerOptions().position(
//				new LatLng(9.560634, 100.023222)).title("Bo phut resort & spa"));
//
//		mMap.addMarker(new MarkerOptions().position(
//				new LatLng(9.451940, 99.937735)).title("Chaweng beach"));
//
//		mMap.addMarker(new MarkerOptions().position(
//				new LatLng(9.528131, 100.052576)).title(
//				"Elements Boutique Resort & Spa Hideaway Bay."));
//
//		mMap.addMarker(new MarkerOptions()
//				.position(new LatLng(9.45192, 100.04)).title("Hin ta & Hin yai."));
		try {
		GsonBuilder builder = new GsonBuilder();
		RestAdapter rest = new RestAdapter.Builder()
				.setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(Url)
				.setConverter(new GsonConverter(builder.create())).build();
		ApiService retrofit = rest.create(ApiService.class);

		retrofit.getPinDetailWithCallback(new Callback<List<PinDetailModel>>() {

			@Override
			public void success(List<PinDetailModel> pinDetail, Response respone) {
				List<PinDetailModel> pd = pinDetail;
				Log.d("List", "List data"+pd.size());
				
				
				for(numOfPin=0;numOfPin<pd.size();numOfPin++){ 
					data = pd.get(numOfPin);
					try {
						  mMap.addMarker(new MarkerOptions().position(new LatLng( data.getLatitude() 
								  , data.getLongitude()))
								  .title(data.getName()+"\n"+data.getAddress()+"\n"+data.getContact())); 
					} catch (Exception e) {
						e.printStackTrace();
						  mMap.addMarker(new MarkerOptions().position(new LatLng( 0 
								  , 0))
								  .title(data.getName()+"\n"+data.getAddress()+"\n"+data.getContact()));
					}
				}
				
				
				
				
			}

			@Override
			public void failure(RetrofitError error) {
			//	Toast.makeText(getActivity(), "error wa", Toast.LENGTH_LONG)
			//			.show();
				Log.d("List", "error "+error.getMessage());

			}
		});
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		final ViewGroup infoWindows = (ViewGroup) getLayoutInflater().inflate(
				R.layout.marker_info_dialog, null);

		final TextView infoTitle = (TextView) infoWindows
				.findViewById(R.id.tv_detail_title);
		final TextView infoSnippet = (TextView) infoWindows
				.findViewById(R.id.tv_detail_message);
		final Button infoButton = (Button) infoWindows
				.findViewById(R.id.rl_route);

		infoButtonListener = new OnInfoWindowElemTouchListener(infoButton,
				null, null) {

			@Override
			protected void onClickConfirmed(View v, Marker marker) {

				if (tempMarker != null && tempMarker.isVisible()) {
					GoogleDirection gd = new GoogleDirection(
							MapsFragmentActivity.this);
					gd.request(tempLatLng, marker.getPosition(),
							GoogleDirection.MODE_DRIVING);
					gd.setOnDirectionResponseListener(new OnDirectionResponseListener() {

						@Override
						public void onResponse(String status, Document doc,
								GoogleDirection gd) {
							if (polyline != null) {
								polyline.remove();
							}
							polyline = mMap.addPolyline(gd.getPolyline(doc, 3,
									Color.BLUE));
						}
					});
				}
				marker.hideInfoWindow();
			}
		};

		infoButton.setOnTouchListener(infoButtonListener);

		mMap.setInfoWindowAdapter(new InfoWindowAdapter() {
			@Override
			public View getInfoWindow(Marker marker) {
				return null;
			}

			@Override
			public View getInfoContents(Marker marker) {
				// Setting up the infoWindow with current's marker info
				infoTitle.setText(marker.getTitle());
				infoSnippet.setText(marker.getSnippet());
				infoButtonListener.setMarker(marker);

				// We must call this to set the current marker and infoWindow
				// references
				// to the MapWrapperLayout
				mapWrapperLayout.setMarkerWithInfoWindow(marker, infoWindows);
				return infoWindows;
			}
		});

		mMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				final double dlat = marker.getPosition().latitude;
				final double dlon = marker.getPosition().longitude;
				Log.d("latlng", "old : " + marker.getPosition().latitude + "/"
						+ marker.getPosition().longitude + "\n" + "new : "
						+ dlat + "/" + dlon);
				mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(
						dlat, dlon)));

				return false;
			}
		});

		mMap.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public void onMapClick(LatLng latLng) {
				tempLatLng = latLng;
				if (tempMarker != null)
					tempMarker.remove();
				if (polyline != null)
					polyline.remove();

				tempMarker = mMap.addMarker(new MarkerOptions()
						.position(latLng)
						.icon(BitmapDescriptorFactory
								.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

				tempMarker.setInfoWindowAnchor(1000f, 1000f);

				mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
			}
		});

	}

	// ////////// this class below use when u need to locate your current
	// location //////////////

	/*
	 * LocationListener listener = new LocationListener() { public void
	 * onLocationChanged(Location loc) { LatLng coordinate = new
	 * LatLng(loc.getLatitude(), loc.getLongitude()); lat = loc.getLatitude();
	 * lng = loc.getLongitude();
	 * 
	 * if (mMarker != null) mMarker.remove();
	 * 
	 * mMarker = mMap.addMarker(new MarkerOptions().position(new LatLng( lat,
	 * lng))); // mMap.animateCamera(CameraUpdateFactory //
	 * .newLatLngZoom(coordinate, 16)); }
	 * 
	 * public void onStatusChanged(String provider, int status, Bundle extras) {
	 * }
	 * 
	 * public void onProviderEnabled(String provider) { }
	 * 
	 * public void onProviderDisabled(String provider) { } };
	 */

	public void onResume() {
		super.onResume();

		/*
		 * lm = (LocationManager)
		 * mContext.getSystemService(Context.LOCATION_SERVICE);
		 * 
		 * boolean isNetwork = lm
		 * .isProviderEnabled(LocationManager.NETWORK_PROVIDER); boolean isGPS =
		 * lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
		 * 
		 * if (isNetwork) {
		 * lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10,
		 * listener); Location loc = lm
		 * .getLastKnownLocation(LocationManager.NETWORK_PROVIDER); if (loc !=
		 * null) { lat = loc.getLatitude(); lng = loc.getLongitude(); } }
		 * 
		 * if (isGPS) { lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
		 * 5000, 10, listener); Location loc = lm
		 * .getLastKnownLocation(LocationManager.GPS_PROVIDER); if (loc != null)
		 * { lat = loc.getLatitude(); lng = loc.getLongitude(); } }
		 */
	}

	public void onPause() {
		super.onPause();
		// lm.removeUpdates(listener);
	}

	public static int getPixelsFromDp(Context context, float dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}
	
	public class PinDetailListAdapter extends BaseAdapter {

		List<PinDetailModel> PinDetail;

		public PinDetailListAdapter(List<PinDetailModel> ct) {
			PinDetail = ct;
		}

		@Override
		public int getCount() {
			return PinDetail.size();
		}

		@Override
		public Object getItem(int position) {
			return PinDetail.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		private class ViewHolder {
			TextView Name;
			
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			LayoutInflater inflater = getLayoutInflater();

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.category_column,
						parent, false);
				holder = new ViewHolder();
				holder.Name = (TextView) convertView
						.findViewById(R.id.catName);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			PinDetailModel ct = PinDetail.get(position);
			holder.Name.setText("" + ct.getName());
			return convertView;
		}
	}

}
