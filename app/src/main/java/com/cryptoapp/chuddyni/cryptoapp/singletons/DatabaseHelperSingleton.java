package com.cryptoapp.chuddyni.cryptoapp.singletons;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cryptoapp.chuddyni.cryptoapp.models.rest.CoinFavoritesStructures;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class DatabaseHelperSingleton extends SQLiteOpenHelper {

    private static DatabaseHelperSingleton sInstance;

    private static final String DATABASE_NAME = "cryptoapp.db";
    private static final String DATABASE_TABLE = "favorites_list";
    private static final int DATABASE_VERSION = 1;
    private static final String FAVORITE_COINS_COL_0 = "ID";
    private static final String FAVORITE_COINS_COL_1 = "FAVORITES";
    private static final String DEFAULT_FAVORITE_COINS = "BTC,ETH,XRP,LTC,BCH";

    // Use singleton design pattern so there is only ever one DB object floating around
    public static synchronized DatabaseHelperSingleton getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new DatabaseHelperSingleton(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHelperSingleton(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Run this method when the DB Schema is upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    // Run this method the first time the DB is ever created
    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + FAVORITE_COINS_COL_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FAVORITE_COINS_COL_1 + " TEXT)");
        List<String> defaultCoinsList = new ArrayList<>(Arrays.asList(DEFAULT_FAVORITE_COINS.split(",")));
        Gson gson = new Gson();
        String favoritesListString = gson.toJson(defaultCoinsList);
        ContentValues defaultFavoriteCoins = new ContentValues();
        defaultFavoriteCoins.put(FAVORITE_COINS_COL_1, favoritesListString);
        db.insert(DATABASE_TABLE, null, defaultFavoriteCoins);
    }

    public CoinFavoritesStructures getFavorites() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select FAVORITES from " + DATABASE_TABLE, null);
        cursor.moveToPosition(0);
        String favoritesListString = cursor.getString(0);
        cursor.close();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> favoritesList = gson.fromJson(favoritesListString, type);
        HashMap<String, String> favoritesMap = new HashMap<>();
        for (int i = 0; i < favoritesList.size(); i++) {
            favoritesMap.put(favoritesList.get(i), favoritesList.get(i));
        }
        return new CoinFavoritesStructures(favoritesList, favoritesMap);
    }

    public void saveCoinFavorites(CoinFavoritesStructures favs) {
        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();
        String favoritesListString = gson.toJson(favs.favoriteList);
        ContentValues newCoinFavorites = new ContentValues();
        newCoinFavorites.put(FAVORITE_COINS_COL_1, favoritesListString);
        db.update(DATABASE_TABLE, newCoinFavorites, null, null);
    }
}
