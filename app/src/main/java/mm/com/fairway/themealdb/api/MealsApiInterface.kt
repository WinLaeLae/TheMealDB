package mm.com.fairway.themealdb.api

import com.example.meal.model.Details.Details
import com.example.meal.model.categorySearch.SearchByCategory
import mm.com.fairway.themealdb.model.categories.Categories
import mm.com.fairway.themealdb.model.countryMeals.CountryMeals
import mm.com.fairway.themealdb.model.firstLetter.Letter
import mm.com.fairway.themealdb.model.ingredientFilter.IngredientList
import mm.com.fairway.themealdb.model.random.Random
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApiInterface {

    @GET("random.php")
    fun getRandom(): Call<Random>

    @GET("categories.php")
    fun getCategories(): Call<Categories>

    @GET("search.php")
    fun getLetterMealList(
        @Query("f") firstLetter: String
    ): Call<Letter>

    @GET("filter.php")
    fun getCountryMealsList(
        @Query("a") cauntry: String
    ): Call<CountryMeals>

    @GET("filter.php")
    fun getIngredientList(
        @Query("i") ingredient: String
    ): Call<IngredientList>

    @GET("lookup.php")
    fun getDetails(
        @Query("i") i: String
    ): Call<Details>

    @GET("filter.php")
    fun getSearchCategory(
        @Query("c") c :String
    ):Call<SearchByCategory>

}