package co.joebirch.braillebox.ui.base;

import android.app.Activity;
import android.os.Bundle;

import co.joebirch.braillebox.BrailleBoxApplication;
import co.joebirch.braillebox.injection.component.ActivityComponent;
import co.joebirch.braillebox.injection.component.DaggerActivityComponent;
import co.joebirch.braillebox.injection.module.ActivityModule;

/**
 * Base Activity implementation that provides activity with the following functionality:
 * <p>
 * - Creation of Dagger components
 */
public abstract class BaseActivity extends Activity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(BrailleBoxApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

}