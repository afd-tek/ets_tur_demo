package com.example.ets_tur_demo.DAL.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ets_tur_demo.DAL.Entites.Person;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface PersonDAO {
    @Insert
    void Insert(Person person);

    @Update
    void Update(Person person);

    @Query("update Person set is_active = 0 WHERE id = :id")
    void Delete(int id);

    @Query("select * from Person where id = :id and is_active = 1")
    Person GetById(int id);

    @Query("select * from Person where is_active = 1 order by first_name asc limit :pageSize offset :page*:pageSize")
    LiveData<List<Person>> GetAll(int page, int pageSize);

    @Query("select * from Person where (first_name like :query or last_name like :query) and is_active = 1 order by first_name")
    LiveData<List<Person>> GetByName(String query);
}
