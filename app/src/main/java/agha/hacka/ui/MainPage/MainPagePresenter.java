package agha.hacka.ui.MainPage;

import android.content.Context;
import android.util.Log;

public class MainPagePresenter {
    private static final String TAG = "MainPagePresenter";

    private Context mContext;
    private MainPageModel mMainPageModel;
    private MainPageView mMainPageView;

    public MainPagePresenter(Context context, MainPageView mainPageView) {
        mContext = context;
        mMainPageView = mainPageView;
        mMainPageModel = new MainPageModel();
        //
    }
    public void getFaculty () {
        // calling the request and then responsing with call back functions.
        mMainPageModel.getFaculty()
                .subscribe(
                        successResponse -> {
                            Log.d(TAG, "getFaculty: The request has been done successfully! and the data is: " + successResponse);
                            mMainPageView.onSuccess(successResponse);
                        },
                        error -> {
                            Log.d(TAG, "getFaculty: There is an error!" + error.getMessage());
                            error.printStackTrace();
                            mMainPageView.onFail();

                        }
                );
    }
}
