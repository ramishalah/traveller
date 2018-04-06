package agha.hacka.network;

import java.util.List;

import agha.hacka.pogo.FacultyResponse;
import agha.hacka.ui.Login.LoginPOJO.LoginPojo;
import agha.hacka.ui.Verify.VerifyPOJO.VerifyPojo;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


// look at the gradle file and look at the manifest for the permission
// Also, look at how the mvp works exactly

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
}
