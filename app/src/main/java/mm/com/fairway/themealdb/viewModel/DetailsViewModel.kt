package mm.com.fairway.themealdb.viewModel

import android.telecom.Call
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meal.model.Details.Details
import mm.com.fairway.themealdb.api.ApiClient
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel:ViewModel(){

    private var result:MutableLiveData<Details> = MutableLiveData()
    fun getResult():LiveData<Details> = result

    fun loadDetails(id:String)
    {
        var apiClient=ApiClient()
        var apiCall =apiClient.apiInterface.getDetails(id)

        apiCall.enqueue(object : Callback <Details> {
            override fun onFailure(call: retrofit2.Call<Details>, t: Throwable) {
                Log.d("Error>>>>>>",t.toString())
            }

            override fun onResponse(call: retrofit2.Call<Details>, response: Response<Details>) {
                result.value=response.body()
            }
        })
    }
}