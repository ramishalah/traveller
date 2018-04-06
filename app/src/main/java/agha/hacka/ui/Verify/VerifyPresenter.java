package agha.hacka.ui.Verify;

import android.content.Context;

public class VerifyPresenter {

    //private Context context ;
    private VerifyView mView ;
    private VerifyModel mModel ;

    public VerifyPresenter(Context context , VerifyView mView){
        //this.context = context;
        this.mView = mView;
        mModel = new VerifyModel();
    }

    public void insertCode(String number, String code) {
        // calling the request and then responsing with call back functions.
        mModel.insertNumber(number, code)
                .subscribe(
                        successResponse -> {
                            mView.onSuccess(successResponse);
                        },
                        error -> {
                            error.printStackTrace();
                            mView.onFail();
                        }
                );
    }



}
