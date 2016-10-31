package com.example.raider.test1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by RAIDER on 07.10.2016.
 */

class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {
    private ArrayList<String> contPoster;
    private Context context;
    private int height;
    private int with;
    private static ClickListener clickListener;

    RVAdapter(ArrayList<String> contPoster, Context context, int height) {
        this.contPoster = contPoster;
        this.context = context;
        this.height = height;
        this.with = height;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new MyViewHolder(view);
    }

    private Bitmap readBitmapFromResourceUri (String stringUri) {
        InputStream stream = null;
        Bitmap bitmap = null;
        try {
            stream = context.getContentResolver().openInputStream(Uri.parse(stringUri));
            bitmap = BitmapFactory.decodeStream(stream);
        } catch (FileNotFoundException e) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.poster1);
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BitmapResizer resizer = new BitmapResizer();
        Bitmap bitmap = resizer.getResizedBitmap(
                readBitmapFromResourceUri(contPoster.get(position)), with, height);
        holder.poster.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return contPoster.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView poster;

        MyViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.newsImage);
            poster.setMaxHeight(height);
            poster.setMinimumHeight(height);
            poster.setMaxWidth(with);
            poster.setMinimumWidth(with);
            poster.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }


    }

    void setOnItemClickListener(ClickListener clickListener) {
        RVAdapter.clickListener = clickListener;
    }

    interface ClickListener {
        void onItemClick(int position, View view);
    }
}
