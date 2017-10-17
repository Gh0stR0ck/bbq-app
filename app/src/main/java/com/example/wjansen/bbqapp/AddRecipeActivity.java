package com.example.wjansen.bbqapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.view.View;
import android.widget.EditText;

import com.example.wjansen.bbqapp.service.RecipeService;
import com.example.wjansen.bbqapp.domain.Recipe;

public class AddRecipeActivity extends AppCompatActivity {
    private RecipeService mRecipeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        mRecipeService = new RecipeService(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipe recipe = new Recipe();

                EditText addTitle = (EditText)findViewById(R.id.addtitle);
                recipe.setTitle(addTitle.getText().toString());

                EditText addText = (EditText)findViewById(R.id.addtext);
                recipe.setText(addText.getText().toString());

                mRecipeService.saveRecipeToFile(recipe);
                finish();
            }
        });
    }

}
