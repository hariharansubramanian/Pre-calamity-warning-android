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
public class MapMediumRisk extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapmediumrisk);

        Button button_medium = (Button) findViewById(R.id.Button02);
        button_medium.setEnabled(false);
        
        
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
		OnClickListener click_low= new OnClickListener() {

			public void onClick(View arg0) {
				 
			    Intent intent = new Intent(context, MapLowRisk.class);
                            startActivity(intent);   
 
			}
		};
 
		Button button_low = (Button) findViewById(R.id.Button01);
		button_low.setOnClickListener(click_low);
		
		OnClickListener click_high= new OnClickListener() {

			public void onClick(View arg0) {
				 
			    Intent intent = new Intent(context, MapHighRisk.class);
                            startActivity(intent);   
 
			}
		};
 
		Button button_high = (Button) findViewById(R.id.Button03);
		button_high.setOnClickListener(click_high);
		
		
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     */
    @Override
    public void onMapReady(GoogleMap map) {
    	LatLng aa = new LatLng(36.052118, 138.043535);
    	map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.052118,138.043535), 13f), 2000, null);
    	 map.moveCamera(CameraUpdateFactory.newLatLngZoom(aa,13f));
        map.addMarker(new MarkerOptions().position(new LatLng(36.07032,138.040338)).title("Customer9").snippet("ID: 52198").snippet("Policy No: 1024"));
        map.addMarker(new MarkerOptions().position(new LatLng(36.055264,138.0442)).title("Customer15").snippet("ID: 52340").snippet("Policy No: 1207"));
        List<LatLng> list = null;
        try {
            list = readItems(R.raw.mapmediumrisk);
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
        }
        int[] colors = {
        		Color.rgb(204, 204, 0), // medium risk
        	    Color.rgb(204, 204, 0)  
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
    


