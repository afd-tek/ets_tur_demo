package com.example.ets_tur_demo.Presentation.ViewModels.Person;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ets_tur_demo.BLL.Repositories.PersonRepository;
import com.example.ets_tur_demo.DAL.Entites.Person;
import com.example.ets_tur_demo.Presentation.ViewModels.Generic.EtsViewModel;

import java.sql.Date;
import java.util.List;

public class CollectionViewModel extends AndroidViewModel {

    private PersonRepository mRepository;
    private final LiveData<List<Person>> mAllWords;

    public CollectionViewModel(Application application) {
        super(application);
        mRepository = new PersonRepository(application);

        mAllWords = mRepository.GetPeople(0,100);
    }

    public LiveData<List<Person>> getPeople() {
        return mAllWords;
    }

    public LiveData<List<Person>> searchPeople(String query) {
        return mRepository.GetByName(query);
    }

    public LiveData<Person> GetById(int id){
        return mRepository.GetById(id);
    }

    public void Update(Person person){
        mRepository.Update(person);
    }

    public void Insert(Person person) {
        mRepository.Insert(person);
    }
    public void Delete(int id) {
        mRepository.Delete(id);
    }
}
