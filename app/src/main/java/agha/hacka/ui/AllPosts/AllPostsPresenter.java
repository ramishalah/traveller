package agha.hacka.ui.AllPosts;

import android.content.Context;

public class AllPostsPresenter {

    private Context context ;
    private AllPostsView mView ;
    private AllPostsModel mModel ;

    public AllPostsPresenter(Context c, AllPostsView view){
        mView = view ;
        context = c ;
        mModel = new AllPostsModel();
    }

    public void getPosts(String token){
        // calling the request and then responsing with call back functions.
        mModel.getPosts(token)
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
