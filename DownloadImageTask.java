package com.example.raider.triviaquiz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.widget.ImageSwitcher;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by RAIDER on 13.09.2016.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Drawable> {
    private ImageSwitcher questionImageSwitcher;
    private Context context;
    private int questionImageSwitcherHeight;


    public DownloadImageTask(Context context, ImageSwitcher questionImageSwitcher, int height) {
        this.context = context;
        this.questionImageSwitcher = questionImageSwitcher;
        this.questionImageSwitcherHeight = height;
    }

    @Override
    protected Drawable doInBackground(String... params) {
        String urlImageOfQuestion = params[0];
        Drawable questionImageDrawable;
        InputStream stream;
        int sampleSize = 1;
        try {
            stream = new URL(urlImageOfQuestion).openStream();
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            if (questionImageSwitcherHeight > 2) {
                BitmapFactory.decodeStream(stream, null, options);
                stream = new URL(urlImageOfQuestion).openStream();
                final int height = options.outHeight;
                if (height > questionImageSwitcherHeight) {
                    sampleSize = Math.round((float) height / (float) questionImageSwitcherHeight);
                    if ((!(sampleSize % 2 == 0)) && (sampleSize > 1)) {
                        --sampleSize;
                    }
                }
            }
            options.inSampleSize = sampleSize;
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeStream(stream, null, options);
            questionImageDrawable = new BitmapDrawable(context.getResources(), bitmap);
        } catch (IOException e) {
            e.printStackTrace();
            questionImageDrawable = ContextCompat.getDrawable(context, R.drawable.default_game_image);
        }
        return questionImageDrawable;
    }

    @Override
    protected void onPostExecute(Drawable drawable) {
        questionImageSwitcher.setImageDrawable(drawable);
    }
}
