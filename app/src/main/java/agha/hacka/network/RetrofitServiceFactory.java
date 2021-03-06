package agha.hacka.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceFactory {
    // Base URL
    String ENDPOINT = "https://elmhackhub.com";

    // returning api
    public Api buildApiService() {
        return buildRetrofit().create(Api.class);
    }

    // creating the retrofit instance
    public Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
