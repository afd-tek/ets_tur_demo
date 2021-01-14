package com.example.ets_tur_demo.Presentation.ViewModels.Generic;

import android.app.Application;

import androidx.lifecycle.ViewModel;

public class EtsViewModel extends ViewModel {
    private Application mApplication;
    public EtsViewModel(Application application) {
        mApplication = application;
    }
}
