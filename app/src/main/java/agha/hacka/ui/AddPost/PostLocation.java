package agha.hacka.ui.AddPost;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import agha.hacka.R;
import agha.hacka.ui.AddPost.AddPostPojo.AddPostRequest;
import agha.hacka.ui.AddPost.AddPostPojo.AddPostResponse;
import agha.hacka.ui.AddPost.AddPostPojo.Metadata;
import agha.hacka.ui.AddPost.AddPostPojo.PutImageResponse;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PostLocation extends FragmentActivity implements OnMapReadyCallback, AddPostView{
    private static final String TAG = "PostLocation";

    @BindView(R.id.saveButton)
    Button saveButton;

    private GoogleMap mMap;
    private LatLng locationSpecified;
    private AddPostPresenter mAddPostPresenter;
    private String token = "bearer 71916f764939161c869a5b46945216543cec65af180dd7ef911c1e2ba02d63a6";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_location);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.postMap);
        mapFragment.getMapAsync(this);

        // Initializing the presenter
        mAddPostPresenter = new AddPostPresenter(this);

        // Adding the listener to the saveButton
        saveButton.setOnClickListener(view -> {

            if(locationSpecified != null) {
//                Intent intent = new Intent(this, AddPost.class);
//                intent.putExtra("LAT_LOCATION", locationSpecified.latitude);
//                intent.putExtra("LNG_LOCATION", locationSpecified.longitude);

                Log.d(TAG, "onCreate: the latitude is: " + locationSpecified.latitude);
                Log.d(TAG, "onCreate: the longitude is: " + locationSpecified.longitude);

                // We initialize the request body
                String description = getIntent().getExtras().getString("DESCRIPTION");
                String title = getIntent().getExtras().getString("TITLE");

                double lat = locationSpecified.latitude;
                double lng = locationSpecified.longitude;

                String metaDataKey = "ramiagha";
                String choice = getIntent().getExtras().getString("CHOICE");



                Metadata metadata = new Metadata();
                metadata.setTitle(title);
                metadata.setChoice(choice);


                AddPostRequest addPostRequest = new AddPostRequest();
                addPostRequest.setLatitude(lat);
                addPostRequest.setLongitude(lng);
                addPostRequest.setDescription(description);
                addPostRequest.setMetadata(metadata);
                addPostRequest.setMetadataKey(metaDataKey);

                mAddPostPresenter.addPost(addPostRequest, token);
            }

        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        // Checking for the permission granted or not
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "please enable the location permission", Toast.LENGTH_LONG).show();
        } else {
            mMap.setMyLocationEnabled(true);
        }

        mMap.getUiSettings().setMyLocationButtonEnabled(true);


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(24, 46);
        float zoomLevel = 16.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        // Adding the marker to the clicked location.
        mMap.setOnMapClickListener(latLng -> {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(latLng.latitude + " : " + latLng.longitude);

            // Getting tha latitude and the longitude.
            locationSpecified = markerOptions.getPosition();

            mMap.clear();
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.addMarker(markerOptions);
        });
    }



    // Converting a bitmap to a file so that it can be sent as a part
    private File convertBytetoFile(byte [] arr) {

        //create a file to write bitmap data
        File f = new File(this.getCacheDir(), "bitmapImage");
        try {
            f.createNewFile();
        } catch (IOException e) {
        }


//        //Convert bitmap to byte array
//        Bitmap bitmap = bitmapImage;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapdata = arr;

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

    private Bitmap extractBitmap(byte[] arr){
        // extract image
        byte[] byteArray = arr;
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bmp;
    }

    @Override
    public void onSuccess(AddPostResponse addPostResponse) {
        Log.d(TAG, "onSuccess: HOLA!");
        String postId = addPostResponse.getPostId();
        // Getting the bitmap from the extras
//        Bitmap bitmap = extractBitmap(getIntent().getByteArrayExtra("TAKEN_IMAGE"));

        // This is for the image taken from the camera
        byte [] arr = getIntent().getByteArrayExtra("TAKEN_IMAGE");
        File imageTaken = null;

        if(arr != null) {
            imageTaken = convertBytetoFile(arr);
        }



        Log.d(TAG, "onSuccess: The URI extracted is: " + getIntent().getExtras().getString("SELECTED_IMAGE_URI"));

        // This is for the image selected from the gallary
        String uriString = getIntent().getExtras().getString("SELECTED_IMAGE_URI");
        Uri uri = null;
        if(uriString != null) {
            uri = Uri.parse(uriString) ;
        }

        File imageSelected = null;
        if(uri != null) {
            try{
                Bitmap bitmap = getBitmapFromUri(uri);
                imageSelected = convertBitmapToFile(bitmap);
            } catch (IOException e) {
                Log.d(TAG, "onSuccess: error in converting to bitmap");
            }
        }



        MultipartBody.Part filePart = null;

        if(imageTaken != null) {
            // Creating the multipart part
            filePart = MultipartBody.Part.createFormData("file", imageTaken.getName(), RequestBody.create(MediaType.parse("image/*"), imageTaken));
        } else if (imageSelected != null) {
            filePart = MultipartBody.Part.createFormData("file", imageSelected.getName(), RequestBody.create(MediaType.parse("image/*"), imageSelected));
        }

        mAddPostPresenter.putImage(token, postId, filePart);
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    @Override
    public void onFail() {

    }

    @Override
    public void onSuccessPuttingImage(PutImageResponse putImageResponse) {
        Log.d(TAG, "onSuccessPuttingImage: The image has been uploaded successfully");
    }


    @Override
    public void onFailPuttingImage() {

    }


//    @Override
//    public void onMapClick(LatLng latLng) {
//        Log.d(TAG, "onMapClick: I'm clicking on the map");
//        // Adding the marker to the location specified
//        mMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude, latLng.longitude)));
//
//
//    }


}
