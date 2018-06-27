package com.eltrio723.recetario;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.eltrio723.recetario.database.RecipeRepository;
import com.eltrio723.recetario.local.RecipeDataSource;
import com.eltrio723.recetario.local.RecipeDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RecipeManager {
    private static final RecipeManager ourInstance = new RecipeManager();

    private List<Recipe> recipeList;
    private Context context;
    private int next_id;

    private CompositeDisposable compositeDisposable;
    private RecipeRepository recipeRepository;
    private RecipeDatabase recipeDatabase;

    private Recipe temporalRecipe;

    public static RecipeManager getInstance() {
        return ourInstance;
    }

    private RecipeManager() {
        recipeList = new ArrayList<Recipe>();
        temporalRecipe = new Recipe();
        compositeDisposable = new CompositeDisposable();
    }

    public void init(Context context){
        this.context = context;
        recipeDatabase = RecipeDatabase.getInstance(context);
        recipeRepository = RecipeRepository.getInstance(RecipeDataSource.getInstance(recipeDatabase.recipeDAO()));
        loadData();
    }


    List<Recipe> getRecipeList(){
        return recipeList;
    }

    void setRecipes(List<Recipe> recipes){
        this.recipeList = recipes;
    }

    Recipe getRecipeOfIndex(int i){
        return recipeList.get(i);
    }



    void addRecipe(final Recipe recipe){
        
        Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception{
                recipe.setId(next_id);
                next_id++;
                recipeRepository.insertRecipe(recipe);
                e.onComplete();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object o) throws Exception {
                                   Toast.makeText(context, R.string.recipe_added, Toast.LENGTH_SHORT).show();
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Toast.makeText(context, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                               }
                           },
                        new Action() {
                               @Override
                               public void run() throws Exception {
                                    loadData();
                               }
                           }

                );
    }

    void deleteRecipe(final Recipe recipe){
        Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception{
                recipeRepository.deleteRecipe(recipe);
                e.onComplete();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object o) throws Exception {
                                   Toast.makeText(context, R.string.recipe_deleted, Toast.LENGTH_SHORT).show();
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Toast.makeText(context, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                               }
                           },
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                loadData();
                            }
                        }

                );
    }

    void updateRecipe(final Recipe recipe){
        Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception{
                recipeRepository.updateRecipe(recipe);
                e.onComplete();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object o) throws Exception {
                                   Toast.makeText(context, R.string.recipe_updated, Toast.LENGTH_SHORT).show();
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Toast.makeText(context, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                               }
                           },
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                loadData();
                            }
                        }

                );
    }

    void deleteAllRecipes(){
        Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception{
                recipeRepository.deleteAllRecipes();
                e.onComplete();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object o) throws Exception {
                                   Toast.makeText(context, R.string.all_recipes_deleted, Toast.LENGTH_SHORT).show();
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Toast.makeText(context, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                               }
                           },
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                loadData();
                            }
                        }

                );
    }

    Context getContext(){
        return context;
    }

    /*void storeRecipes(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(recipeList);
        prefsEditor.putString(STORED_RECIPES_KEY, json);
        prefsEditor.putInt(STORED_NEXT_ID_KEY, next_id);
        prefsEditor.apply();
    }*/

    /*void loadRecipes(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(STORED_RECIPES_KEY,"");
        next_id = sharedPreferences.getInt(STORED_NEXT_ID_KEY,-1);
        Type type = new TypeToken<List<Recipe>>(){}.getType();
        this.recipeList = gson.fromJson(json, type);
        if(recipeList == null)
            recipeList = new ArrayList<Recipe>();
    }*/

    private void loadData(){
        Disposable disposable = recipeRepository.getAllRecipes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Recipe>>() {
                    @Override
                    public void accept(List<Recipe> recipes) throws Exception {
                        onGetAllRecipesSuccess(recipes);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(context,""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void onGetAllRecipesSuccess(List<Recipe> recipes) {
        this.recipeList.clear();
        this.recipeList.addAll(recipes);
        next_id = recipeList.get(recipeList.size()-1).getId()+1;
    }

    public void storeTempRecipe(Recipe r){
        temporalRecipe = r;
    }

    public Recipe loadTempRecipe(){
        return temporalRecipe;
    }

}
