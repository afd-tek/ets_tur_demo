package com.example.ets_tur_demo.BLL.Constants;

import java.util.regex.Pattern;

public class Regex {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHONE_NUMBER_REGEX =
            Pattern.compile("(d{3}[ ]?)(d{3}[ ]?)(d{2}[ ]?)d{2}$", Pattern.CASE_INSENSITIVE);
}
