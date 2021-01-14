package com.example.ets_tur_demo.BLL.Repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.ets_tur_demo.BLL.Validators.PersonValidator;
import com.example.ets_tur_demo.DAL.DAOs.Database.ETSDatabase;
import com.example.ets_tur_demo.DAL.DAOs.PersonDAO;
import com.example.ets_tur_demo.DAL.Entites.Person;

import java.util.List;

public class PersonRepository {
    private PersonDAO mPersonDAO;

    public PersonRepository(Application application) {
        ETSDatabase db = ETSDatabase.getDatabase(application);
        mPersonDAO = db.personDAO();
        Log.e("PATH",
                db.getOpenHelper().getWritableDatabase().getPath());
    }

    public LiveData<List<Person>> GetPeople(int page,int page_size) {
        return mPersonDAO.GetAll(page,page_size);
    }

    public void Insert(Person person) {
        if(PersonValidator.PersonIsValid(person))
            ETSDatabase.databaseWriteExecutor.execute(() -> mPersonDAO.Insert(person));
    }

    public void Update(Person person) {
        if(PersonValidator.PersonIsValid(person))
            ETSDatabase.databaseWriteExecutor.execute(() -> mPersonDAO.Update(person));
    }

    public void Delete(int id) {
        ETSDatabase.databaseWriteExecutor.execute(() -> mPersonDAO.Delete(id));
    }

    public Person GetById(int id) {
        return mPersonDAO.GetById(id);
    }

    public LiveData<List<Person>> GetByName(String name){
        return mPersonDAO.GetByName(name);
    }

}
