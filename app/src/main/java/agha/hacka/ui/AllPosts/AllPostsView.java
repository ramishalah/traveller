package agha.hacka.ui.AllPosts;

import java.util.ArrayList;

import agha.hacka.ui.AllPosts.AllPostsPOJO.PostPojo;

public interface AllPostsView {

    public void onSuccess(ArrayList<PostPojo> list);
    public void onFail();
}
