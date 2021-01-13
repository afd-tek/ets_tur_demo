package com.example.ets_tur_demo.BLL.Validators;

import com.example.ets_tur_demo.BLL.Constants.Regex;
import com.example.ets_tur_demo.DAL.Entites.Person;

public class PersonValidator {

    public static boolean RuleForName(String name){
        return name.length() >= 2 && name.length() <= 20;
    }

    public static boolean RuleForEmail(String email){
        return email.length() <= 60 && Regex.VALID_EMAIL_ADDRESS_REGEX.matcher(email).find();
    }

    public static boolean RuleForPhone(String phone){
        return (phone.length() <= 13 && Regex.VALID_PHONE_NUMBER_REGEX.matcher(phone).find()) || phone.length() <= 20;
    }

    public static boolean PersonIsValid(Person person){
        return RuleForName(person.first_name) &&
                RuleForName(person.last_name) &&
                RuleForEmail(person.email) &&
                RuleForPhone(person.phone);
    }

}
