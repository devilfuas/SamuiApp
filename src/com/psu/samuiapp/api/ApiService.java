package com.psu.samuiapp.api;

import java.util.List;

import com.psu.samuiapp.model.AttractionDetailModel;
import com.psu.samuiapp.model.CategoryModel;
import com.psu.samuiapp.model.EventModel;
import com.psu.samuiapp.model.NameModel;
import com.psu.samuiapp.model.PinDetailModel;
import com.psu.samuiapp.model.PlacenameModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiService {

	@GET("/get_category.php")
	void getCategoryWithCallback(Callback<List<CategoryModel>> calback);
	
	@GET("/get_place_name.php")
	void getPlaceNameByIdWithCallback(@Query("category_id")int i,Callback<List<PlacenameModel>> calback);
	
	
	
	@GET("/get_attraction_details.php")
	void getAttractionDetailByIdWithCallback(@Query("category_id")int category_id
			, @Query("detail_id")int detail_id
			, Callback<List<AttractionDetailModel>> callback);
	
//	@GET("/get_attraction_details.php")
//	void getAttractionDetailByIdWithCallback(@Query("name")String name
//			, Callback<List<AttractionDetailModel>> callback);
	
	@GET("/get_name.php")
	void getNameWithCallback(@Query("name")String name, Callback<List<NameModel>> calback);
	
	
	
	@GET("/get_event.php")
	void getEventWithCallback(Callback<List<EventModel>> calback);
	
	
	
	@GET("/get_pin_detail.php")
	void getPinDetailWithCallback(Callback<List<PinDetailModel>> calback);
	
	
	
	
	
}
