package mm.com.fairway.themealdb.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mm.com.fairway.themealdb.api.ApiClient
import mm.com.fairway.themealdb.model.country.Country
import mm.com.fairway.themealdb.model.firstLetter.Letter
import mm.com.fairway.themealdb.model.ingredientList.IngredientsFoodList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LetterViewModel : ViewModel() {
    private var letterResult: MutableLiveData<Letter> = MutableLiveData()
    private var areaResult: MutableLiveData<Country> = MutableLiveData()
    private var ingredientResult: MutableLiveData<IngredientsFoodList> = MutableLiveData()
    private var apiClient = ApiClient()
    fun getIngredientLoad():LiveData<IngredientsFoodList> = ingredientResult
    fun setIngredientLoad(){
        var apiIngreCall= apiClient.getIngredientList()
        apiIngreCall.enqueue(object : Callback<IngredientsFoodList>{
            override fun onResponse(
                call: Call<IngredientsFoodList>,
                response: Response<IngredientsFoodList>
            ) {
                ingredientResult.value= response.body()
            }

            override fun onFailure(call: Call<IngredientsFoodList>, t: Throwable) {
                Log.d("IngredientList>>>>>>",t.toString())
            }

        })
    }
    fun getAreaLoad(): LiveData<Country> = areaResult
    fun setAreaLoad() {
        var apiAreaCall = apiClient.getArae()
        apiAreaCall.enqueue(object : Callback<Country> {
            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                areaResult.value = response.body()
            }

            override fun onFailure(call: Call<Country>, t: Throwable) {
                Log.d("AreaList>>>>>>", t.toString())
            }
        })
    }

    fun getLoadLetterResult(): LiveData<Letter> = letterResult
    fun setLoadLetterResult(letter: String) {
        //  var apiClient= ApiClient()
        var apiCall = apiClient.getLetterMealList(letter)
        apiCall.enqueue(object : Callback<Letter> {
            override fun onFailure(call: Call<Letter>, t: Throwable) {
                Log.d("LetterMealList>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<Letter>, response: Response<Letter>) {
                letterResult.value = response.body()
            }

        })
    }
}