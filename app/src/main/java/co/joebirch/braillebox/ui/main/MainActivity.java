package co.joebirch.braillebox.ui.main;

import android.os.Bundle;

import com.google.android.things.contrib.driver.button.Button;

import javax.inject.Inject;

import co.joebirch.braillebox.ui.base.BaseActivity;
import co.joebirch.braillebox.util.GpioHelper;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final int DEFAULT_DELAY = 500;
    private static final String DEFAULT_SOLENOID_STATE = "111111";

    @Inject GpioHelper gpioHelper;
    @Inject MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        mainPresenter.attachView(this);

        gpioHelper.openPushButtonGpioPin(buttonCallback);
        gpioHelper.openSolenoidGpioPins();
    }

    @Override
    public void showSequence(String sequence) {
        gpioHelper.sendGpioValues(sequence);
    }

    @Override
    public void resetSolenoids() {
        gpioHelper.sendGpioValues(DEFAULT_SOLENOID_STATE);
    }

    private Button.OnButtonEventListener buttonCallback =
            new Button.OnButtonEventListener() {
                @Override
                public void onButtonEvent(Button button, boolean pressed) {
                    if (pressed) {
                        mainPresenter.fetchArticle(DEFAULT_DELAY);
                    }
                }
            };
}