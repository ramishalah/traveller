package agha.hacka.ui.MainPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import agha.hacka.R;
import agha.hacka.pogo.FacultyResponse;

public class MainActivity extends AppCompatActivity implements MainPageView{
    private static final String TAG = "MainActivity";

    private MainPagePresenter mainPagePresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starting onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPagePresenter = new MainPagePresenter(this, this);
        mainPagePresenter.getFaculty();
    }

    @Override
    public void onSuccess(List<FacultyResponse> facultyResponse) {
        // here we do something
    }

    @Override
    public void onFail() {
        // you do something if it fails
    }
}
