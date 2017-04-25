package co.joebirch.braillebox.util;

import android.os.Build;

import com.google.android.things.pio.PeripheralManagerService;

import java.util.List;

class BoardDefaults {

    private static final String DEVICE_EDISON_ARDUINO = "edison_arduino";
    private static final String DEVICE_EDISON = "edison";
    private static final String DEVICE_RPI3 = "rpi3";
    private static final String DEVICE_NXP = "imx6ul";
    private static String sBoardVariant = "";

    static String getFirstSolenoidGpioPin() {
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

    static String getSecondSolenoidGpioPin() {
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

    static String getThirdSolenoidGpioPin() {
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

    static String getFourthSolenoidGpioPin() {
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

    static String getFifthSolenoidGpioPin() {
        switch (getBoardVariant()) {
            case DEVICE_EDISON_ARDUINO:
                return "";
            case DEVICE_EDISON:
                return "";
            case DEVICE_RPI3:
                return "BCM16";
            case DEVICE_NXP:
                return "";
            default:
                throw new IllegalArgumentException("Unknown device: " + Build.DEVICE);
        }
    }

    static String getSixthSolenoidGpioPin() {
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

    static String getPushButtonGpioPin() {
        switch (getBoardVariant()) {
            case DEVICE_EDISON_ARDUINO:
                return "";
            case DEVICE_EDISON:
                return "";
            case DEVICE_RPI3:
                return "BCM12";
            case DEVICE_NXP:
                return "";
            default:
                throw new IllegalArgumentException("Unknown device: " + Build.DEVICE);
        }
    }

    private static String getBoardVariant() {
        if (!sBoardVariant.isEmpty()) return sBoardVariant;

        sBoardVariant = Build.DEVICE;
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
