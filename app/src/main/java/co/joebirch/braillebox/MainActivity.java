package co.joebirch.braillebox;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AppIdentifier;
import com.google.android.gms.nearby.connection.AppMetadata;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, Connections.ConnectionRequestListener,
        Connections.MessageListener {

    private static int[] NETWORK_TYPES = {ConnectivityManager.TYPE_WIFI,
            ConnectivityManager.TYPE_ETHERNET};

    private Gpio firstSolenoidGpio;
    private Gpio secondSolenoidGpio;
    private Gpio thirdSolenoidGpio;
    private Gpio fourthSolenoidGpio;
    private Gpio fifthSolenoidGpio;
    private Gpio sixthSolenoidGpio;

    private GoogleApiClient googleApiClient;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        googleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Nearby.CONNECTIONS_API).build();

        PeripheralManagerService pioService = new PeripheralManagerService();
        try {
            firstSolenoidGpio = pioService.openGpio(BoardDefaults.getFirstSolenoidGpioPin());
            secondSolenoidGpio = pioService.openGpio(BoardDefaults.getSecondSolenoidGpioPin());
            thirdSolenoidGpio = pioService.openGpio(BoardDefaults.getThirdSolenoidGpioPin());
            fourthSolenoidGpio = pioService.openGpio(BoardDefaults.getFourthSolenoidGpioPin());
            fifthSolenoidGpio = pioService.openGpio(BoardDefaults.getFifthSolenoidGpioPin());
            sixthSolenoidGpio = pioService.openGpio(BoardDefaults.getSixthSolenoidGpioPin());
        } catch (IOException e) {
            Log.e("Main", "Error configuring GPIO pins", e);
        }
    }

    @Override
    public void onConnected(@Nullable final Bundle bundle) {
        startAdvertising();

    }

    @Override
    public void onConnectionSuspended(final int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
    }

    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

       // close gpio connections
    }

    private boolean isConnectedToNetwork() {
        if (connectivityManager == null) {
            connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return (activeNetwork != null && activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                || (activeNetwork != null && activeNetwork.getType() ==
                ConnectivityManager.TYPE_ETHERNET);
    }

    private void startAdvertising() {
        if (!isConnectedToNetwork()) {
            return;
        }

        List<AppIdentifier> appIdentifierList = new ArrayList<>();
        appIdentifierList.add(new AppIdentifier(getPackageName()));
        AppMetadata appMetadata = new AppMetadata(appIdentifierList);
        Nearby.Connections.startAdvertising(googleApiClient, getString(R.string.service_id),
                appMetadata, 0L, this)
                .setResultCallback(new ResultCallback<Connections.StartAdvertisingResult>() {
                    @Override
                    public void onResult(@NonNull Connections.StartAdvertisingResult result) {

                    }
                });
    }

    @Override
    public void onConnectionRequest(final String endpointId, String deviceId, String endpointName,
                                    byte[] payload) {
        Nearby.Connections.acceptConnectionRequest(googleApiClient, endpointId, payload, this)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess()) {

                        } else {

                        }
                    }
                });
    }

    @Override
    public void onMessageReceived(final String s, final byte[] bytes, final boolean b) {

    }

    @Override
    public void onDisconnected(String s) {

    }


}
