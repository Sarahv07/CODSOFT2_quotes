package com.sneha.quotes;

import java.io.Serializable;
import java.util.List;

public class QuoteData implements Serializable {
    private final List<String> favoriteQuotes;

    public QuoteData(List<String> favoriteQuotes) {
        this.favoriteQuotes = favoriteQuotes;
    }

    public List<String> getFavoriteQuotes() {
        return favoriteQuotes;
    }
}
