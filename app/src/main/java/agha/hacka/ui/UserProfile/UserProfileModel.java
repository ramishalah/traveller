package agha.hacka.ui.UserProfile;

import java.util.ArrayList;

import agha.hacka.network.Api;
import agha.hacka.network.RetrofitServiceFactory;
import agha.hacka.ui.AllPosts.AllPostsPOJO.PostPojo;
import agha.hacka.ui.Verify.VerifyPOJO.UserProfileResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserProfileModel {
    // setting up the observation
    public Observable<UserProfileResponse> updateUserProfile(String token, String userId, MultipartBody.Part filePart, RequestBody emailPart, RequestBody fullName) {
        Api apiService = new RetrofitServiceFactory().buildApiService();
        return apiService
                .updateUserProfile(token, userId, filePart, emailPart, fullName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
