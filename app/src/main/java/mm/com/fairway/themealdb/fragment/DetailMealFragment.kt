package mm.com.fairway.themealdb.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detail_meal.*
import mm.com.fairway.themealdb.R

 
class DetailMealFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       var mealarg  = arguments?.let { DetailMealFragmentArgs.fromBundle(it)  }
        var mealid =mealarg?.randomId
        Log.d("detail",mealid)
        mealID.text = mealid
    }
}