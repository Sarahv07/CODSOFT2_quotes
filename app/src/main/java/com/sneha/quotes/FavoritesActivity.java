package com.sneha.quotes;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.sneha.quotes.QuoteData;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);


        Intent intent = getIntent();
        QuoteData quoteData = (QuoteData) intent.getSerializableExtra("quoteData");


        assert quoteData != null;
        List<String> favoriteQuotes = quoteData.getFavoriteQuotes();


        ListView favoritesListView = findViewById(R.id.favoritesListView);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, favoriteQuotes);

        favoritesListView.setAdapter(adapter);
    }
}



