package com.padhuga.tamil.games.models;

import android.graphics.drawable.Drawable;

//items stored in ListView
public class Item {
    public final Drawable ItemDrawable;
    public final String ItemString;

    public Item(Drawable drawable, String t) {
        ItemDrawable = drawable;
        ItemString = t;
    }
}
