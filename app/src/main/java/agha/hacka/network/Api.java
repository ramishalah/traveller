package agha.hacka.network;

import java.util.ArrayList;
import java.util.List;

import agha.hacka.pogo.FacultyResponse;
import agha.hacka.ui.AllPosts.AllPostsPOJO.PostPojo;
import agha.hacka.ui.Login.LoginPOJO.LoginPojo;
import agha.hacka.ui.Verify.VerifyPOJO.VerifyPojo;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


// look at the gradle file and look at the manifest for the permission
// Also, look at how the mvp works exactly

//hello
// In this interface you add the requests
public interface Api {

    @GET("faculty")
    Observable<List<FacultyResponse>> getFaculty();


    @POST("/api/v1/users")
    @FormUrlEncoded
    Observable<LoginPojo> insertNumber(@Field("mobile") String number);

    @POST("/api/v1/auth")
    @FormUrlEncoded
    Observable<VerifyPojo> insertCode(@Field("mobile") String number , @Field("code") String code);

    @GET("/api/v1/posts?status_id=0&radius_km=5&metadata_key=ramiagha")
    Observable<ArrayList<PostPojo>> getPosts(@Header("Authorization") String token);

}
