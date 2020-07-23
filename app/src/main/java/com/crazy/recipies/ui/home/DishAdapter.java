package com.crazy.recipies.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.crazy.recipies.OptionActivity;
import com.crazy.recipies.R;
import com.crazy.recipies.RecyclerViewClickInt;

import java.util.ArrayList;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ExampleViewHolder> {

    private ArrayList<Dish> mExampleList;
    private static RecyclerViewClickInt recyclerViewClickInt;


    public DishAdapter(ArrayList<Dish> exampleList,RecyclerViewClickInt recyclerViewClickInt) {
        this.mExampleList = exampleList;
        this.recyclerViewClickInt=recyclerViewClickInt;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_layout, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Dish currentItem = mExampleList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    ///View holder
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view_upload);
            mTextView1 = itemView.findViewById(R.id.text_view_name);
            mTextView2 = itemView.findViewById(R.id.textViewOptions);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickInt.onItemClick(getAdapterPosition());

                }
            });
        }

    }
}