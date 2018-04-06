package agha.hacka.ui.Login;

import java.util.List;

import agha.hacka.network.Api;
import agha.hacka.network.RetrofitServiceFactory;
import agha.hacka.pogo.FacultyResponse;
import agha.hacka.ui.Login.LoginPOJO.LoginPojo;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel {

    // setting up the observation
    public Observable<LoginPojo> insertNumber(String number) {
        Api apiService = new RetrofitServiceFactory().buildApiService();
        return apiService
                .insertNumber(number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
