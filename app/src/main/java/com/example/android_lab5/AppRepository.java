package com.example.android_lab5;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class AppRepository {

    private final AppDao appDao;
    private MutableLiveData<List<Cuisine>> cuisines = new MutableLiveData<>();
    private MutableLiveData<List<Restaurant>> restaurants = new MutableLiveData<>();
    // constructor for AppRepository
    AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        // init variables
        appDao = db.appDao();
    }

    public MutableLiveData<List<Cuisine>> getCuisines() {
        return cuisines;
    }

    public MutableLiveData<List<Restaurant>> getRestaurants() {
        return restaurants;
    }

    public void getAllCuisines(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Cuisine> c= appDao.getAll();
                if(c.isEmpty()){
                    appDao.insertAll(new Cuisine( 1,"Caprese Salad with Pesto Sauce"));
                    appDao.insertAll(new Restaurant("Scaddabush Italian Kitchen & Bar Scarborough", 1,"580 Progress Ave, Scarborough, ON M1P 2K2", 3.77764859115719, -79.25379223486107));
                    appDao.insertAll(new Restaurant("Nova Ristorante", 1,"2272 Lawrence Ave E #2, Scarborough, ON M1P 2P9", 43.749630782386184, -79.27758633061958));
                    // japanese
                    appDao.insertAll(new Cuisine( 2,"Sushi(壽司)"));
                    appDao.insertAll(new Restaurant("Sushi Legend Scarborough 糰長壽司", 2,"Chartwell Shopping Centre, 175 Commander Blvd unit 2, Scarborough, ON M1S 3M7",43.796731216643224, -79.26965083671064));
                    appDao.insertAll(new Restaurant("Umami House 鲜味屋", 2,"2038 Sheppard Ave E, North York, ON M2J 5B3", 43.775384916052204, -79.33036027294717));
                    // korean
                    appDao.insertAll(new Cuisine( 3,"Samgyetang (삼계탕)"));
                    appDao.insertAll(new Restaurant("The Nilgiris Restaurant", 3,"3021 Markham Rd #50, Scarborough, ON M1X 1L8", 43.829290436445575, -79.24872277294583));
                    appDao.insertAll(new Restaurant("Tutto Pronto Bayview", 3,"1551 Bayview Ave, East York, ON M4G 3B5", 43.70558507626752, -79.37483557294877));
                    // chinese
                    appDao.insertAll(new Cuisine(4,"Dumplings(饺子)"));
                    appDao.insertAll(new Restaurant("Perfect Chinese Restaurant", 4,"4386 Sheppard Ave E, Unit 1, Toronto, ON M1S 1T8", 43.78771157452607, -79.27055947299833));
                    appDao.insertAll(new Restaurant("May Yan Seafood Restaurant 陸福海鮮酒家", 4,"4227 Sheppard Ave E Unit B2, Scarborough, ON M1S 5H5", 43.784714174372574, -79.27770214416279));

                }
                cuisines.postValue(appDao.getAll());
            }
        }).start();
    }
    public void getRestaurants(int cuisineId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                restaurants.postValue(appDao.getRestaurantsByCuisineId(cuisineId));
            }
        }).start();
    }

}
