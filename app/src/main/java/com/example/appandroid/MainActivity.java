package com.example.appandroid;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import com.skydoves.colorpickerview.ColorEnvelope;
//import com.skydoves.colorpickerview.ColorPickerDialog;
//import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        mapView=findViewById(R.id.map);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Configuration.getInstance().setUserAgentValue(getPackageName());

        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);
        checkLocationPermission();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //Location localizacao = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,
                location -> {

        textView.setText("Latitude" + location.getLatitude() + "\n Longitude" + location.getLatitude());
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(15.0);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        GeoPoint geoPoint = new GeoPoint(latitude, longitude);
        mapView.getController().setCenter(geoPoint);
        mapView.getController().setZoom(18.0);
        mapView.getController().animateTo(geoPoint);
        Marker marker = new Marker(mapView);
        marker.setPosition(geoPoint);
        marker.setTitle("Voce esta aqui");
        mapView.getOverlays().add(marker);
    });



        /*if (localizacao != null) {
            double latitude = localizacao.getLatitude();
            double longitude = localizacao.getLongitude();
            textView.setText("Latitude" + latitude + "\n Longitude" + longitude);
        } else {
            textView.setText("Localizacao nao disponivel");
        }*/

    }


    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Permissao concedida, continuar com operacao
            } else {
                //Permissao negada, tratar o caso
            }
        }
    }
}