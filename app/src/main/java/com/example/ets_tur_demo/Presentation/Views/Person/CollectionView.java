package com.example.ets_tur_demo.Presentation.Views.Person;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ets_tur_demo.BLL.Repositories.PersonRepository;
import com.example.ets_tur_demo.DAL.Entites.Person;
import com.example.ets_tur_demo.R;

import java.sql.Date;

public class CollectionView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_collection_view);
    }
}