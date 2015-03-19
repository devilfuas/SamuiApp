package com.psu.samuiapp.fragment.home;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

import com.psu.samuiapp.R;

public class HomeFragment extends Fragment {
	TextView tv;
	TextView tv2;
	View rootView;
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
   
         rootView = inflater.inflate(R.layout.fragment_home, container, false);
        setView();
      
         
        return rootView;
    }

	private void setView() {
		// TODO Auto-generated method stub
		 tv =(TextView) rootView.findViewById(R.id.txtLabel2);
		 tv.setText("    Ko Samui is the premier island destination in the Gulf of Thailand. Samui is easily accessible, features beautiful beaches and a variety of activities, and caters to visitors on any budget.Ko Samui, Thailand’s second most popular island destination, is located in the Gulf of Thailand roughly 700 km. south of Bangkok and 80 km. from Thailand’s southern coast. Samui is the third largest island in Thailand and the largest in an archipelago of more than 80 islands that includes the Ang Thong National Marine Park, a kayaking paradise and day trip from Ko Samui. While Samui is small enough to be circumnavigated in just a couple of hours by motorbike or car, the island features such a variety of beaches and activities that it would be impossible to experience everything in a single visit. However, this was not always the case. Until the late 20th century, Samui was home to a small community engaged primarily in fishing and harvesting coconuts. There were not even any roads on the island until the early 1970’s. However, once foreign visitors discovered this island gem, lush with tropical forest, fringed with palm tree lined stretches of golden sand, and surrounded by pellucid, aquamarine water, development quickly followed. Today the beaches of Chaweng and Lamai are bustling beach towns with fabulous beach resorts, internationally acclaimed restaurants, and world-class nightclubs. Activities around Ko Samui include cooking courses, yoga instruction, Muay Thai training, scuba diving, and even golf. While there are a few quieter beaches that are ideal for relaxation, particularly those that feature some of the finest 5-star resorts in the world, and some that exude old world charm, such as Bo Phut, which features converted, old Chinese shop houses, Samui is a lively, exciting place than it was a few decades ago. Ko Samui has developed into its own style of island paradise, retaining much of its natural beauty while offering nearly every imaginable activity or service for the ultimate beach holiday.");
	}
}
