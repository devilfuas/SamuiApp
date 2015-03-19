package com.psu.samuiapp.rss;

import java.util.List;

public interface AsyncTaskCompletionListener {
	public void onTaskComplete(List<RssFeedStructure> result);
}
