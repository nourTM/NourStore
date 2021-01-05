package com.example.singlescreenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView items ;
    ItemAdapter itemAdapter;
    ArrayList<Item> itemsList;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = findViewById(R.id.items);

        itemsList = new ArrayList<>();
        itemsList.add(
          new Item("Title1",R.drawable.clothes1,1500)
        );

         itemsList.add(
                 new Item("Title2",R.drawable.clothes2,2000)
         );

         itemsList.add(
                 new Item("Title3",R.drawable.clothes1,1000)
         );

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                (this, LinearLayoutManager.HORIZONTAL,false);
        items.setLayoutManager(linearLayoutManager);
        items.setHasFixedSize(true);

        itemAdapter = new ItemAdapter(itemsList,this);

        items.setAdapter(itemAdapter);

        // snapping the scroll items
         final GravitySnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
         snapHelper.attachToRecyclerView(items);

         // set a timer for default item
         final Handler handler = new Handler();
         handler.postDelayed(new Runnable() {
             @Override
             public void run() {
                 // Do something after 100  ms
                 RecyclerView.ViewHolder viewHolderDefault = items.findViewHolderForAdapterPosition(0);
                 LinearLayout itemrootDefault = viewHolderDefault.itemView.findViewById(R.id.item_root);
                 itemrootDefault.animate().scaleY(1).scaleX(1).setDuration(350).setInterpolator(new AccelerateInterpolator()).start();

             }
         },100);
         // add animate scroll
         items.addOnScrollListener(new RecyclerView.OnScrollListener() {
             @Override
             public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                 super.onScrollStateChanged(recyclerView, newState);

                 if (newState == RecyclerView.SCROLL_STATE_IDLE){
                     View view = snapHelper.findSnapView(linearLayoutManager);
                     int pos = linearLayoutManager.getPosition(view);

                     RecyclerView.ViewHolder viewHolder =
                             items.findViewHolderForAdapterPosition(pos);
                     LinearLayout itemRoot = viewHolder.itemView.findViewById(R.id.item_root);
                     itemRoot.animate().scaleY(1).scaleX(1).setDuration(250)
                             .setInterpolator(new AccelerateInterpolator()).start();

                 }
                 else{
                     View view = snapHelper.findSnapView(linearLayoutManager);
                     int pos = linearLayoutManager.getPosition(view);

                     RecyclerView.ViewHolder viewHolder =
                             items.findViewHolderForAdapterPosition(pos);
                     LinearLayout itemRoot = viewHolder.itemView.findViewById(R.id.item_root);
                     itemRoot.animate().scaleY(0.7f).scaleX(0.7f).setDuration(250)
                             .setInterpolator(new AccelerateInterpolator()).start();
                 }
             }

             @Override
             public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                 super.onScrolled(recyclerView, dx, dy);
             }
         });

    }
}