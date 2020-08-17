package mm.com.fairway.themealdb.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_country.*
import mm.com.fairway.themealdb.R
import mm.com.fairway.themealdb.adapter.CountryAdapter
import mm.com.fairway.themealdb.model.countryMeals.CountryMeals
import mm.com.fairway.themealdb.model.countryMeals.Meal
import mm.com.fairway.themealdb.viewModel.CountryMealsViewModel


class CountryFragment : Fragment(),CountryAdapter.ClickListener {

    lateinit var  countryAdapter: CountryAdapter
    var countryMealsViewModel=CountryMealsViewModel()
            var countryName: String="Canadian"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryAdapter= CountryAdapter()
        countryAdapter.setOnClickListener(this)
        recyclerview_Country.apply {
            layoutManager= GridLayoutManager(context,2)
            adapter= countryAdapter
        }
        countryMealsViewModel= ViewModelProvider(this).get(CountryMealsViewModel::class.java)
observeCountry()
        countryMealsViewModel.setLoadCountryResult(countryName)
    }

 fun observeCountry(){
        countryMealsViewModel.getLoadCountryResult()
            .observe(viewLifecycleOwner, Observer<CountryMeals> { country ->
                countryAdapter.updateCountry(
                    country.meals as ArrayList<Meal>
                )
            })
    }
    override fun onClick(meal: Meal) {
       var action= CountryFragmentDirections.actionCountryFragmentToDetailMealFragment(meal.idMeal)
findNavController().navigate(action)
    }

}