package com.bignerdranch.android.weather.Dagger;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = ModuleDB.class)
@Singleton
public interface DaggerComponent {
    void  inject(Context context);
}
