package mm.com.fairway.themealdb.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mm.com.fairway.themealdb.api.ApiClient
import mm.com.fairway.themealdb.model.categories.Categories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel :ViewModel() {
    private  var categoryResult: MutableLiveData<Categories> = MutableLiveData()
    fun getLoadResult():LiveData<Categories> = categoryResult
    fun setLoadResult(){
        var apiClient= ApiClient()
        var apiCall = apiClient.getCategories()
        apiCall.enqueue(object : Callback<Categories>{
            override fun onFailure(call: Call<Categories>, t: Throwable) {
                Log.d("Category>>>>>>",t.toString())
            }

            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
               categoryResult.value = response.body()
            }

        })
    }

}