package mm.com.fairway.themealdb.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mm.com.fairway.themealdb.api.ApiClient
import mm.com.fairway.themealdb.model.firstLetter.Letter
import mm.com.fairway.themealdb.model.ingredientFilter.IngredientList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IngredientViewModel:ViewModel() {
    private  var Result: MutableLiveData<IngredientList> = MutableLiveData()
    fun getLoadIngredientResult(): LiveData<IngredientList> = Result
    fun setLoadIngredientResult(ingredientName: String){
        var apiClient= ApiClient()
        var apiCall=apiClient.getIngredientMeal(ingredientName)
        apiCall.enqueue(object : Callback<IngredientList> {
            override fun onFailure(call: Call<IngredientList>, t: Throwable) {
                Log.d("LetterMealList>>>>>>",t.toString())
            }

            override fun onResponse(call: Call<IngredientList>, response: Response<IngredientList>) {
               Result.value = response.body()
            }

        })
    }
}