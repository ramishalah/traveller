package agha.hacka.ui.AddPost;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import agha.hacka.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPost extends AppCompatActivity {
    private static final String TAG = "AddPost";

    @BindView(R.id.coverImage)
    ImageView coverImage;

    @BindView(R.id.descriptionText)
    TextView description;

    @BindView(R.id.uploadImage)
    ImageButton uploadImage;

    @BindView(R.id.takeImage)
    ImageButton takeImage;

    @BindView(R.id.post)
    Button postButton;

    @BindView(R.id.locationButton)
    ImageButton locationButton;

    private double lat;
    private double lng;

    private Uri selectedImage;
    private Bitmap photo;
//    private Intent locationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        ButterKnife.bind(this);


        // Assigning the listeners
        uploadImage.setOnClickListener(view -> {

            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

        });

        takeImage.setOnClickListener(view -> {

            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, 0);
        });

//        locationButton.setOnClickListener(view -> {
//
//            Intent intent = new Intent(this, PostLocation.class);
//            startActivity(intent);
//        });

        postButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, PostLocation.class);
            if(selectedImage != null) {
                intent.putExtra("SELECTED_IMAGE_URI", selectedImage.toString());
            }

            if(photo != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("TAKEN_IMAGE", byteArray);
            }

            // Adding the description
            intent.putExtra("DESCRIPTION", description.getText().toString());

            startActivity(intent);
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    coverImage.setImageBitmap(photo);
                    Log.d(TAG, "onActivityResult: the image is: " + photo);
//                    Uri selectedImage = imageReturnedIntent.getData();
//                    Log.d(TAG, "onActivityResult: The takenImage URI: " + selectedImage);
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    Log.d(TAG, "onActivityResult: The selectedImage URI: " + selectedImage);
//                    imageview.setImageURI(selectedImage);
                }
                break;
//            case 2:
//                lat = getIntent().getExtras().getDouble("LAT_LOCATION");
//                lng = getIntent().getExtras().getDouble("LNG_LOCATION");

        }
    }

//    private Bitmap convertImageViewToBitmap(ImageView v) {
//        Bitmap bm = Ion.with(v).getBitmap();
//        return bm;
//    }
//
//    private void put() {
//        // put bitmap
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        convertImageViewToBitmap(image).compress(Bitmap.CompressFormat.JPEG, 50, stream);
//        byte[] byteArray = stream.toByteArray();
//        locationIntent.putExtra("image", byteArray);
//    }

}
