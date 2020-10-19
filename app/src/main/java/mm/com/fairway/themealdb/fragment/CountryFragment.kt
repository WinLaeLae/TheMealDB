package mm.com.fairway.themealdb.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_country.*
import kotlinx.android.synthetic.main.fragment_country.choosetxt
import kotlinx.android.synthetic.main.fragment_letter_get_food.*
import mm.com.fairway.themealdb.R
import mm.com.fairway.themealdb.adapter.CountryAdapter
import mm.com.fairway.themealdb.model.country.Country
import mm.com.fairway.themealdb.model.countryMeals.CountryMeals
import mm.com.fairway.themealdb.model.countryMeals.Meal
import mm.com.fairway.themealdb.viewModel.CountryMealsViewModel
import mm.com.fairway.themealdb.viewModel.LetterViewModel


class CountryFragment : Fragment(), CountryAdapter.ClickListener,
    AdapterView.OnItemSelectedListener {

    lateinit var countryAdapter: CountryAdapter
    var countryMealsViewModel = CountryMealsViewModel()
    var areaListViewModel = LetterViewModel()

    //var countryName: String = "Canadian"
    var areaLists  = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onResume() {
        super.onResume()
        areaListViewModel.setAreaLoad()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        areaListViewModel = ViewModelProvider(this).get(LetterViewModel::class.java)
        countryMealsViewModel = ViewModelProvider(this).get(CountryMealsViewModel::class.java)
        countryAdapter = CountryAdapter()
        observeAreaList()

        countrySpinner.onItemSelectedListener = this

        observeCountry()
        recyclerview_Country.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = countryAdapter
        }
        countryAdapter.setOnClickListener(this)
    }

    fun observeAreaList() {
        areaListViewModel.getAreaLoad().observe(viewLifecycleOwner, Observer { area ->
            var areaList = area.meals as ArrayList<mm.com.fairway.themealdb.model.country.Meal>
            Log.d("areaList<<<<<", areaList.toString())
            var i = 0
            while (i < areaList.size) {
                this.areaLists.add( areaList.get(i).strArea)
                i++
            }
            val spinnerAdapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_spinner_item,areaLists
            )
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            countrySpinner.adapter = spinnerAdapter
            Log.d("list",this.areaLists.get(0).toString())
            Log.d("list",this.areaLists.size.toString())
            Log.d("list",this.areaLists.toString())
        })

    }

    fun observeCountry() {
        countryMealsViewModel.getLoadCountryResult()
            .observe(viewLifecycleOwner, Observer<CountryMeals> { country ->
                countryAdapter.updateCountry(
                    country.meals as ArrayList<Meal>
                )
            })
    }

    override fun onClick(meal: Meal) {
        var action =
            CountryFragmentDirections.actionCountryFragmentToDetailMealFragment(meal.idMeal)
        findNavController().navigate(action)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var countryName = parent?.getItemAtPosition(position).toString()
        countryMealsViewModel.setLoadCountryResult(countryName)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        choosetxt.text = "Plese choose a Letter!!! "
    }

}