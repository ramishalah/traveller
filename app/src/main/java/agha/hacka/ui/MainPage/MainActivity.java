package agha.hacka.ui.MainPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import agha.hacka.R;
import agha.hacka.pogo.FacultyResponse;
import agha.hacka.ui.Map.MapsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPageView{
    @BindView(R.id.helloWorld) TextView helloWorld;
    private static final String TAG = "MainActivity";

    private MainPagePresenter mainPagePresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starting onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPagePresenter = new MainPagePresenter(this, this);
        mainPagePresenter.getFaculty();

        helloWorld.setText("butter knife is used");
    }

    @Override
    public void onSuccess(List<FacultyResponse> facultyResponse) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFail() {
        // you do something if it fails
    }
}
