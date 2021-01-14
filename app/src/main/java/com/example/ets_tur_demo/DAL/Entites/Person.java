package com.example.ets_tur_demo.DAL.Entites;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.ets_tur_demo.BLL.Validators.PersonValidator;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Entity
public class Person{
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String first_name;
    public String last_name;
    public String email;
    public String phone;
    public Date birthdate;
    public boolean is_active;
    public String note;


    public Person(String first_name, String last_name, String email, String phone, Date birthdate, String note) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.is_active = true;
        this.note = note;
    }
}
