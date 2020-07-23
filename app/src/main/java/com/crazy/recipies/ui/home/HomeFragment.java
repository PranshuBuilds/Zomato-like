package com.crazy.recipies.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.crazy.recipies.MainActivity;
import com.crazy.recipies.OptionActivity;
import com.crazy.recipies.R;
import com.crazy.recipies.RecyclerViewClickInt;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RecyclerViewClickInt {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final int NUM_COLUMNS = 2;
    ArrayList<Dish> exampleList = new ArrayList<>();
    Toolbar toolbar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        exampleList.add(new Dish(R.drawable.pasta, "Pasta ssssssssssssssssgggggggggggg", "Pasta"));
        exampleList.add(new Dish(R.drawable.dosa, "Dosa aaaaaaaaaa aaaaaaaaaaa ", "Dosa"));
        exampleList.add(new Dish(R.drawable.header, "Vegetableeeeeeeeeeeeeee eeeeeeeeeeee eeeeeeeeeee", "Vegetable"));
        exampleList.add(new Dish(R.drawable.salad, "Saladdddddddddddddddddddddddddddddssssssssssssssss ssssssssss", "Salad"));
        exampleList.add(new Dish(R.drawable.soups, "Soupsssssssssssssssssss sssssssss aaaaaaaaaaa", "Soups"));
        exampleList.add(new Dish(R.drawable.rice, "Rice ssssssssssssssssgggggggggggg", "Rice"));
        exampleList.add(new Dish(R.drawable.dosa, "Dosa aaaaaaaaaa aaaaaaaaaaa ", "Dosa"));
        exampleList.add(new Dish(R.drawable.header, "Vegetableeeeeeeeeeeeeee eeeeeeeeeeee eeeeeeeeeee", "Vegetable"));


        mRecyclerView = root.findViewById(R.id.dish);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new DishAdapter(exampleList,HomeFragment.this);


        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return root;
    }


    @Override
    public void onItemClick(int position) {

        Toast.makeText(getContext(), "clicked"+exampleList.get(position).getImageResource(), Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getContext(), OptionActivity.class);
        i.putExtra("image",exampleList.get(position).getImageResource());
        i.putExtra("textmain",exampleList.get(position).getText2());
        i.putExtra("discription",exampleList.get(position).getText1());
        startActivity(i);
    }

    @Override
    public void onItemLongClick(int position) {

        Toast.makeText(getContext(), "clicked"+exampleList.get(position).getImageResource(), Toast.LENGTH_SHORT).show();
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(toggle.onOptionsItemSelected(item)){
//            return true;
//        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            final Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.dialog_about);
            dialog.setTitle("About");

            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_about) {
//            final Dialog dialog = new Dialog(getContext());
//            dialog.setContentView(R.layout.dialog_about);
//            dialog.setTitle("About");
//
//            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
//            // if button is clicked, close the custom dialog
//            dialogButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//
//            dialog.show();
//            return true;
//        }
//        return false;
//    }
}