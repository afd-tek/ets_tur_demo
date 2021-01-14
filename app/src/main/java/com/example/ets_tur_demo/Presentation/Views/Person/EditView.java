package com.example.ets_tur_demo.Presentation.Views.Person;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ets_tur_demo.BLL.Validators.PersonValidator;
import com.example.ets_tur_demo.DAL.Entites.Person;
import com.example.ets_tur_demo.Presentation.ViewModels.Generic.CollectionViewModelFactory;
import com.example.ets_tur_demo.Presentation.ViewModels.Person.CollectionViewModel;
import com.example.ets_tur_demo.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class EditView extends AppCompatActivity {

    private int person_id;
    private boolean isAddState;

    private EditText et_first_name,et_last_name,et_email,et_note;
    private MaskedEditText et_phone,et_birthdate;
    private CollectionViewModel collectionViewModel;

    TextView tv_first_name_valid,tv_last_name_valid,tv_email_valid,tv_note_valid,tv_birthdate_valid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_edit_view);

        collectionViewModel = new ViewModelProvider(this, new CollectionViewModelFactory(this.getApplication())).get(CollectionViewModel.class);

        et_first_name = findViewById(R.id.et_person_edit_view_name);
        et_last_name = findViewById(R.id.et_person_edit_view_surname);
        et_email = findViewById(R.id.et_person_edit_view_email);
        et_note = findViewById(R.id.et_person_edit_view_note);
        et_phone = findViewById(R.id.et_person_edit_view_phone);
        et_birthdate = findViewById(R.id.et_person_edit_view_birthdate);

        tv_first_name_valid = findViewById(R.id.tv_person_edit_view_name_validation);
        tv_last_name_valid = findViewById(R.id.tv_person_edit_view_surname_validation);
        tv_email_valid = findViewById(R.id.tv_person_edit_view_email_validation);
        tv_note_valid = findViewById(R.id.tv_person_edit_view_note_validation);
        tv_birthdate_valid = findViewById(R.id.tv_person_edit_view_birthdate_validation);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            person_id = bundle.getInt("person_id");
            if(person_id > 0) {
                collectionViewModel.GetById(person_id).observe(this, this::FillForm);
                isAddState = false;
            }
        }else {
            isAddState = true;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            person_id = bundle.getInt("person_id");
            if(person_id > 0) {
                collectionViewModel.GetById(person_id).observe(this, this::FillForm);
                isAddState = false;
            }
        }else {
            isAddState = true;
        }
    }

    private void FillForm(Person personToEdit) {
        et_first_name.setText(personToEdit.first_name);
        et_last_name.setText(personToEdit.last_name);
        et_birthdate.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(personToEdit.birthdate).replace("/",""));
        et_email.setText(personToEdit.email);
        et_phone.setText(personToEdit.phone.substring(1).replace(" ",""));
        et_note.setText(personToEdit.note);
    }


    public void OnClick(View view){
        if (view.getId() == R.id.btn_person_edit_view_back) {
            super.onBackPressed();
        }else if(view.getId() == R.id.btn_person_edit_view_submit){
            SubmitForm();
        }
    }

    private void SubmitForm() {
        boolean is_form_valid=true;
        Date birthdate = new Date();
        try {
            tv_birthdate_valid.setVisibility(View.INVISIBLE);
            birthdate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(et_birthdate.getText().toString());
        } catch (ParseException e) {
            tv_birthdate_valid.setVisibility(View.VISIBLE);
            is_form_valid = false;
        }
        Person personToAdd = new Person(et_first_name.getText().toString(),et_last_name.getText().toString(),et_email.getText().toString(),et_phone.getText().toString(),birthdate,et_note.getText().toString());

        tv_first_name_valid.setVisibility(PersonValidator.RuleForName(personToAdd.first_name) ? View.INVISIBLE : View.VISIBLE);
        is_form_valid = is_form_valid && PersonValidator.RuleForName(personToAdd.first_name);

        tv_last_name_valid.setVisibility(PersonValidator.RuleForName(personToAdd.last_name) ? View.INVISIBLE : View.VISIBLE);
        is_form_valid = is_form_valid && PersonValidator.RuleForName(personToAdd.last_name);

        tv_email_valid.setVisibility(PersonValidator.RuleForEmail(personToAdd.email) ? View.INVISIBLE : View.VISIBLE);
        is_form_valid = is_form_valid && PersonValidator.RuleForEmail(personToAdd.email);

        tv_note_valid.setVisibility(PersonValidator.RuleForNote(personToAdd.note) ? View.INVISIBLE : View.VISIBLE);
        is_form_valid = is_form_valid && PersonValidator.RuleForNote(personToAdd.note);

        if(is_form_valid && isAddState){
            collectionViewModel.Insert(personToAdd);
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(getLayoutInflater().inflate(R.layout.toast_success,findViewById(R.id.container_toast_success)));
            toast.show();
            super.onBackPressed();
        }else if(is_form_valid){
            personToAdd.id = person_id;
            collectionViewModel.Update(personToAdd);
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(getLayoutInflater().inflate(R.layout.toast_success,findViewById(R.id.container_toast_success)));
            toast.show();
            super.onBackPressed();
        }
    }
}