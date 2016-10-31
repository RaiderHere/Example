package com.example.raider.test1;

import android.graphics.Bitmap;

/**
 * Created by RAIDER on 10.10.2016.
 */

class BitmapResizer {

    Bitmap getResizedBitmap(Bitmap bitmap, int targetWidth, int targetHeight) {
        return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true);
    }

    Bitmap getResizedBitmap(Bitmap bitmap, int targetWidth) {
        int bitmapWith = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int targetHeight = bitmapHeight * targetWidth / bitmapWith;
        return this.getResizedBitmap(bitmap, targetWidth, targetHeight);
    }

}
