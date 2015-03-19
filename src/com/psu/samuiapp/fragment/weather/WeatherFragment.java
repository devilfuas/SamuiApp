package com.psu.samuiapp.fragment.weather;

import zh.wang.android.apis.yweathergetter4a.WeatherInfo;
import zh.wang.android.apis.yweathergetter4a.WeatherInfo.ForecastInfo;
import zh.wang.android.apis.yweathergetter4a.YahooWeather;
import zh.wang.android.apis.yweathergetter4a.YahooWeather.SEARCH_MODE;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherExceptionListener;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherInfoListener;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.psu.samuiapp.R;

public class WeatherFragment extends Fragment implements
		YahooWeatherInfoListener, YahooWeatherExceptionListener {

	private String[] day = { " TODAY ===", "TOMORROW", "NEXT2DAYS",
			"NEXT3DAYS", "NEXT4DAYS" };
	private View v;
	private ImageView mIvWeather0;
	private TextView mTvWeather0;
	private TextView mTvErrorMessage;
	private TextView mTvTitle;
	private LinearLayout mWeatherInfosLayout;

	private YahooWeather mYahooWeather = YahooWeather.getInstance(5000, 5000,
			true);

	private ProgressDialog mProgressDialog;

	public WeatherFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		v = inflater.inflate(R.layout.fragment_weather, container, false);
		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Set
															// SCREEN_ORIENTATION_PORTRAIT
		setView();
		return v;
	}

	private void setView() {
		mYahooWeather.setExceptionListener(this);

		mProgressDialog = new ProgressDialog(getActivity());
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.show();

		mTvTitle = (TextView) v.findViewById(R.id.textview_title);
		mTvWeather0 = (TextView) v.findViewById(R.id.textview_weather_info_0);
		mTvErrorMessage = (TextView) v
				.findViewById(R.id.textview_error_message);
		mIvWeather0 = (ImageView) v.findViewById(R.id.imageview_weather_info_0);

		searchByPlaceName("samui");
		showProgressDialog();

		mWeatherInfosLayout = (LinearLayout) v.findViewById(R.id.weather_infos);

	}

	@Override
	public void onPause() {
		hideProgressDialog();
		mProgressDialog = null;
		super.onPause();
	}

	@Override
	public void gotWeatherInfo(WeatherInfo weatherInfo) {
		// TODO Auto-generated method stub
		hideProgressDialog();
		if (weatherInfo != null) {
			setNormalLayout();
			mWeatherInfosLayout.removeAllViews();
			mTvTitle.setText(//weatherInfo.getTitle()+ 
					"Koh Samui,"
					// + weatherInfo.getWOEIDneighborhood() + ", "
					// + weatherInfo.getWOEIDCounty() + ", "
					// + weatherInfo.getWOEIDState() + ", "
					+ weatherInfo.getWOEIDCountry() + "\n"
					+ weatherInfo.getCurrentConditionDate()+ "\n"
					+ weatherInfo.getCurrentText()+", "+weatherInfo.getCurrentTempC()+" í C");

			final LinearLayout forecastInfoLayout0 = (LinearLayout) getActivity()
					.getLayoutInflater().inflate(R.layout.forecastinfo, null);
			final TextView mTvWeather0 = (TextView) forecastInfoLayout0
					.findViewById(R.id.textview_forecast_info);

			mTvWeather0.setText("======= RIGHT NOW =======" 
					//+ "date: "+ weatherInfo.getCurrentConditionDate() + "\n"
					//+ "weather: " + weatherInfo.getCurrentText() + "\n"
					//+ "temperature in C: " + weatherInfo.getCurrentTempC()
					+ "\n" + "temperature in F: "
					+ weatherInfo.getCurrentTempF() + "\n"
					+ "wind chill in F: " + weatherInfo.getWindChill() + " m/h\n"
					+ "wind direction: " + weatherInfo.getWindDirection()+" í\n" 
					+ "wind speed: " + weatherInfo.getWindSpeed() + " km/h\n"
					+ "Humidity: " + weatherInfo.getAtmosphereHumidity() + " %RH\n"
					+ "Pressure: " + weatherInfo.getAtmospherePressure() + " N/m2\n"
					+ "Visibility: " + weatherInfo.getAtmosphereVisibility()+" km");
			if (weatherInfo.getCurrentConditionIcon() != null) {
				final ImageView mIvWeather0 = (ImageView) forecastInfoLayout0
						.findViewById(R.id.imageview_forecast_info);
				mIvWeather0.setImageBitmap(weatherInfo
						.getCurrentConditionIcon());

			}
			mWeatherInfosLayout.addView(forecastInfoLayout0);
			for (int i = 0; i < YahooWeather.FORECAST_INFO_MAX_SIZE; i++) {
				final LinearLayout forecastInfoLayout = (LinearLayout) getActivity()
						.getLayoutInflater().inflate(R.layout.forecastinfo,
								null);
				final TextView tvWeather = (TextView) forecastInfoLayout
						.findViewById(R.id.textview_forecast_info);
				final ForecastInfo forecastInfo = weatherInfo
						.getForecastInfoList().get(i);
				tvWeather.setText("=== FORECAST " + (day[i]) + "===" + "\n"
						+ "      " + forecastInfo.getForecastDate() + "\n"
						+ "     " + forecastInfo.getForecastText() + "\n"
						+ "low  temperature in C: "
						+ forecastInfo.getForecastTempLowC() + "\n"
						+ "high temperature in C: "
						+ forecastInfo.getForecastTempHighC() + "\n"
						+ "low  temperature in F: "
						+ forecastInfo.getForecastTempLowF() + "\n"
						+ "high temperature in íF: "
						+ forecastInfo.getForecastTempHighF() + "\n");
				final ImageView ivForecast = (ImageView) forecastInfoLayout
						.findViewById(R.id.imageview_forecast_info);
				if (forecastInfo.getForecastConditionIcon() != null) {
					ivForecast.setImageBitmap(forecastInfo
							.getForecastConditionIcon());
				}
				mWeatherInfosLayout.addView(forecastInfoLayout);
			}
		} else {
			setNoResultLayout();
		}
	}

	@Override
	public void onFailConnection(final Exception e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailParsing(final Exception e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailFindLocation(final Exception e) {
		// TODO Auto-generated method stub

	}

	private void setNormalLayout() {
		mWeatherInfosLayout.setVisibility(View.VISIBLE);
		mTvTitle.setVisibility(View.VISIBLE);
		mTvErrorMessage.setVisibility(View.INVISIBLE);
	}

	private void setNoResultLayout() {
		mTvTitle.setVisibility(View.INVISIBLE);
		mWeatherInfosLayout.setVisibility(View.INVISIBLE);
		mTvErrorMessage.setVisibility(View.VISIBLE);
		mTvErrorMessage.setText("Sorry, no result returned");
		mProgressDialog.cancel();
	}

	private void searchByGPS() {
		mYahooWeather.setNeedDownloadIcons(true);
		mYahooWeather.setSearchMode(SEARCH_MODE.GPS);
		mYahooWeather.queryYahooWeatherByGPS(getActivity(), this);
	}

	private void searchByPlaceName(String location) {
		mYahooWeather.setNeedDownloadIcons(true);
		mYahooWeather.setSearchMode(SEARCH_MODE.PLACE_NAME);
		mYahooWeather.queryYahooWeatherByPlaceName(getActivity(), location,
				this);
	}

	private void showProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.cancel();
		}
		mProgressDialog = new ProgressDialog(getActivity());
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.show();
	}

	private void hideProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.cancel();
		}
	}
}
