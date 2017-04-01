package co.joebirch.braillebox.injection.component;

import android.app.Application;
import android.content.Context;

import com.google.android.things.pio.PeripheralManagerService;

import javax.inject.Singleton;

import co.joebirch.braillebox.data.remote.BrailleBoxService;
import co.joebirch.braillebox.injection.module.ApplicationModule;
import co.joebirch.braillebox.injection.scope.ApplicationContext;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
    Application application();
    PeripheralManagerService peripheralManagerService();
    BrailleBoxService brailleBoxService();

}
