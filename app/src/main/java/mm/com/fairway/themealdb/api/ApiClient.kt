package mm.com.fairway.themealdb.api

import mm.com.fairway.themealdb.model.categories.Categories
import mm.com.fairway.themealdb.model.firstLetter.Letter
import mm.com.fairway.themealdb.model.random.Random
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private  val BASE_URL="https://www.themealdb.com/api/json/v1/1/"
val apiInterface : MealsApiInterface
    init {
    var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    apiInterface= retrofit.create(MealsApiInterface::class.java)
}
    fun getMealRandom():Call<Random>{
        return apiInterface.getRandom()
    }
    fun getCategories():Call<Categories>{
        return apiInterface.getCategories()
    }
    fun getLetterMealList(latter:String):Call<Letter>{
        return apiInterface.getLetterMealList(latter)
    }

}