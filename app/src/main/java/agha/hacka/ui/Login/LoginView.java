package agha.hacka.ui.Login;

import java.util.List;

import agha.hacka.ui.Login.LoginPOJO.LoginPojo;

public interface LoginView {

    public void onFail();
    public void onSuccess(LoginPojo obj);
    public void fabClicked();
}
