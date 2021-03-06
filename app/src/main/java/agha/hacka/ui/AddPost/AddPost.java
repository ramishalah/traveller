package agha.hacka.ui.AddPost;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

import agha.hacka.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPost extends AppCompatActivity {
    private static final String TAG = "AddPost";

    @BindView(R.id.post_image)
    ImageView coverImage;

    @BindView(R.id.post_description)
    TextView description;

    @BindView(R.id.gallery)
    Button uploadImage;

    @BindView(R.id.camera)
    Button takeImage;

    @BindView(R.id.location)
    Button postButton;

    @BindView(R.id.post_title)
    TextView title;

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;

    @BindView(R.id.spinner)
    MaterialSpinner spinner;

    private double lat;
    private double lng;

    private Uri selectedImage;
    private Bitmap photo;
    //    private Intent locationIntent;
    private String itemText = "Default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        ButterKnife.bind(this);

        // Setting the title of the tool bar
        toolbar.setTitle("Add Post");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        // Setting the items of the spinner
        spinner.setItems("Default", "Park", "Restaurant");
        spinner.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) ->
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show());

        // Adding a listener
        spinner.setOnItemSelectedListener((view, position, id, item) -> {
            itemText = (String) item;
        });

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


        postButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, PostLocation.class);
            if (selectedImage != null) {
                intent.putExtra("SELECTED_IMAGE_URI", selectedImage.toString());
            }

            if (photo != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("TAKEN_IMAGE", byteArray);
            }
            String descriptionText = description.getText().toString();

            if (descriptionText != null) {
                // Adding the description
                intent.putExtra("DESCRIPTION", description.getText().toString());
            }


            String titleText = title.getText().toString();
            if (titleText != null) {
                // Adding the title
                intent.putExtra("TITLE", titleText);
            }

            if (itemText != null) {
                intent.putExtra("CHOICE", itemText);
            }
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
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    Bitmap bitMapPhoto = null;
                    try {
                        bitMapPhoto = getBitmapFromUri(imageReturnedIntent.getData());
                    } catch (IOException e) {
                        Log.d(TAG, "onActivityResult: Error in converting the uri to bitmap");
                    }

                    coverImage.setImageBitmap(bitMapPhoto);
                    Log.d(TAG, "onActivityResult: The selectedImage URI: " + selectedImage);
                }
                break;

        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }


}
