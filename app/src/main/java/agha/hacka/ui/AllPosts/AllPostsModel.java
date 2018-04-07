package agha.hacka.ui.AllPosts;

import android.content.Context;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import agha.hacka.network.Api;
import agha.hacka.network.RetrofitServiceFactory;
import agha.hacka.ui.AllPosts.AllPostsPOJO.PostPojo;
import agha.hacka.ui.Login.LoginPOJO.LoginPojo;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AllPostsModel {

    // setting up the observation
    public Observable<ArrayList<PostPojo>> getPosts(String token) {
        Api apiService = new RetrofitServiceFactory().buildApiService();
        return apiService
                .getPosts(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
