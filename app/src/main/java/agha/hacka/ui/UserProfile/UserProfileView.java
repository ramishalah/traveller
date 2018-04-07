package agha.hacka.ui.UserProfile;

import agha.hacka.ui.Verify.VerifyPOJO.UserProfileResponse;

public interface UserProfileView {
    public void onSuccess(UserProfileResponse userProfileResponse);
    public void onFail();
}
