package com.fraiman.zeev.karamath01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "appDB.db";

    public static final String EXERS_TABLE = "Exers";
    public static final String EXER_DATE = "Exer_date";
    public static final String EXER_TYPE = "FQ_type";
    public static final String COEF_A = "Coeff_a";
    public static final String COEF_B = "Coeff_b";
    public static final String COEF_C = "Coeff_c";

    public HelperDB(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String st2="CREATE TABLE "+EXERS_TABLE+" ( ";
        st2+=EXER_DATE+" TEXT, ";
        st2+=EXER_TYPE+" TEXT, ";
        st2+=COEF_A+" TEXT, ";
        st2+=COEF_B+" TEXT, ";
        st2+=COEF_C+" TEXT );";
        sqLiteDatabase.execSQL(st2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
