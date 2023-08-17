package com.sneha.quotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class FavoriteAdapter extends ArrayAdapter<String> {

    public FavoriteAdapter(Context context, List<String> favoriteQuotes) {
        super(context, 0, favoriteQuotes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_favorites, parent, false);
        }

        String favoriteQuote = getItem(position);

        TextView favoriteQuoteTextView = convertView.findViewById(R.id.favoriteQuoteTextView);
        favoriteQuoteTextView.setText(favoriteQuote);



        return convertView;
    }
}

