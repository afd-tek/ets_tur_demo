package com.example.ets_tur_demo.Presentation.Adapters.Product;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.ets_tur_demo.DAL.Entites.Person;
import com.example.ets_tur_demo.Presentation.Views.Person.EditView;

public class CollectionViewAdapter extends ListAdapter<Person, ViewHolder> {

    private ViewGroup parentView;

    public CollectionViewAdapter(@NonNull DiffUtil.ItemCallback<Person> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentView = parent;
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person current = getItem(position);
        holder.bind(current);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentView.getContext(), EditView.class);
                intent.putExtra("person_id",current.id);
                parentView.getContext().startActivity(intent);
            }
        });
    }

    public Person GetPerson(int position){
        return getItem(position);
    }
    public static class PersonDiff extends DiffUtil.ItemCallback<Person> {

        @Override
        public boolean areItemsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
            return oldItem.id == newItem.id;
        }
    }
}