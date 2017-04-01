package co.joebirch.braillebox.util;

import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class GpioHelper {

    private List<Gpio> solenoids;
    private PeripheralManagerService peripheralManagerService;

    @Inject
    GpioHelper(PeripheralManagerService peripheralManagerService) {
        this.peripheralManagerService = peripheralManagerService;
        solenoids = new ArrayList<>();
    }

    public void openPushButtonGpioPin(Button.OnButtonEventListener buttonCallback) {
        try {
            Button button = new Button(BoardDefaults.getPushButtonGpioPin(),
                    Button.LogicState.PRESSED_WHEN_LOW);

            button.setOnButtonEventListener(buttonCallback);
        } catch (IOException error) {
            Timber.e(error, "There was an error configuring the push button GPIO pin");
        }
    }

    public void openSolenoidGpioPins() {
        try {
            solenoids.add(peripheralManagerService.openGpio(
                    BoardDefaults.getFirstSolenoidGpioPin()));
            solenoids.add(peripheralManagerService.openGpio(
                    BoardDefaults.getSecondSolenoidGpioPin()));
            solenoids.add(peripheralManagerService.openGpio(
                    BoardDefaults.getThirdSolenoidGpioPin()));
            solenoids.add(peripheralManagerService.openGpio(
                    BoardDefaults.getFourthSolenoidGpioPin()));
            solenoids.add(peripheralManagerService.openGpio(
                    BoardDefaults.getFifthSolenoidGpioPin()));
            solenoids.add(peripheralManagerService.openGpio(
                    BoardDefaults.getSixthSolenoidGpioPin()));
        } catch (IOException error) {
            Timber.e(error, "There was an error configuring GPIO pins");
        }
    }

    public void sendGpioValues(String sequence) {
        for (int i = 0; i < solenoids.size(); i++) {
            try {
                solenoids.get(i).setValue((int) sequence.charAt(i) == 1);
            } catch (IOException error) {
                Timber.e(error, "There was an error configuring GPIO pins");
            }
        }
    }

}
