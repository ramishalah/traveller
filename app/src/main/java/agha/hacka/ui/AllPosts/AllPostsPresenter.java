package agha.hacka.ui.AllPosts;

import android.content.Context;

import java.util.ArrayList;

import agha.hacka.ui.AllPosts.AllPostsPOJO.PostPojo;

public class AllPostsPresenter {

    private Context context ;
    private AllPostsView mView ;
    private AllPostsModel mModel ;
    private ArrayList<PostPojo> list ;

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
                            list = new ArrayList<>();
                            list.addAll(successResponse);
                            mView.onSuccess(successResponse);
                        },
                        error -> {
                            error.printStackTrace();
                            mView.onFail();
                        }
                );
    }

    public void filter(String item){
        if (item.equals("Default"))
            mView.onSuccess(list);
        else if (item.equals("Park")){
            ArrayList<PostPojo> copy = new ArrayList<>();
            for (PostPojo obj : list){
                if (obj.getMetadata().getChoice() != null) {
                    if (obj.getMetadata().getChoice().equals("Park"))
                        copy.add(obj);
                }
                else {
                    copy.add(obj);
                }
            } mView.onSuccess(copy);
        } else {
            ArrayList<PostPojo> copy = new ArrayList<>();
            for (PostPojo obj : list){
                if (obj.getMetadata().getChoice() != null) {
                    if (obj.getMetadata().getChoice().equals("Restaurant"))
                        copy.add(obj);
                }
                else {
                    copy.add(obj);
                }
            } mView.onSuccess(copy);
        }
    }



}
