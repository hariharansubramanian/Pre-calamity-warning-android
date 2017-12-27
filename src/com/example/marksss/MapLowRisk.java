package com.example.marksss;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 */
public class MapLowRisk extends FragmentActivity implements OnMapReadyCallback {

    /*static final String TAG_RESULTS = "results";
    static final String TAG_GEO = "geometry";
    static final String TAG_LOCATION = "location";
    static final String TAG_LAT = "lat";
    static final String TAG_LNG = "lng";
    JSONArray res = null;
     static String url = "http://maps.google.com/maps/api/geocode/json?address=guindy,+chennai,+IN&sensor=false";

   */ 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maplowrisk);
        
        /*JSONParstring jParser = new JSONParstring();
        JSONObject jobj = new JSONObject(json);

        res = jobj.getJSONArray(TAG_RESULTS);
        for(int i = 0; i < res.length(); i++){
            JSONObject c = res.getJSONObject(i);

            JSONObject loc = c.optJSONObject(TAG_GEO).optJSONObject(TAG_LOCATION);

        String lat1 =loc.getString(TAG_LAT);

        String lng1 = loc.getString(TAG_LNG);
        double latpoint = Double.parseDouble(i);  
		double lngpoint = Double.parseDouble(j);
        */
        
        Button button_low = (Button) findViewById(R.id.Button01);
        button_low.setEnabled(false);
        
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        
        final Context context = this;
        
		OnClickListener click_notify= new OnClickListener() {

			public void onClick(View arg0) {
				 
			    Intent intent = new Intent(context, PopupDemoActivity.class);
                            startActivity(intent);   
 
			}
		};
		
		Button button_notify = (Button) findViewById(R.id.Button04);
		button_notify.setOnClickListener(click_notify);
		
		
		OnClickListener click_high= new OnClickListener() {

			public void onClick(View arg0) {
				 
			    Intent intent = new Intent(context, MapHighRisk.class);
                            startActivity(intent);   
 
			}
		};
		
		Button button_high = (Button) findViewById(R.id.Button03);
		button_high.setOnClickListener(click_high);
		
		OnClickListener click_medium= new OnClickListener() {

			public void onClick(View arg0) {
				 
			    Intent intent = new Intent(context, MapMediumRisk.class);
                            startActivity(intent);   
 
			}
		};
 
		Button button_medium = (Button) findViewById(R.id.Button02);
		button_medium.setOnClickListener(click_medium);

		
		
    }
        
    

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     */
    @Override
    public void onMapReady(GoogleMap map) {
    	LatLng aa = new LatLng(36.052118, 138.043535);
    	//map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.052118,138.043535), 13f), 2000, null);
    	 map.moveCamera(CameraUpdateFactory.newLatLngZoom(aa,13f));
        map.addMarker(new MarkerOptions().position(new LatLng(36.066435,138.058019)).title("Customer5").snippet("ID: 52565| Policy No: 2440"));
        map.addMarker(new MarkerOptions().position(new LatLng(36.072748,138.027463)).title("Customer7").snippet("ID: 52513| Policy No: 5319"));
        
        List<LatLng> list = null;
        try {
            list = readItems(R.raw.maplowrisk);
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
        }
        int[] colors = {
        		Color.rgb(0, 153, 0), // yellow low risk
        	    Color.rgb(0, 153, 61)  
        };

        	float[] startPoints = {
        	    0.2f, 1f
        	};

        	Gradient gradient = new Gradient(colors, startPoints);
        HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
            .data(list)
            .gradient(gradient)
            .radius(50)
            .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        TileOverlay mOverlay = map.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
    }
    private ArrayList<LatLng> readItems(int resource) throws JSONException {
        ArrayList<LatLng> list = new ArrayList<LatLng>();
        InputStream inputStream = getResources().openRawResource(resource);
        String json = new Scanner(inputStream).useDelimiter("\\A").next();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            double lat = object.getDouble("lat");
            double lng = object.getDouble("lng");
            list.add(new LatLng(lat, lng));
        }
        return list;
    }
}
    


