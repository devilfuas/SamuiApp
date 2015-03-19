package com.psu.samuiapp.rss;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class RssFeedAsyncTask extends
		AsyncTask<String, Void, List<RssFeedStructure>> {
	private ProgressDialog Dialog;
	String response = "";
	List<RssFeedStructure> rssStr;
	Context _context;
	private AsyncTaskCompletionListener callback;

	public RssFeedAsyncTask(Context _context, AsyncTaskCompletionListener listener) {
		this._context = _context;
		this.callback = (AsyncTaskCompletionListener) listener;
	}

	@Override
	protected void onPreExecute() {
		Dialog = new ProgressDialog(_context);
		Dialog.setMessage("Loading...");
		Dialog.show();

	}

	@Override
	protected List<RssFeedStructure> doInBackground(String... urls) {
		try {
			String feed = urls[0];
			XmlHandler rh = new XmlHandler();
			rssStr = rh.getLatestArticles(feed);
		} catch (Exception e) {
		}
		return rssStr;

	}

	@Override
	protected void onPostExecute(List<RssFeedStructure> result) {
		Dialog.dismiss();
		callback.onTaskComplete(result);

	}

}
