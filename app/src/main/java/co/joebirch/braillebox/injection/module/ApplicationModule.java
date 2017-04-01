package co.joebirch.braillebox.injection.module;

import android.app.Application;
import android.content.Context;

import com.google.android.things.pio.PeripheralManagerService;

import javax.inject.Singleton;

import co.joebirch.braillebox.data.remote.BrailleBoxService;
import co.joebirch.braillebox.data.remote.BrailleBoxServiceFactory;
import co.joebirch.braillebox.injection.scope.ApplicationContext;
import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies. Mainly singleton object that can be injected from
 * anywhere in the app.
 */
@Module
public class ApplicationModule {
    protected final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    BrailleBoxService provideBrailleBoxService() {
        return BrailleBoxServiceFactory.makeBrailleBoxService();
    }

    @Provides
    PeripheralManagerService providesPeripheralManagerService() {
        return new PeripheralManagerService();
    }

}