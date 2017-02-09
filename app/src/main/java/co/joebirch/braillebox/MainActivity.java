package co.joebirch.braillebox;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AppIdentifier;
import com.google.android.gms.nearby.connection.AppMetadata;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        Connections.ConnectionRequestListener,
        Connections.MessageListener,
        Connections.EndpointDiscoveryListener {

    private Gpio firstSolenoidGpio;
    private Gpio secondSolenoidGpio;
    private Gpio thirdSolenoidGpio;
    private Gpio fourthSolenoidGpio;
    private Gpio fifthSolenoidGpio;
    private Gpio sixthSolenoidGpio;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Nearby.CONNECTIONS_API)
                .build();

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


}
