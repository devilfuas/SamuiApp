package com.psu.samuiapp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.ProfilePictureView;
import com.psu.samuiapp.R;

public class FacebookFragment  extends Fragment {

	private View v;
	TextView userName;
	LoginButton loginBtn;
	ProfilePictureView profile;
	Button updateStatusBtn, updateImage;
	static EditText editText1;
	static String msg;
	private UiLifecycleHelper uiHelper;

	private static final List<String> PERMISSIONS = Arrays
			.asList("publish_actions");

	private static String message = "samuiapp";
	
	public FacebookFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		v = inflater.inflate(R.layout.fragment_facebook, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	//Set SCREEN_ORIENTATION_PORTRAIT   
	    setView(savedInstanceState);
        return v;
    }
	
	private void setView(Bundle savedInstanceState) {
		uiHelper = new UiLifecycleHelper(getActivity(), statusCallback);
		uiHelper.onCreate(savedInstanceState);
		showHashKey(getActivity());

		userName = (TextView) v.findViewById(R.id.user_name);
		profile = (ProfilePictureView) v.findViewById(R.id.profile);
		loginBtn = (LoginButton) v.findViewById(R.id.authButton);
		updateStatusBtn = (Button) v.findViewById(R.id.update_status);
		updateImage = (Button) v.findViewById(R.id.post_image);
		editText1 = (EditText) v.findViewById(R.id.editText1);

		updateStatusBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				postStatusMessage();

			}
		});

		updateImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				postImage();
			}
		});

		buttonsEnabled(true);
		loginBtn.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
			@Override
			public void onUserInfoFetched(GraphUser user) {
				if (user != null) {
					userName.setText("Hello, " + user.getName());
					profile.setProfileId(user.getId());
				} else {
					userName.setText("You are not logged");
					profile.setProfileId(null);
				}
			}
		});
	}

	public static void showHashKey(Context context) {
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					"com.psu.samuiapp", PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.i("KeyHash:",
						Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (PackageManager.NameNotFoundException e) {
			Log.i("KeyHash:", "error");
		} catch (NoSuchAlgorithmException e) {
			Log.i("KeyHash:", "error");
		}
	}

	private Session.StatusCallback statusCallback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			if (state.isOpened()) {
				Log.d("FacebookSampleActivity", "Facebook session opened");
			} else if (state.isClosed()) {
				Log.d("FacebookSampleActivity", "Facebook session closed");
			}
		}
	};

	public String getStringFromEdittext() {
		msg = editText1.getText().toString()+"\n" + "http://kohsamui.ictte-project.com/";
		return msg;
	}

	public void postStatusMessage() {
		message = "" + getStringFromEdittext();
		if (checkPermissions()) {
			Request request = Request.newStatusUpdateRequest(
					Session.getActiveSession(), message,
					new Request.Callback() {
						@Override
						public void onCompleted(Response response) {
							if (response.getError() == null)
								Toast.makeText(
										getActivity(),
										"Status updated successfully",
										Toast.LENGTH_LONG).show();
						}
					});
			request.executeAsync();
		} else {
			requestPermissions();
		}
	}

	public void postImage() {

		if (checkPermissions()) {
			Bitmap img = BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_launcher);
			Request uploadRequest = Request.newUploadPhotoRequest(
					Session.getActiveSession(), img, new Request.Callback() {
						@Override
						public void onCompleted(Response response) {
							Toast.makeText(getActivity(),
									"Photo uploaded successfully",
									Toast.LENGTH_LONG).show();
						}
					});
			uploadRequest.executeAsync();

		} else {
			requestPermissions();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onSaveInstanceState(Bundle savedState) {
		super.onSaveInstanceState(savedState);
		uiHelper.onSaveInstanceState(savedState);
	}

	public boolean checkPermissions() {
		Session s = Session.getActiveSession();
		if (s != null) {
			return s.getPermissions().contains("publish_actions");
		} else
			return false;
	}

	public void requestPermissions() {
		Session s = Session.getActiveSession();
		if (s != null)
			s.requestNewPublishPermissions(new Session.NewPermissionsRequest(
					getActivity(), PERMISSIONS));
	}

	public void buttonsEnabled(boolean isEnabled) {
		updateStatusBtn.setEnabled(isEnabled);
		updateImage.setEnabled(isEnabled);
	}
	
	

}
