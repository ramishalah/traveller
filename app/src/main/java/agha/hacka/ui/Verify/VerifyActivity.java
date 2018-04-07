package agha.hacka.ui.Verify;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import agha.hacka.R;
import agha.hacka.ui.AllPosts.AllPosts;
import agha.hacka.ui.Verify.VerifyPOJO.VerifyPojo;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyActivity extends AppCompatActivity implements VerifyView{

    @BindView(R.id.fab)
    FloatingActionButton fab ;

    @BindView(R.id.verify_number)
    EditText number ;

    private VerifyPresenter presenter;
    private Intent i ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        ButterKnife.bind(this);

        // init presenter
        presenter = new VerifyPresenter(this,this);

        fab.setOnClickListener(view -> fabClicked());
    }

    @Override
    public void onSuccess(VerifyPojo obj) {
        Log.e("LOGIN STATUS", obj.getAccessToken().toString().trim());
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("TOKEN", obj.getAccessToken().toString().trim()).apply();
        Toast.makeText(this,"User Has Been Authorized Successfuly",Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, AllPosts.class);
        startActivity(i);
    }

    @Override
    public void onFail() {
        number.setError("Incorrect Code !!");
    }

    @Override
    public void fabClicked() {
        i = getIntent();
        String phone = i.getExtras().getString("PHONE_NUMBER");
        Log.i("PHONE_NUMBER",String.valueOf(phone));
        presenter.insertCode(phone,number.getText().toString().trim());
    }
}
