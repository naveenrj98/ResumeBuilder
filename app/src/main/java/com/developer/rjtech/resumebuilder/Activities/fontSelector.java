package com.developer.rjtech.resumebuilder.Activities;


import android.content.Context;
import android.graphics.Typeface;

public class fontSelector {
    public static Typeface font;
    private final Context context;

    public fontSelector(Context context) {
        this.context = context;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/Sansita-Regular.ttf");
    }
}
