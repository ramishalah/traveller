package agha.hacka.ui.Verify;

import agha.hacka.ui.Verify.VerifyPOJO.VerifyPojo;

public interface VerifyView {

    public void onSuccess(VerifyPojo obj);
    public void onFail();
    public void fabClicked();

}
