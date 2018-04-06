package agha.hacka.ui.AllPosts;

import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import agha.hacka.R;
import agha.hacka.ui.AllPosts.AllPostsPOJO.PostPojo;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AllPosts extends AppCompatActivity implements AllPostsView{

    @BindView(R.id.toolbar)
    Toolbar toolbar ;

    @BindView(R.id.rv)
    RecyclerView rv ;

    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout ;

    private RecyclerViewAdapter adapter ;
    private AllPostsPresenter presenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_posts);
        ButterKnife.bind(this);
        // init presenter
        presenter = new AllPostsPresenter(this,this);
        // call
        presenter.getPosts(getToken());
        // init toolbar
        toolbar.setTitle("All Posts");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        // swipe
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() { swipe(); swipeRefreshLayout.setRefreshing(false); }});

        Log.e("token",getToken());

    }

    @Override
    public void onSuccess(ArrayList<PostPojo> list) {
        adapter = new RecyclerViewAdapter(this,list);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }

    @Override
    public void onFail() {
        Toast.makeText(this,"Failed to download", Toast.LENGTH_SHORT).show();
    }

    public String getToken(){
        return "bearer " + PreferenceManager.getDefaultSharedPreferences(this).getString("TOKEN","DEFAULT");
    }

    public void swipe(){
        presenter.getPosts(getToken());
    }
}
