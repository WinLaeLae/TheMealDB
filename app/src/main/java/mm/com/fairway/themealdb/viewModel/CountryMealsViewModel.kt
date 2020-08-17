package mm.com.fairway.themealdb.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mm.com.fairway.themealdb.api.ApiClient
import mm.com.fairway.themealdb.model.countryMeals.CountryMeals
import mm.com.fairway.themealdb.model.firstLetter.Letter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryMealsViewModel : ViewModel() {
    private  var letterResult: MutableLiveData<CountryMeals> = MutableLiveData()
    fun getLoadCountryResult(): LiveData<CountryMeals> = letterResult
    fun setLoadCountryResult(countryName: String){
        var apiClient= ApiClient()
        var apiCall=apiClient.getCountryMeal(countryName)
        apiCall.enqueue(object : Callback<CountryMeals> {
            override fun onFailure(call: Call<CountryMeals>, t: Throwable) {
                Log.d("LetterMealList>>>>>>",t.toString())
            }

            override fun onResponse(call: Call<CountryMeals>, response: Response<CountryMeals>) {
                letterResult.value = response.body()
            }

        })
    }
}
