package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    int mode;
    String STATE_TITLE= "state_string";
    String STATE_LIST= "state_list";
    String STATE_MODE= "state_mode";
    ArrayList<Hero> list= new ArrayList<>();
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.recycler_heroes);
        recyclerView.setHasFixedSize(true);
        list.addAll(HeroesData.getListData());
        showRecyclerList();
        if(savedInstanceState== null ){
            setAction(title);
            list.addAll(HeroesData.getListData());
            showRecyclerList();
            mode= R.id.action_list;
        }
        else{
            title= savedInstanceState.getString(STATE_TITLE);
            ArrayList<Hero> statelist= savedInstanceState.getParcelableArrayList(STATE_LIST);
            int stateMode= savedInstanceState.getInt(STATE_MODE);
            setAction(title);
            if(statelist!=null){
                list.addAll(statelist);
            }
            setMode(stateMode);
        }
    }

    private void showRecyclerList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListheroAdapter listheroAdapter= new ListheroAdapter(list);
        recyclerView.setAdapter(listheroAdapter);
        setAction(title);
    }
    private void showRecyclerGrid(){
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        GridHeroAdapter gridHeroAdapter= new GridHeroAdapter(list);
        recyclerView.setAdapter(gridHeroAdapter);
        setAction(title);
    }
    private void showRescyclerCard(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardViewHeroAdapter cardViewHeroAdapter= new CardViewHeroAdapter(list);
        recyclerView.setAdapter(cardViewHeroAdapter);
        setAction(title);
        cardViewHeroAdapter.setOnItemClickCallback(new CardViewHeroAdapter.OnItemClickCallback() {
            @Override
            public void onItemCliked(Hero data) {
                Toast.makeText(MainActivity.this, data.getName() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void setMode(int selectMode){
        switch (selectMode){
            case R.id.action_list:
                showRecyclerList();
                title="list";
                break;
            case R.id.action_grid:
                showRecyclerGrid();
                title= "grid";
                break;
            case R.id.action_cardview:
                showRescyclerCard();
                title= "card";
                break;
        }
        mode= selectMode;
        setAction(title);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }
    public void setAction(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(STATE_TITLE, title);
        outState.putParcelableArrayList(STATE_LIST, list);
        outState.putInt(STATE_MODE, mode);
    }
}
