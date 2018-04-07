package agha.hacka.ui.AddPost;

import android.content.Context;
import android.util.Log;

import agha.hacka.ui.AddPost.AddPostPojo.AddPostRequest;
import okhttp3.MultipartBody;

public class AddPostPresenter {
    private static final String TAG = "AddPostPresenter";

    private AddPostModel mAddPostModel;
    private AddPostView mAddPostView;

    public AddPostPresenter(AddPostView addPostView) {
        mAddPostView = addPostView;
        mAddPostModel = new AddPostModel();
        //
    }
    public void addPost (AddPostRequest addPostRequest, String token) {
        // calling the request and then responsing with call back functions.
        mAddPostModel.addPost(token, addPostRequest)
                .subscribe(
                        successResponse -> {
                            Log.d(TAG, "addPost: The request has been done successfully! and the data is: " + successResponse);
                            mAddPostView.onSuccess(successResponse);
                        },
                        error -> {
                            Log.d(TAG, "addPost: There is an error!" + error.getMessage());
                            error.printStackTrace();
                            mAddPostView.onFail();
                        }
                );
    }

    public void putImage (String token, String postId, MultipartBody.Part file) {
        // calling the request and then responsing with call back functions.
        mAddPostModel.putImage(token, postId, file)
                .subscribe(
                        successResponse -> {
                            Log.d(TAG, "putImage: The request has been done successfully! and the data is: " + successResponse);
                            mAddPostView.onSuccessPuttingImage(successResponse);
                        },
                        error -> {
                            Log.d(TAG, "putImage: There is an error!" + error.getMessage());
                            error.printStackTrace();
                            mAddPostView.onFailPuttingImage();
                        }
                );
    }
}
