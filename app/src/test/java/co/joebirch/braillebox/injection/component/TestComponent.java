package co.joebirch.braillebox.injection.component;

import javax.inject.Singleton;

import co.joebirch.braillebox.injection.module.ApplicationTestModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}