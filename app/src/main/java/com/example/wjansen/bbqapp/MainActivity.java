package com.example.wjansen.bbqapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wjansen.bbqapp.domain.Recipe;
import com.example.wjansen.bbqapp.service.RecipeService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddRecipeActivity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshRecipesList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){

            case R.id.action_settings:
                return true;

            case R.id.action_new_recipe:
                goToAddRecipeActivity();
                return true;

            case R.id.action_clear_recipes:
                goToClearRecipeFileActivity();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void goToAddRecipeActivity(){
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivity(intent);
    }

    public void goToClearRecipeFileActivity(){
        RecipeService mRecipeService = new RecipeService(this);
        mRecipeService.clearRecipesFile();
        refreshRecipesList();
    }

    public void refreshRecipesList(){
        RecipeService mRecipeService = new RecipeService(this);
        List<String> recipeTitlesList = mRecipeService.getAllRecipeTitle();


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, new ArrayList<String>());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, android.R.id.text1, recipeTitlesList);
        ListView listview = (ListView) findViewById(R.id.recipes);
        listview.setAdapter(adapter);

        /*
        if(recipeList != null) {
            //textview.setText(list.toString());
            for (Recipe recipe : recipeList) {
                if (recipe != null){
                    listview.
                    //listview.add(recipe.getTitle());
                }
            }
        }
        */

    }
}
