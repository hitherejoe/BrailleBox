package co.joebirch.braillebox.util;

import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

import static com.google.android.things.pio.Gpio.ACTIVE_HIGH;
import static com.google.android.things.pio.Gpio.DIRECTION_OUT_INITIALLY_LOW;

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
        solenoids.add(configureGpioPin(BoardDefaults.getFirstSolenoidGpioPin()));
        solenoids.add(configureGpioPin(BoardDefaults.getSecondSolenoidGpioPin()));
        solenoids.add(configureGpioPin(BoardDefaults.getThirdSolenoidGpioPin()));
        solenoids.add(configureGpioPin(BoardDefaults.getFourthSolenoidGpioPin()));
        solenoids.add(configureGpioPin(BoardDefaults.getFifthSolenoidGpioPin()));
        solenoids.add(configureGpioPin(BoardDefaults.getSixthSolenoidGpioPin()));
    }

    private Gpio configureGpioPin(String pin) {
        Gpio gpioPin = null;
        try {
            gpioPin = peripheralManagerService.openGpio(pin);
            gpioPin.setDirection(DIRECTION_OUT_INITIALLY_LOW);
            gpioPin.setActiveType(ACTIVE_HIGH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gpioPin;
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