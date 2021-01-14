package com.example.ets_tur_demo.Presentation.Views.Person;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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
    private ImageButton btn_add;
    private CollectionViewAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_collection_view);

        recyclerView = findViewById(R.id.rv_person_collection_view_people);
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
                    recyclerView.setLayoutManager(new LinearLayoutManager(CollectionView.this,RecyclerView.HORIZONTAL,false));
                    collectionViewModel.searchPeople(String.format("%s%%",s)).observe(CollectionView.this, adapter::submitList);
                }else{
                    recyclerView.setLayoutManager(new LinearLayoutManager(CollectionView.this,RecyclerView.VERTICAL,false));
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

        btn_add = findViewById(R.id.btn_person_collection_view_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CollectionView.this,EditView.class));
            }
        });

        InitSwipe();
        collectionViewModel = new ViewModelProvider(this, new CollectionViewModelFactory(this.getApplication())).get(CollectionViewModel.class);

        collectionViewModel.getPeople().observe(this, adapter::submitList);

    }

    public void InitSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Person p = adapter.GetPerson(viewHolder.getAdapterPosition());
                collectionViewModel.Delete(p.id);
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(getLayoutInflater().inflate(R.layout.toast_success,findViewById(R.id.container_toast_success)));
                toast.show();
            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}