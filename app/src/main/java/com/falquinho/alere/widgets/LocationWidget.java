package com.falquinho.alere.widgets;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by falquinho on 07/12/2016.
 */
public class LocationWidget implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener
{
    protected static LocationWidget singleton;

    protected GoogleApiClient google_api;
    protected Context context;

    protected Location last_location;

    protected LocationWidget(Context android_context)
    {
        google_api = new GoogleApiClient.Builder(android_context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        last_location = null;
        context       = android_context;

        google_api.connect();
    }

    public static LocationWidget requestLocationWidget()
    {
        Log.i("LocationWidget","Requested!");
        return singleton;
    }

    public static LocationWidget initializeLocationWidget(Context c)
    {
        Log.i("LocationWidget","Initializing....");
        if (singleton != null)
        {
            Log.i("LocationWidget","Already initialized!");
            return null;
        }

        singleton = new LocationWidget(c);
        return singleton;
    }

    public void closeConnectionGoogleAPI()
    {
        google_api.disconnect();
    }

    public Location getLastLocation()
    {
        return last_location;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest lr = buildLocationRequest();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            Log.i("LocationWidget","DOES NOT HAVE PERMISSION TO ACCESS LOCATION");
            return;
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(google_api, lr, this);
        Log.i("LocationWidget","CONNECTED TO GOOGLE API.. REQUESTING LOCATION UPDTDATES");
    }

    protected LocationRequest buildLocationRequest()
    {
        LocationRequest lr = new LocationRequest();
        lr.setFastestInterval(60000);
        lr.setInterval(300000);
        lr.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        return lr;
    }


    @Override
    public void onLocationChanged(Location location)
    {
        Log.i("LocationWidget","LOCATION UPDATED");

        last_location = location;
    }

    @Override
    public void onConnectionSuspended(int i)
    {
        Log.i("LocationWidget","CONNECTION SUSPENDED");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        Log.i("LocationWidget","ERROR: FOR SOME REASON THE CONNECTION TO GOOGLE API FAILED");
    }
}
