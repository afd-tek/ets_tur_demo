package com.example.ets_tur_demo.Presentation.ViewModels.Generic;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ets_tur_demo.Presentation.ViewModels.Person.CollectionViewModel;

public class CollectionViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;


    public CollectionViewModelFactory(Application application) {
        mApplication = application;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new CollectionViewModel(mApplication);
    }
}