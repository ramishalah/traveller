package agha.hacka.ui.AllPosts;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

import agha.hacka.R;
import agha.hacka.ui.AddPost.AddPost;
import agha.hacka.ui.AllPosts.AllPostsPOJO.PostPojo;
import agha.hacka.ui.UserProfile.UserProfile;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AllPosts extends AppCompatActivity implements AllPostsView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private RecyclerViewAdapter adapter;
    private AllPostsPresenter presenter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_posts);
        ButterKnife.bind(this);
        // init presenter
        presenter = new AllPostsPresenter(this, this);
        // call
        presenter.getPosts(getToken());
        // init toolbar
        toolbar.setTitle("All Posts");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        // swipe
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipe();
            swipeRefreshLayout.setRefreshing(false);
        });

        Log.e("token", getToken());

        // add post
        fab.setOnClickListener(view -> {
            Intent i = new Intent(AllPosts.this, AddPost.class);
            startActivity(i);
        });

    }

    @Override
    public void onSuccess(ArrayList<PostPojo> list) {
        adapter = new RecyclerViewAdapter(this, list);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }

    @Override
    public void onFail() {
        Toast.makeText(this, "Failed to download", Toast.LENGTH_SHORT).show();
    }

    public String getToken() {
        return "bearer " + PreferenceManager.getDefaultSharedPreferences(this).getString("TOKEN", "DEFAULT");
    }

    public void swipe() {
        presenter.getPosts(getToken());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.spinner, menu);



        return true;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // id
        int id = item.getItemId();

        switch (id){
            case R.id.profile :
                Intent intent = new Intent(AllPosts.this, UserProfile.class);
                startActivity(intent);
                break;
            case R.id.defualt :
                presenter.filter("Default");
                break;
            case R.id.park :
                presenter.filter("Park");
                break;
            case R.id.restaurant :
                presenter.filter("Restaurant");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
