package agha.hacka.ui.Login;

import android.content.Context;
import android.util.Log;

import agha.hacka.ui.MainPage.MainPageModel;
import agha.hacka.ui.MainPage.MainPageView;


public class LoginPresenter {

    private Context mContext ;
    private LoginModel mLoginModel;
    private LoginView mLoginView;

    public LoginPresenter(Context context, LoginView loginView) {
        mContext = context;
        mLoginView = loginView;
        mLoginModel = new LoginModel();
    }


    public void insertNumber(String number) {
        // calling the request and then responsing with call back functions.
        mLoginModel.insertNumber(number)
                .subscribe(
                        successResponse -> {
                            mLoginView.onSuccess(successResponse);
                        },
                        error -> {
                            error.printStackTrace();
                            mLoginView.onFail();
                        }
                );
    }

}
