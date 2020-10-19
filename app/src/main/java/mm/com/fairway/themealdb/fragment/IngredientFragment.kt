package mm.com.fairway.themealdb.fragment

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_ingredient.*
import kotlinx.android.synthetic.main.fragment_ingredient.choosetxt
import mm.com.fairway.themealdb.R
import mm.com.fairway.themealdb.adapter.IngredientAdapter
import mm.com.fairway.themealdb.model.ingredientFilter.IngredientList
import mm.com.fairway.themealdb.model.ingredientFilter.Meal
import mm.com.fairway.themealdb.model.ingredientList.IngredientsFoodList
import mm.com.fairway.themealdb.viewModel.IngredientViewModel
import mm.com.fairway.themealdb.viewModel.LetterViewModel


class IngredientFragment : Fragment(),IngredientAdapter.ClickListener , AdapterView.OnItemSelectedListener{
lateinit var  ingredientAdapter: IngredientAdapter
    var ingredientViewModel= IngredientViewModel()

var ingreListViewModel = LetterViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ingredientAdapter= IngredientAdapter()
        ingredientViewModel= ViewModelProvider(this).get(IngredientViewModel::class.java)
        ingreListViewModel = ViewModelProvider(this).get(LetterViewModel::class.java)
      observeIngreList()
        letterSpinner.onItemSelectedListener = this
        observeIngredient()
        recyclerview_Ingredient.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = ingredientAdapter
        }



        ingredientAdapter.setOnClickListener(this)
    }
    var ingreLists = ArrayList<String>()
fun observeIngreList(){
    ingreListViewModel.getIngredientLoad().observe(viewLifecycleOwner, Observer { ingreList ->
        var ingreList = ingreList.meals as ArrayList<mm.com.fairway.themealdb.model.ingredientList.Meal>
         var i = 0
        while (i < ingreList.size){
            this.ingreLists.add(ingreList.get(i).strIngredient)
            i++
        }
        val spinnerAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,ingreLists)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        letterSpinner.adapter = spinnerAdapter
    })
}
    override fun onResume() {
        super.onResume()
        ingreListViewModel.setIngredientLoad()

    }
  fun  observeIngredient(){
      ingredientViewModel.getLoadIngredientResult().observe(viewLifecycleOwner, Observer <IngredientList>{ ingredient ->
          ingredientAdapter.updateIngredient(
              ingredient.meals as ArrayList<Meal>
          )
      })
  }

    override fun onClick(meal: Meal) {
        var action= IngredientFragmentDirections.actionIngredientFragmentToDetailMealFragment(meal.idMeal)
   findNavController().navigate(action)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var ingredientName:String = parent?.getItemAtPosition(position).toString()
        ingredientViewModel.setLoadIngredientResult(ingredientName)


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        choosetxt.text = "Plese choose a Letter!!! "
    }
}