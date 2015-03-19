package com.psu.samuiapp.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.psu.samuiapp.R;
import com.psu.samuiapp.rss.RssFeedStructure;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.StrictMode;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class NewsAdapter extends ArrayAdapter<RssFeedStructure> {
	List<RssFeedStructure> imageAndTexts1 = null;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	public NewsAdapter(Activity activity,
			List<RssFeedStructure> imageAndTexts) {
		super(activity, 0, imageAndTexts);
		imageAndTexts1 = imageAndTexts;
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Activity activity = (Activity) getContext();
		LayoutInflater inflater = activity.getLayoutInflater();

		View rowView = inflater.inflate(R.layout.rssfeedadapter_layout, null);
		TextView textView = (TextView) rowView.findViewById(R.id.feed_text);
		TextView timeFeedText = (TextView) rowView.findViewById(R.id.feed_updatetime);
		TextView feed_author = (TextView) rowView.findViewById(R.id.feed_author);
		TextView feed_category = (TextView) rowView.findViewById(R.id.feed_category);
		TextView feed_link = (TextView) rowView.findViewById(R.id.feed_link);
		TextView feed_description = (TextView) rowView.findViewById(R.id.feed_description);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.feed_image);
		try {

			Log.d("rssfeed", "imageAndTexts1.get(position).getImgLink() :: "
					+ imageAndTexts1.get(position).getImgLink() + " :: "
					+ imageAndTexts1.get(position).getDescription());
			textView.setText(imageAndTexts1.get(position).getTitle());
			
			// do 4 lines
			//feed_description.setText"door"+(imageAndTexts1.get(position).getDescription());
			feed_author.setText("By: "+imageAndTexts1.get(position).getAuthor());
			feed_category.setText(""+imageAndTexts1.get(position).getCategory());
			feed_link.setText("More: "+imageAndTexts1.get(position).getLink());
			
			SpannableString content = new SpannableString(imageAndTexts1.get(
					position).getPubDate());
			content.setSpan(new UnderlineSpan(), 0, 13, 0);
			
			timeFeedText.setText(content);
			if (imageAndTexts1.get(position).getImgLink() != null) {

				URL feedImage = new URL(imageAndTexts1.get(position)
						.getImgLink().toString());
				if (!feedImage.toString().equalsIgnoreCase("null")) {
					HttpURLConnection conn = (HttpURLConnection) feedImage
							.openConnection();
					InputStream is = conn.getInputStream();
					Bitmap img = BitmapFactory.decodeStream(is);
					imageView.setImageBitmap(img);
				} else {
					imageView.setBackgroundResource(R.drawable.default_empty_rss);
				}
			}

		} catch (MalformedURLException e) {

		} catch (IOException e) {

		}

		return rowView;

	}

}