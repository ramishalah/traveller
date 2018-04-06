package agha.hacka.ui.Login;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import agha.hacka.R;
import agha.hacka.ui.Login.LoginPOJO.LoginPojo;
import agha.hacka.ui.Verify.VerifyActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.fab)
    FloatingActionButton fab ;

    @BindView(R.id.phone_number)
    EditText number ;

    private LoginPresenter presenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // init presenter
        presenter = new LoginPresenter(this,this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { fabClicked(); } });

    }

    public void fabClicked() {
        presenter.insertNumber(number.getText().toString().trim());
    }

    public void onFail(){
        number.setError("Incorrect number !!");
    }


    public void onSuccess(LoginPojo obj){
        Log.e("LOGIN STATUS", obj.getSuccess().toString().trim());
        Intent i = new Intent(this, VerifyActivity.class);
        i.putExtra("PHONE_NUMBER",number.getText().toString().trim());
        startActivity(i);
    }



}
