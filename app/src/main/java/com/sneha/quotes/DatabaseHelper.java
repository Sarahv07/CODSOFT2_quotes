package com.sneha.quotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "FavoriteQuotes.db";
    private static final int DATABASE_VERSION = 1;
    static final String FAVORITE_QUOTES_TABLE = "favorite_quotes";
    static final String COLUMN_QUOTE = "quote";

    private static final String CREATE_TABLE_FAVORITE_QUOTES =
            "CREATE TABLE " + FAVORITE_QUOTES_TABLE + " (" +
                    COLUMN_QUOTE + " TEXT PRIMARY KEY)";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAVORITE_QUOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FAVORITE_QUOTES_TABLE);
        onCreate(db);
    }
}
