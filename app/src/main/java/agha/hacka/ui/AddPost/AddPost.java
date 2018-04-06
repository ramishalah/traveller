package agha.hacka.ui.AddPost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import agha.hacka.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPost extends AppCompatActivity {
    @BindView(R.id.descriptionText)
    TextView description;

    @BindView(R.id.uploadImage)
    ImageButton uploadImage;

    @BindView(R.id.post)
    Button postButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        ButterKnife.bind(this);


    }
}
