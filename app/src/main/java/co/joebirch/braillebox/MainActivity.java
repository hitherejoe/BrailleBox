package co.joebirch.braillebox;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

public class MainActivity extends Activity  {

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
    protected void onDestroy(){
        super.onDestroy();

       // close gpio connections
    }


}
