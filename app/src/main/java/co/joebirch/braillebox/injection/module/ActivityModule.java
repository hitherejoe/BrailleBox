package co.joebirch.braillebox.injection.module;

import android.app.Activity;
import android.content.Context;

import co.joebirch.braillebox.injection.scope.ActivityContext;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return activity;
    }

}