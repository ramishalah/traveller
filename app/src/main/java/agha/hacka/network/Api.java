package agha.hacka.network;

import java.util.List;

import agha.hacka.pogo.FacultyResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;


// look at the gradle file and look at the manifest for the permission
// Also, look at how the mvp works exactly

//hello
// In this interface you add the requests
public interface Api {

    @GET("faculty")
    Observable<List<FacultyResponse>> getFaculty();
}
