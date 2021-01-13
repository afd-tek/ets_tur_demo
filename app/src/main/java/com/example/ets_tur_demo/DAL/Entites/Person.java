package com.example.ets_tur_demo.DAL.Entites;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class Person {
    @PrimaryKey
    public int id;
    public String first_name;
    public String last_name;
    public String email;
    public String phone;
    public Date birthdate;
    public boolean is_active;
}
