package co.joebirch.braillebox.injection.component;

import co.joebirch.braillebox.injection.module.ActivityModule;
import co.joebirch.braillebox.injection.scope.PerActivity;
import co.joebirch.braillebox.ui.base.BaseActivity;
import co.joebirch.braillebox.ui.main.MainActivity;
import dagger.Component;

/**
 * Component used to inject dependencies throughout the application at an Activity-level
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);
    void inject(MainActivity mainActivity);

}
