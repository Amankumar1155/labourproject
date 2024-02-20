package com.aman.labour;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aman.labour.Adapter.ServiceAdapter;
import com.aman.labour.Model.Category;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Select_service extends AppCompatActivity {

    RecyclerView recyclerView;

    String names[]={"Ram","Shyam"};
    String otp;




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, OtpVerification.class));
        finish();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service);


        recyclerView = findViewById(R.id.gridRecyclerView);

        recyclerView=findViewById(R.id.gridRecyclerView);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String baseUrl = getString(R.string.baseUrl);
        String API_URL = baseUrl + "api/category.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<Category> categories = new ArrayList<>();

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject categoryJson = response.getJSONObject(i);
                                String id = categoryJson.getString("id");
                                String name = categoryJson.getString("name");
                                String image = categoryJson.getString("image");

                                // Create a Category object and add it to the list
                                Category category = new Category(id, name, image);
                                categories.add(category);
                            }

                            // Convert the list of Category objects to arrays
                            Category[] categoriesArray = categories.toArray(new Category[0]);

                            // Create an adapter with the Category array
                            ServiceAdapter adapter = new ServiceAdapter(categoriesArray, Select_service.this);
                            recyclerView.setLayoutManager(new GridLayoutManager(Select_service.this, 2));
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", "Error: " + error.toString());
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }
}
