package agha.hacka.ui.AddPost;

import java.io.File;
import java.util.List;

import agha.hacka.network.Api;
import agha.hacka.network.RetrofitServiceFactory;
import agha.hacka.pogo.FacultyResponse;
import agha.hacka.ui.AddPost.AddPostPojo.AddPostRequest;
import agha.hacka.ui.AddPost.AddPostPojo.AddPostResponse;
import agha.hacka.ui.AddPost.AddPostPojo.PutImageResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class AddPostModel {

    // setting up the observation
    public Observable<AddPostResponse> addPost(String token, AddPostRequest addPostRequest) {
        Api apiService = new RetrofitServiceFactory().buildApiService();
        return apiService
                .addPost(token, addPostRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    // setting up the observation
    public Observable<PutImageResponse> putImage(String token, String postId, MultipartBody.Part file) {
        Api apiService = new RetrofitServiceFactory().buildApiService();
        return apiService
                .putImage(token, postId, file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
