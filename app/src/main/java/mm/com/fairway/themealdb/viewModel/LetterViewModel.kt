package mm.com.fairway.themealdb.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mm.com.fairway.themealdb.api.ApiClient
import mm.com.fairway.themealdb.model.firstLetter.Letter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LetterViewModel  : ViewModel(){
    private  var letterResult:MutableLiveData<Letter> = MutableLiveData()
    fun getLoadLetterResult():LiveData<Letter> = letterResult
    fun setLoadLetterResult(letter: String){
        var apiClient= ApiClient()
        var apiCall=apiClient.getLetterMealList(letter)
        apiCall.enqueue(object : Callback<Letter>{
            override fun onFailure(call: Call<Letter>, t: Throwable) {
                Log.d("LetterMealList>>>>>>",t.toString())
            }

            override fun onResponse(call: Call<Letter>, response: Response<Letter>) {
                letterResult.value = response.body()
            }

        })
    }
}