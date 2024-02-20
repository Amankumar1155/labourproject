package com.aman.labour;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class Registrationform extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private EditText dobEditText;
    private EditText addressEditText;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private ProgressBar progressBar;
    private Button btnSelectImage;
    private ImageView imageView;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ProgressDialog progressDialog;
    private String categoryName,categoryId;
    private EditText nameEditText, emailEditText, getAddress, mobileEditText, getDob, passwordEditText, reEnterPasswordEditText;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Select_service.class));
        finish();
    }

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationform);
        initUI();
        progressBar = findViewById(R.id.regProgress);
        dobEditText = findViewById(R.id.dob);
        dobEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        btnSelectImage = findViewById(R.id.btnSelectImage);
        imageView = findViewById(R.id.imageView);

        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            categoryId = intent.getStringExtra("category_id");
            categoryName = intent.getStringExtra("category_name");

            Toast.makeText(this, "CatId - "+categoryId, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "CatName - "+categoryName, Toast.LENGTH_SHORT).show();
        }

        addressEditText = findViewById(R.id.enteradd);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateAddress(location.getLatitude(), location.getLongitude());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(Registrationform.this, "Location provider disabled", Toast.LENGTH_SHORT).show();
            }
        };

        addressEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(Registrationform.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    progressBar.setVisibility(View.VISIBLE);
                    requestLocationUpdates();
                } else {
                    ActivityCompat.requestPermissions(Registrationform.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION);
                }
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading Image...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);

        nameEditText = findViewById(R.id.nameenter);
        emailEditText = findViewById(R.id.enteremail);
        getAddress = findViewById(R.id.enteradd);
        mobileEditText = findViewById(R.id.mobile);
        getDob = findViewById(R.id.dob);
        passwordEditText = findViewById(R.id.newpassword);
        reEnterPasswordEditText = findViewById(R.id.reEnterPassword);

        Button submitButton = findViewById(R.id.submitt);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String mobile = mobileEditText.getText().toString();
                String dob = dobEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String reEnterPassword = reEnterPasswordEditText.getText().toString();

                if (name.isEmpty() || email.isEmpty() || address.isEmpty() || mobile.isEmpty() || dob.isEmpty() || password.isEmpty() || reEnterPassword.isEmpty()) {
                    Toast.makeText(Registrationform.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(Registrationform.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                } else if (mobile.length() != 10) {
                    Toast.makeText(Registrationform.this, "Mobile number should be 10 digits", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(reEnterPassword)) {
                    Toast.makeText(Registrationform.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    String imagePath = getImagePath();
                    String selectedGender = ((AutoCompleteTextView) findViewById(R.id.SpinnerGender)).getText().toString();

                    String otp = generateOTP();
                    Log.d("Your Otp", "Otp - " + otp);
                    Toast.makeText(Registrationform.this, "Your Otp - " + otp, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Registrationform.this, OtpVerification.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("address", address);
                    intent.putExtra("mobile", mobile);
                    intent.putExtra("dob", dob);
                    intent.putExtra("gender", selectedGender);
                    intent.putExtra("password", password);
                    intent.putExtra("reEnterPassword", reEnterPassword);
                    intent.putExtra("imagePath", imagePath);
                    intent.putExtra("categoryId", categoryId);
                    intent.putExtra("otp", otp);
                    startActivity(intent);
                }
            }
        });


    }
    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generates a random number between 100000 and 999999
        return String.valueOf(otp);
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }
    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "-" + (month + 1) + "-" + year;
                        dobEditText.setText(selectedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    private void updateAddress(double latitude, double longitude) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                Geocoder geocoder = new Geocoder(Registrationform.this, Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                    if (!addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        String addressText = address.getAddressLine(0);
                        addressEditText.setText(addressText);
                    } else {
                        Toast.makeText(Registrationform.this, "Unable to fetch address", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void requestLocationUpdates() {
        progressBar.setVisibility(View.VISIBLE);
        try {
            locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, locationListener, null);
        } catch (SecurityException e) {
            e.printStackTrace();
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocationUpdates();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);

            uploadImage(imageUri);
        }
    }

    private void uploadImage(Uri imageUri) {
        progressDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                Toast.makeText(Registrationform.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
            }
        }, 1000);
    }

    private String getImagePath() {
        Uri imageUri = getImageUri();
        String imagePath = null;

        if (imageUri != null) {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(imageUri, projection, null, null, null);

            if (cursor != null) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                imagePath = cursor.getString(columnIndex);
                cursor.close();
            }
        }

        return imagePath;
    }

    private Uri getImageUri() {
        Drawable drawable = imageView.getDrawable();

        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
            return Uri.parse(path);
        }

        return null;
    }
    private void initUI() {
        final AutoCompleteTextView customerAutoTV = findViewById(R.id.SpinnerGender);
        ArrayList<String> customerList = getCustomerList();
        CustomArrayAdapter adapter = new CustomArrayAdapter(Registrationform.this, android.R.layout.simple_spinner_item, customerList);
        customerAutoTV.setAdapter(adapter);
    }

    private ArrayList<String> getCustomerList() {
        ArrayList<String> customers = new ArrayList<>();
        String[] genderArray = getResources().getStringArray(R.array.gender_options);
        Collections.addAll(customers, genderArray);
        return customers;
    }

    public class CustomArrayAdapter extends ArrayAdapter<String> {

        public CustomArrayAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setPadding(20, 20, 20, 20);
            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
            View view = super.getDropDownView(position, convertView, parent);
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setPadding(20, 20, 20, 20);
            return view;
        }
    }

}