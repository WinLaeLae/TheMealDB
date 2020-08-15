package mm.com.fairway.themealdb.api

import mm.com.fairway.themealdb.model.random.Random
import retrofit2.Call
import retrofit2.http.GET

interface MealsApiInterface {

    @GET("random.php")
    fun getRandom():Call<Random>

}