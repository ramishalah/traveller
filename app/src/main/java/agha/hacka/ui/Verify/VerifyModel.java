package agha.hacka.ui.Verify;

import agha.hacka.network.Api;
import agha.hacka.network.RetrofitServiceFactory;
import agha.hacka.ui.Login.LoginPOJO.LoginPojo;
import agha.hacka.ui.Verify.VerifyPOJO.UserProfileResponse;
import agha.hacka.ui.Verify.VerifyPOJO.VerifyPojo;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VerifyModel {

    // setting up the observation
    public Observable<VerifyPojo> insertNumber(String number, String code) {
        Api apiService = new RetrofitServiceFactory().buildApiService();
        return apiService
                .insertCode(number, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    // setting up the observation
    public Observable<UserProfileResponse> getUserProfile(String token, String userId) {
        Api apiService = new RetrofitServiceFactory().buildApiService();
        return apiService
                .getUserProfile(token, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
