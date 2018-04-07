package agha.hacka.ui.UserProfile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import agha.hacka.R;
import agha.hacka.ui.AllPosts.AllPosts;
import agha.hacka.ui.Verify.VerifyPOJO.UserProfileResponse;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserProfile extends AppCompatActivity implements UserProfileView {
    private static final String TAG = "UserProfile";
    // Getting the singleton
    UserSingleton userSingleton = UserSingleton.getInstance();

    UserProfilePresenter userProfilePresenter;

    private Uri selectedImage;

    Bitmap bitMapPhoto = null;

    // Binding the views
    @BindView(R.id.profile)
    ImageView userPhoto;

    @BindView(R.id.name)
    EditText fullNameText;

    @BindView(R.id.email)
    EditText emailText;

    @BindView(R.id.submit)
    Button updateButton;

    @BindView(R.id.gallery)
    FloatingActionButton uploadImageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        ButterKnife.bind(this);

        userProfilePresenter = new UserProfilePresenter(this);

        // Initializing the fields
        String url = userSingleton.getUrl();
        if (url != null) {
            Ion.with(this)
                    .load(userSingleton.getUrl())
                    .withBitmap()
                    .intoImageView(userPhoto);
        }

        String fullName = userSingleton.getFullName();
        if (fullName != null) {
            fullNameText.setText(fullName);
        }

        String email = userSingleton.getEmail();
        if (email != null) {
            emailText.setText(email);
        }

        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               String email = emailText.getText().toString();
//               String fullName = fullNameText.getText().toString();
                String token = "bearer " + PreferenceManager.getDefaultSharedPreferences(UserProfile.this).getString("TOKEN", "DEFAULT");
                String userId = PreferenceManager.getDefaultSharedPreferences(UserProfile.this).getString("USER_ID", "DEFAULT");

//                MultipartBody.Part fullName = MultipartBody.Part.createFormData("email", RequestBody.create(MediaType.parse("text/plain"), emailText.getText().toString()));
                RequestBody fullName = RequestBody.create(MediaType.parse("text/plain"), fullNameText.getText().toString());
                RequestBody email = RequestBody.create(MediaType.parse("text/plain"), emailText.getText().toString());
                File imageSelected = null;

                if(bitMapPhoto != null) {
                    imageSelected = convertBitmapToFile(bitMapPhoto);
                }
                MultipartBody.Part filePart = null;
                if(imageSelected != null){
                    filePart = MultipartBody.Part.createFormData("file", imageSelected.getName(), RequestBody.create(MediaType.parse("image/*"), imageSelected));

                }
                // Request
                userProfilePresenter.updateUserProfile(token, userId, filePart, email, fullName);

                // Modifying the singleton
                userSingleton.setFullName(fullNameText.getText().toString());
                userSingleton.setEmail(emailText.getText().toString());

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (resultCode == RESULT_OK) {

            selectedImage = imageReturnedIntent.getData();
            bitMapPhoto = null;
            try {
                bitMapPhoto = getBitmapFromUri(imageReturnedIntent.getData());
            } catch (IOException e) {
                Log.d(TAG, "onActivityResult: Error in converting the uri to bitmap");
            }

            userPhoto.setImageBitmap(bitMapPhoto);
            Log.d(TAG, "onActivityResult: The selectedImage URI: " + selectedImage);
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

    // Converting a bitmap to a file so that it can be sent as a part
    private File convertBitmapToFile(Bitmap bitmapImage) {

        //create a file to write bitmap data
        File f = new File(this.getCacheDir(), "bitmapImage");
        try {
            f.createNewFile();
        } catch (IOException e) {
        }


        //Convert bitmap to byte array
        Bitmap bitmap = bitmapImage;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapdata = bos.toByteArray();

        try{
            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            Log.d(TAG, "convertBitmapToFile: Error in opening FIleInputStream");
            return null;
        }
        catch (IOException e){
            Log.d(TAG, "convertBitmapToFile: error in writing to the file");
            return null;
        }
        return f;
    }

    @Override
    public void onSuccess(UserProfileResponse userProfileResponse) {
        Intent i = new Intent(this, AllPosts.class);
        startActivity(i);
    }

    @Override
    public void onFail() {

    }
}
