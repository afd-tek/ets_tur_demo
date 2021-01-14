package com.example.ets_tur_demo.Presentation.Views.Person;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.ets_tur_demo.BLL.Repositories.PersonRepository;
import com.example.ets_tur_demo.DAL.Entites.Person;
import com.example.ets_tur_demo.Presentation.Adapters.Product.CollectionViewAdapter;
import com.example.ets_tur_demo.Presentation.ViewModels.Generic.EtsViewModel;
import com.example.ets_tur_demo.Presentation.ViewModels.Person.CollectionViewModel;
import com.example.ets_tur_demo.Presentation.ViewModels.Generic.CollectionViewModelFactory;
import com.example.ets_tur_demo.R;

import java.sql.Date;

public class CollectionView extends AppCompatActivity {

    private CollectionViewModel collectionViewModel;
    private EditText et_search;
    private CollectionViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_collection_view);

        RecyclerView recyclerView = findViewById(R.id.rv_person_collection_view_people);
        adapter = new CollectionViewAdapter(new CollectionViewAdapter.PersonDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        et_search = findViewById(R.id.et_person_collection_view_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s != null && s.length() > 0){
                    collectionViewModel.searchPeople(String.format("%s%%",s)).observe(CollectionView.this, adapter::submitList);
                }else{
                    collectionViewModel.getPeople().observe(CollectionView.this, adapter::submitList);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.length() > 0){
                    collectionViewModel.searchPeople(String.format("%s%%",s)).observe(CollectionView.this, adapter::submitList);
                }else{
                    collectionViewModel.getPeople().observe(CollectionView.this, adapter::submitList);
                }
            }
        });

        collectionViewModel = new ViewModelProvider(this, new CollectionViewModelFactory(this.getApplication())).get(CollectionViewModel.class);

        collectionViewModel.getPeople().observe(this, adapter::submitList);

    }
}