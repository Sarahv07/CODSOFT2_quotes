package com.sneha.quotes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.sneha.quotes.DatabaseHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.sneha.quotes.QuoteData;

public class QuoteProvider {

    private static final String DATABASE_NAME = "FavoriteQuotes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String FAVORITE_QUOTES_TABLE = "favorite_quotes";
    private static final String COLUMN_QUOTE = "quote";

    private final DatabaseHelper dbHelper;

    private FavoriteAdapter adapter;

    public void setAdapter(FavoriteAdapter adapter) {
        this.adapter = adapter;
    }

    public QuoteProvider(Context context) {
        dbHelper = new DatabaseHelper(context);
    }




    public String getRandomQuote() {
        String[] quotes = {
                "The only way to do great work is to love what you do. - Steve Jobs",
                "In three words I can sum up everything I've learned about life: it goes on. - Robert Frost",
                "The future belongs to those who believe in the beauty of their dreams.- Eleanor Roosevelt",
                "The only limit to our realization of tomorrow will be our doubts of today. - Franklin D. Roosevelt",
                "The greatest glory in living lies not in never falling, but in rising every time we fall.- Nelson Mandela",
                "The only thing necessary for the triumph of evil is for good men to do nothing.- Edmund Burke",
                "Success is not final, failure is not fatal: it is the courage to continue that counts. - Winston Churchill",
                "In the end, we will remember not the words of our enemies, but the silence of our friends. - Martin Luther King Jr.",
                "To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment. - Ralph Waldo Emerson",
                "Life is what happens when you're busy making other plans. - John Lennon",
                "Genius is one percent inspiration and ninety-nine percent perspiration - Bruce Wayne"
        };

        Random random = new Random();
        int randomIndex = random.nextInt(quotes.length);
        return quotes[randomIndex];
    }


    public boolean toggleFavorite(String quote) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        boolean isFavorite = isQuoteFavorite(db, quote);

        try {
            if (isFavorite) {
                db.delete(FAVORITE_QUOTES_TABLE,
                        COLUMN_QUOTE + " = ?", new String[]{quote});
            } else {
                ContentValues values = new ContentValues();
                values.put(COLUMN_QUOTE, quote);
                db.insert(FAVORITE_QUOTES_TABLE, null, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

        return !isFavorite;
    }


    public boolean isQuoteFavorite(SQLiteDatabase db, String quote) {
        Cursor cursor = db.query(FAVORITE_QUOTES_TABLE,
                new String[]{COLUMN_QUOTE},
                COLUMN_QUOTE + " = ?", new String[]{quote},
                null, null, null);

        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        return isFavorite;
    }




    @SuppressLint("Range")
    public List<String> getFavoriteQuotes() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(FAVORITE_QUOTES_TABLE,
                new String[]{COLUMN_QUOTE}, null, null,
                null, null, null);

        List<String> favoriteQuotes = new ArrayList<>();
        while (cursor.moveToNext()) {
            favoriteQuotes.add(cursor.getString(cursor.getColumnIndex(COLUMN_QUOTE)));
        }

        cursor.close();
        db.close();
        return favoriteQuotes;
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
        }


    @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_TABLE_FAVORITE_QUOTES =
                    "CREATE TABLE " + FAVORITE_QUOTES_TABLE + " (" +
                            COLUMN_QUOTE + " TEXT PRIMARY KEY)";
            db.execSQL(CREATE_TABLE_FAVORITE_QUOTES);
        }
        

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + FAVORITE_QUOTES_TABLE);
            onCreate(db);
        }
    }
}
