package com.aman.labour;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chaos.view.PinView;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OtpVerification extends AppCompatActivity {

    PinView otp;
    Button submit;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        otp = findViewById(R.id.otpField);
        submit = findViewById(R.id.otp_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                if (intent != null) {
                    String name = intent.getStringExtra("name");
                    String email = intent.getStringExtra("email");
                    String address = intent.getStringExtra("address");
                    String mobile = intent.getStringExtra("mobile");
                    String dob = intent.getStringExtra("dob");
                    String gender = intent.getStringExtra("gender");
                    String password = intent.getStringExtra("password");
                    String reEnterPassword = intent.getStringExtra("reEnterPassword");
                    String imagePath = intent.getStringExtra("imagePath");
                    String categoryId = intent.getStringExtra("categoryId");
                    String otpValue = intent.getStringExtra("otp");

                    String enteredOtp = Objects.requireNonNull(otp.getText()).toString();

                    if (enteredOtp.equals(otpValue)) {
                        try {
                            byte[] imageBytes = convertImageToByteArray(imagePath);
                            uploadDataToServer(name, email, address, mobile, dob, gender, password, imageBytes, categoryId, otpValue);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(OtpVerification.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OtpVerification.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OtpVerification.this, Registrationform.class));
                }
            }
        });
    }

    private void uploadDataToServer(String name, String email, String address, String mobile, String dob, String gender, String password, byte[] imageBytes, String categoryId, String otp) {
        String baseUrl = getString(R.string.baseUrl);
        String SERVER_URL = baseUrl + "api/partner_insert.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String status = jsonResponse.getString("status");
                            if ("success".equals(status)) {
                                Toast.makeText(OtpVerification.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(OtpVerification.this, MainActivity.class));
                            } else {
                                Toast.makeText(OtpVerification.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(OtpVerification.this, "JSON Parsing Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OtpVerification.this, "Volley Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("address", address);
                params.put("mobile", mobile);
                params.put("dob", dob);
                params.put("gender", gender);
                params.put("password", password);
                params.put("category", categoryId);
                params.put("otp", otp);
                params.put("image", Arrays.toString(imageBytes));
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private byte[] convertImageToByteArray(String imagePath) throws IOException {
        File file = new File(imagePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        fileInputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
