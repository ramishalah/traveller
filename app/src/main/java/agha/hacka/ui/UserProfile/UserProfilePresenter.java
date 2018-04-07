package agha.hacka.ui.UserProfile;

import android.util.Log;

import agha.hacka.ui.AddPost.AddPostModel;
import agha.hacka.ui.AddPost.AddPostView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserProfilePresenter {
    private static final String TAG = "UserProfilePresenter";

    private UserProfileView mUserProfileView;
    private UserProfileModel mUserProfileModel;

    public UserProfilePresenter(UserProfileView userProfileView) {
        mUserProfileView = userProfileView;
        mUserProfileModel = new UserProfileModel();
        //
    }

    public void updateUserProfile(String token, String userId, MultipartBody.Part filePart, RequestBody emailPart, RequestBody fullName) {

        mUserProfileModel.updateUserProfile(token, userId, filePart, emailPart, fullName)
                .subscribe(
                        successResponse -> {
                            Log.d(TAG, "updateUserProfile: user profile updated successfully");
                            mUserProfileView.onSuccess(successResponse);
                        },
                        error -> {
                            Log.d(TAG, "updateUserProfile: error in updating user profile");
                            error.printStackTrace();
                            mUserProfileView.onFail();
                        }
                );
    }
}
