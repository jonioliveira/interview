package com.jonioliveira.interview.di.component;

import android.app.Application;
import com.jonioliveira.interview.InterviewApp;
import com.jonioliveira.interview.di.builder.ActivityBuilder;
import com.jonioliveira.interview.di.module.AppModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(InterviewApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
