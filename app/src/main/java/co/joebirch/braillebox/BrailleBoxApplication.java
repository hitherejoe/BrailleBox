package co.joebirch.braillebox;

import android.app.Application;
import android.content.Context;

import co.joebirch.braillebox.injection.component.ApplicationComponent;
import co.joebirch.braillebox.injection.component.DaggerApplicationComponent;
import co.joebirch.braillebox.injection.module.ApplicationModule;
import timber.log.Timber;

public class BrailleBoxApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static BrailleBoxApplication get(Context context) {
        return (BrailleBoxApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

}