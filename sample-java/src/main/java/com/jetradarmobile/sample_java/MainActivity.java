package com.jetradarmobile.sample_java;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jetradarmobile.sociallogin.SocialLogin;
import com.jetradarmobile.sociallogin.SocialLoginCallback;
import com.jetradarmobile.sociallogin.SocialNetwork;
import com.jetradarmobile.sociallogin.SocialToken;
import com.jetradarmobile.sociallogin_facebook.FacebookNetwork;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements
		View.OnClickListener,
		SocialLoginCallback {

	private TextView info;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		info = (TextView) findViewById(R.id.info);

		Button facebookLoginButton = (Button) findViewById(R.id.facebookButton);
		facebookLoginButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.facebookButton:
				SocialLogin.Factory.with(this).loginTo(new FacebookNetwork(), this);
				break;
			default:
				break;
		}
	}

	@Override
	public void onLoginSuccess(@NotNull SocialNetwork socialNetwork,
	                           @NotNull SocialToken socialToken) {
		displayLoginInfo(socialToken);
	}

	@Override
	public void onLoginError(@NotNull SocialNetwork socialNetwork,
	                         @NotNull String errorMessage) {
		displayError(errorMessage);
	}

	private void displayLoginInfo(SocialToken token) {
		String info = "token = " + token.getToken() + "\n\n" +
				"user id = " + token.getUserId() + "\n\n" +
				"user name = " + token.getUserName() + "\n\n";

		this.info.setText(info);
	}

	private void displayError(String error) {
		info.setText(error);
	}
}
