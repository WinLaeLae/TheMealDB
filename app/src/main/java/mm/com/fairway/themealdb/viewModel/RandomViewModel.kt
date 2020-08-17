package mm.com.fairway.themealdb.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mm.com.fairway.themealdb.api.ApiClient
import mm.com.fairway.themealdb.model.random.Meal
import mm.com.fairway.themealdb.model.random.Random
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomViewModel : ViewModel() {
    private var randomResult:MutableLiveData<Random> = MutableLiveData()
    fun getLoadRandom():LiveData<Random> = randomResult
    fun setLoadRandom(){
        var apiClient= ApiClient()
        var apiCall = apiClient.getMealRandom()
        apiCall.enqueue(object : Callback<Random>{
            override fun onFailure(call: Call<Random>, t: Throwable) {
                Log.d("Category>>>>>>",t.toString())
            }

            override fun onResponse(call: Call<Random>, response: Response<Random>) {
                randomResult.value = response.body()
            }

        })
    }
}