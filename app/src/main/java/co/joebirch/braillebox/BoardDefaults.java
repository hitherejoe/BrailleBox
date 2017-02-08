package co.joebirch.braillebox;

import android.os.Build;

import com.google.android.things.pio.PeripheralManagerService;

import java.util.List;

public class BoardDefaults {

    private static final String DEVICE_EDISON_ARDUINO = "edison_arduino";
    private static final String DEVICE_EDISON = "edison";
    private static final String DEVICE_RPI3 = "rpi3";
    private static final String DEVICE_NXP = "imx6ul";
    private static String sBoardVariant = "";

    public static String getFirstSolenoidGpioPin() {
        switch (getBoardVariant()) {
            case DEVICE_EDISON_ARDUINO:
                return "";
            case DEVICE_EDISON:
                return "";
            case DEVICE_RPI3:
                return "BCM17";
            case DEVICE_NXP:
                return "";
            default:
                throw new IllegalArgumentException("Unknown device: " + Build.DEVICE);
        }
    }

    public static String getSecondSolenoidGpioPin() {
        switch (getBoardVariant()) {
            case DEVICE_EDISON_ARDUINO:
                return "";
            case DEVICE_EDISON:
                return "";
            case DEVICE_RPI3:
                return "BCM27";
            case DEVICE_NXP:
                return "";
            default:
                throw new IllegalArgumentException("Unknown device: " + Build.DEVICE);
        }
    }

    public static String getThirdSolenoidGpioPin() {
        switch (getBoardVariant()) {
            case DEVICE_EDISON_ARDUINO:
                return "";
            case DEVICE_EDISON:
                return "";
            case DEVICE_RPI3:
                return "BCM22";
            case DEVICE_NXP:
                return "";
            default:
                throw new IllegalArgumentException("Unknown device: " + Build.DEVICE);
        }
    }

    public static String getFourthSolenoidGpioPin() {
        switch (getBoardVariant()) {
            case DEVICE_EDISON_ARDUINO:
                return "";
            case DEVICE_EDISON:
                return "";
            case DEVICE_RPI3:
                return "BCM23";
            case DEVICE_NXP:
                return "";
            default:
                throw new IllegalArgumentException("Unknown device: " + Build.DEVICE);
        }
    }

    public static String getFifthSolenoidGpioPin() {
        switch (getBoardVariant()) {
            case DEVICE_EDISON_ARDUINO:
                return "";
            case DEVICE_EDISON:
                return "";
            case DEVICE_RPI3:
                return "BCM24";
            case DEVICE_NXP:
                return "";
            default:
                throw new IllegalArgumentException("Unknown device: " + Build.DEVICE);
        }
    }

    public static String getSixthSolenoidGpioPin() {
        switch (getBoardVariant()) {
            case DEVICE_EDISON_ARDUINO:
                return "";
            case DEVICE_EDISON:
                return "";
            case DEVICE_RPI3:
                return "BCM25";
            case DEVICE_NXP:
                return "";
            default:
                throw new IllegalArgumentException("Unknown device: " + Build.DEVICE);
        }
    }

    private static String getBoardVariant() {
        if (!sBoardVariant.isEmpty()) {
            return sBoardVariant;
        }
        sBoardVariant = Build.DEVICE;
        // For the edison check the pin prefix
        // to always return Edison Breakout pin name when applicable.
        if (sBoardVariant.equals(DEVICE_EDISON)) {
            PeripheralManagerService pioService = new PeripheralManagerService();
            List<String> gpioList = pioService.getGpioList();
            if (gpioList.size() != 0) {
                String pin = gpioList.get(0);
                if (pin.startsWith("IO")) {
                    sBoardVariant = DEVICE_EDISON_ARDUINO;
                }
            }
        }
        return sBoardVariant;
    }
}
