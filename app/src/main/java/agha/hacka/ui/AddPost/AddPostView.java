package agha.hacka.ui.AddPost;

import agha.hacka.ui.AddPost.AddPostPojo.AddPostRequest;
import agha.hacka.ui.AddPost.AddPostPojo.AddPostResponse;
import agha.hacka.ui.AddPost.AddPostPojo.PutImageResponse;

public interface AddPostView {
    public void onSuccess(AddPostResponse addPostResponse);
    public void onFail();

    public void onSuccessPuttingImage(PutImageResponse putImageResponse);
    public void onFailPuttingImage();
}
