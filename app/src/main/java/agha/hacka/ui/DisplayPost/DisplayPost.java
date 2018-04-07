package agha.hacka.ui.DisplayPost;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import agha.hacka.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayPost extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image ;

    @BindView(R.id.date)
    TextView date ;

    @BindView(R.id.title)
    TextView title ;

    @BindView(R.id.description)
    TextView description ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_post);
        ButterKnife.bind(this);

        // get intent
        Intent intent = getIntent();
        // extract extras
        date.setText(intent.getStringExtra("date"));
        title.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("description"));
        // extract image
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        image.setImageBitmap(bmp);

    }
}
