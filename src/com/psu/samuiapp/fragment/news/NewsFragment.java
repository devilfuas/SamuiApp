package com.psu.samuiapp.fragment.news;

import java.util.List;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.psu.samuiapp.R;
import com.psu.samuiapp.adapter.NewsAdapter;
import com.psu.samuiapp.rss.AsyncTaskCompletionListener;
import com.psu.samuiapp.rss.RssFeedAsyncTask;
import com.psu.samuiapp.rss.RssFeedStructure;
import com.psu.samuiapp.utils.Utility;

public class NewsFragment extends Fragment implements AsyncTaskCompletionListener{

	private View v;
	private ListView newsListView;
	
	public NewsFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		v = inflater.inflate(R.layout.fragment_news, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	//Set SCREEN_ORIENTATION_PORTRAIT   
	    setView();
        return v;
    }
	
	private void setView() {
		newsListView = (ListView)v.findViewById(R.id.lv_news);

		if(Utility.determineConnectivity(getActivity()))
		new RssFeedAsyncTask(getActivity(), this).execute(Utility.url);
	}

	@Override
	public void onTaskComplete(List<RssFeedStructure> result) {
		NewsAdapter _adapter= new NewsAdapter(getActivity(), result);
		newsListView.setAdapter(_adapter);
	} 
}
