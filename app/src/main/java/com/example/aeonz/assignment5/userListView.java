package com.example.aeonz.assignment5;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class userListView extends AppCompatActivity {

    private static final String TAG = "userListView";
    databaseHelper mDatabaseHelper;
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new databaseHelper(this);

        populateView();
    }

    private void populateView(){
        Log.d(TAG, "Displaying items in list View");

        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listUser = new ArrayList<>();
        while (data.moveToNext()) {
            listUser.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listUser);
        mListView.setAdapter(adapter);
    }

}
