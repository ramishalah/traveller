package agha.hacka.ui.Verify;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import agha.hacka.R;
import agha.hacka.ui.AllPosts.AllPosts;
import agha.hacka.ui.UserProfile.UserProfile;
import agha.hacka.ui.UserProfile.UserSingleton;
import agha.hacka.ui.Verify.VerifyPOJO.UserProfileResponse;
import agha.hacka.ui.Verify.VerifyPOJO.VerifyPojo;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyActivity extends AppCompatActivity implements VerifyView{
    private static final String TAG = "VerifyActivity";

    @BindView(R.id.fab)
    FloatingActionButton fab ;

    @BindView(R.id.verify_number)
    EditText number ;

    private VerifyPresenter presenter;
    private Intent i ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        ButterKnife.bind(this);

        // init presenter
        presenter = new VerifyPresenter(this,this);

        fab.setOnClickListener(view -> fabClicked());
    }

    @Override
    public void onSuccess(VerifyPojo obj) {
        Log.e("LOGIN STATUS", obj.getAccessToken().toString().trim());
        String token = obj.getAccessToken().toString().trim();
        String userId = obj.getUser().getUserId().toString().trim();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("TOKEN", token).apply();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("USER_ID", userId).apply();

        Log.d(TAG, "onSuccess: TOkEN is: " + token);
        Log.d(TAG, "onSuccess: user id is: " + userId);
        // Getting the user information after verifying the code
        presenter.getUserProfile("bearer " + token, userId);

        Log.e("LOGIN STATUS", obj.getAccessToken().toString().trim());
        Intent i = new Intent(this, AllPosts.class);
        startActivity(i);
    }

    @Override
    public void onFail() {
        number.setError("Incorrect Code !!");
    }

    @Override
    public void fabClicked() {
        i = getIntent();
        String phone = i.getExtras().getString("PHONE_NUMBER");
        Log.i("PHONE_NUMBER",String.valueOf(phone));
        presenter.insertCode(phone,number.getText().toString().trim());
    }

    @Override
    public void onSuccessGettingUserProfile(UserProfileResponse userProfileResponse) {
        Log.d(TAG, "onSuccessGettingUserProfile: successful getting the user profile");

        // Initializing the singleton with the information provided
        UserSingleton user = UserSingleton.getOurInstance();
        user.setEmail(userProfileResponse.getEmail());
        user.setFullName(userProfileResponse.getFullName());
        user.setUrl(userProfileResponse.getUrl());
        user.setUserId(userProfileResponse.getUserId());
        user.setMobile(userProfileResponse.getMobile());

    }

    @Override
    public void onFailGettingUserProfile() {
        Log.d(TAG, "onFailGettingUserProfile: error in getting user profile");
    }
}
