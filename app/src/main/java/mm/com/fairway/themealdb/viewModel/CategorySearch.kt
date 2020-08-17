package mm.com.fairway.themealdb.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meal.model.categorySearch.SearchByCategory
import mm.com.fairway.themealdb.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategorySearch : ViewModel(){

    private var result : MutableLiveData <SearchByCategory> = MutableLiveData()
     fun getResult() : LiveData <SearchByCategory> = result

    fun loadSearchCategory(categoryname: String) {
        var apiClient =ApiClient()
        var apiCall = apiClient.apiInterface.getSearchCategory(categoryname)

        apiCall.enqueue(object : Callback<SearchByCategory>{
            override fun onFailure(call: Call<SearchByCategory>, t: Throwable) {
                Log.d("Error >>>>>",t.toString())
            }

            override fun onResponse(
                call: Call<SearchByCategory>,
                response: Response<SearchByCategory>
            ) {
                result.value=response.body()
            }
        })
    }
}