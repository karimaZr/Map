package com.example.map;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.map.databinding.ActivityMapsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    String showUrl = "http://192.168.1.119/Mapbackend/adaptjson.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        mapFragment.getMapAsync(this);

    }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap; // Initialize the map
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    showUrl, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray positions = response.getJSONArray("positions");

                        for (int i = 0; i < positions.length(); i++) {
                            JSONObject position = positions.getJSONObject(i);
                            LatLng sydne = new LatLng(position.getDouble("latitude"),
                                    position.getDouble("longitude"));
                            mMap.addMarker(new MarkerOptions().position(sydne).title("Marker"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydne));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Handle error
                }
            });
            requestQueue.add(jsonObjectRequest);
        }

}