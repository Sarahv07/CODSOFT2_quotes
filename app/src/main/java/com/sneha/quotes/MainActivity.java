package com.sneha.quotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private ImageButton favoritesButton;
    private QuoteProvider quoteProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteText);
        favoritesButton = findViewById(R.id.favoritesButton);
        quoteProvider = new QuoteProvider(this);

        displayRandomQuote();
    }

    public void displayRandomQuote() {
        String randomQuote = quoteProvider.getRandomQuote();
        quoteTextView.setText(randomQuote);
    }

    public void toggleFavorite(View view) {
        String currentQuote = quoteTextView.getText().toString();
        boolean isFavorite = quoteProvider.toggleFavorite(currentQuote);
        updateFavoritesButton(isFavorite);
    }


    private void updateFavoritesButton(boolean isFavorite) {
        if (isFavorite) {
            favoritesButton.setImageResource(R.drawable.ic_favorite_filled);
        } else {
            favoritesButton.setImageResource(R.drawable.ic_favorite_empty);
        }
    }

    public void shareQuote(View view) {
        String quote = quoteTextView.getText().toString();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, quote);
        startActivity(Intent.createChooser(shareIntent, "Share Quote"));
    }

    public void openFavoritesActivity(View view) {
        List<String> favoriteQuotes = quoteProvider.getFavoriteQuotes();
        QuoteData quoteData = new QuoteData(favoriteQuotes);

        Intent intent = new Intent(this, FavoritesActivity.class);
        intent.putExtra("quoteData", quoteData);
        startActivity(intent);
    }

}
