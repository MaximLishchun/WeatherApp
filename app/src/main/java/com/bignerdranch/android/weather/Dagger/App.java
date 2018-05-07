package com.bignerdranch.android.weather.Dagger;


import android.app.Application;

public class App extends Application{

    private static DaggerComponent daggerComponent;

    public static DaggerComponent getDaggerComponent(){
        return daggerComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        daggerComponent = buildDaggerComponent();
    }

    protected DaggerComponent buildDaggerComponent(){
        return DaggerDaggerComponent.builder()
                .moduleDB(new ModuleDB())
                .build();
    }
}
