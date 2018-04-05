package agha.hacka.ui.MainPage;

import java.util.List;

import agha.hacka.network.Api;
import agha.hacka.network.RetrofitServiceFactory;
import agha.hacka.pogo.FacultyResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.Observable;

public class MainPageModel {


    // setting up the observation
    public Observable<List<FacultyResponse>> getFaculty() {
        Api apiService = new RetrofitServiceFactory().buildApiService();
        return apiService
                .getFaculty()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
