package com.example.wjansen.bbqapp.service;

import android.content.Context;
import android.util.Log;

import com.example.wjansen.bbqapp.domain.Recipe;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjansen on 3-10-2017.
 */

public class RecipeService {
    private Context mContext;

    public RecipeService(Context context){
        this.mContext = context;

    }

    public boolean saveRecipeToFile(Recipe recipe){
        List<Recipe> list = getAllRecipe();
        if(list == null) list = new ArrayList<Recipe>();
        list.add(recipe);
        String filename = "BBQSave";
        Gson gson = new Gson();
        String content = gson.toJson(list);
        FileOutputStream outputStream;

        try {
            outputStream = mContext.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Recipe  getRecipe(String recipeName){
        List<Recipe> list = new ArrayList<>();
        Gson gson = new Gson();
        String ret = "";

        try {
            InputStream inputStream = mContext.openFileInput("BBQSave");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        list = gson.fromJson(ret, List.class);

        for(Recipe recipe: list){
            if(recipe.getTitle() == recipeName) return recipe;
        }

        return new Recipe();
    }

    public List<String> getAllRecipeTitle() {
        List<String> stringlist = new ArrayList<>();
        List<Recipe> recipeList = getAllRecipe();

        for(Object recipe : recipeList){
            stringlist.add(recipe.toString());

        }

        return stringlist;
    }

    public List<Recipe> getAllRecipe(){
        List<Recipe> list = new ArrayList<>();
        Gson gson = new Gson();
        String ret = "";

        try {
            InputStream inputStream = mContext.openFileInput("BBQSave");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        list = gson.fromJson(ret, List.class);

        return list;
    }

    public boolean clearRecipesFile(){
        String filename = "BBQSave";
        String content = "";
        FileOutputStream outputStream;

        try {
            outputStream = mContext.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
