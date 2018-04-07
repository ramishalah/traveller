package agha.hacka.network;

import java.util.ArrayList;
import java.util.List;

import agha.hacka.ui.AddPost.AddPostPojo.AddPostRequest;
import agha.hacka.ui.AddPost.AddPostPojo.AddPostResponse;
import agha.hacka.ui.AddPost.AddPostPojo.PutImageResponse;
import agha.hacka.ui.AllPosts.AllPostsPOJO.PostPojo;
import agha.hacka.ui.Login.LoginPOJO.LoginPojo;
import agha.hacka.ui.Verify.VerifyPOJO.UserProfileResponse;
import agha.hacka.ui.Verify.VerifyPOJO.VerifyPojo;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


// look at the gradle file and look at the manifest for the permission
// Also, look at how the mvp works exactly

//hello
// In this interface you add the requests
public interface Api {


    @POST("/api/v1/users")
    @FormUrlEncoded
    Observable<LoginPojo> insertNumber(@Field("mobile") String number);

    @POST("/api/v1/auth")
    @FormUrlEncoded
    Observable<VerifyPojo> insertCode(@Field("mobile") String number, @Field("code") String code);

    @GET("/api/v1/posts?status_id=0&radius_km=5&metadata_key=ramiagha")
    Observable<ArrayList<PostPojo>> getPosts(@Header("Authorization") String token);


    @Multipart
    @POST("uploadAttachment")
    Observable<Void> uploadAttachment(@Part MultipartBody.Part filePart);

    @POST("/api/v1/posts")
    Observable<AddPostResponse> addPost(@Header("Authorization") String token, @Body AddPostRequest addPostRequest);

    @Multipart
    @PUT("/api/v1/posts/{post_id}")
    Observable<PutImageResponse> putImage(@Header("Authorization") String token, @Path("post_id") String id, @Part MultipartBody.Part filePart);

    @GET("/api/v1/users/{user_id}")
    Observable<UserProfileResponse> getUserProfile(@Header("Authorization") String token, @Path("user_id") String userId);

    @Multipart
    @PUT("/api/v1/users/{user_id}")
    Observable<UserProfileResponse> updateUserProfile(@Header("Authorization") String token, @Path("user_id") String userId, @Part MultipartBody.Part filePart, @Part("email") RequestBody emailPart, @Part("full_name") RequestBody fullName);


}
