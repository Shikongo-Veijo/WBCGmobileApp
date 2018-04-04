package com.apress.gerber.wbcgapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(checkPlayServices())
        {
            Intent intent = new Intent(MainActivity.this,MapsActivity.class);
            startActivity(intent);

        }
    }

    //check for google play services
    public boolean checkPlayServices(){
        Log.d(TAG, "checkPlayServices: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS)
        {
            //everything is fine and the user can make map requests
            Log.d(TAG, "checkPlayServices:Google Play Services is Working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available))
        {
            //an error occured but we cn resolve it
            Log.d(TAG, "checkPlayServices: Error Occured but Can Be Fixed");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else
        {
            Toast.makeText(this, "We cannot connect, please check google play services version", Toast.LENGTH_SHORT).show();
        }

        return false;
    }







}
