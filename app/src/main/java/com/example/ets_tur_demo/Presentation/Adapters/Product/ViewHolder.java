package com.example.ets_tur_demo.Presentation.Adapters.Product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ets_tur_demo.DAL.Entites.Person;
import com.example.ets_tur_demo.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView tv_name;
    private final TextView tv_birthdate;
    private final TextView tv_email;
    private final TextView tv_phone;
    private final TextView tv_note;
    private final LinearLayout cont_note;

    private ViewHolder(View itemView) {
        super(itemView);
        tv_name = itemView.findViewById(R.id.tv_person_view_name);
        tv_birthdate = itemView.findViewById(R.id.tv_person_view_birthdate);
        tv_email = itemView.findViewById(R.id.tv_person_view_email);
        tv_phone = itemView.findViewById(R.id.tv_person_view_phone);
        tv_note = itemView.findViewById(R.id.tv_person_view_note);
        cont_note = itemView.findViewById(R.id.container_person_view_note);
    }

    public void bind(Person person) {
        tv_name.setText(String.format("%s %s", person.first_name, person.last_name));
        tv_birthdate.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(person.birthdate));
        tv_email.setText(person.email);
        tv_phone.setText(person.phone);
        if(person.note != null && !person.note.isEmpty()){
            tv_note.setText(person.note);
            cont_note.setVisibility(View.VISIBLE);
        }else{
            cont_note.setVisibility(View.GONE);
        }
    }

    static ViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_view, parent, false);
        return new ViewHolder(view);
    }
}