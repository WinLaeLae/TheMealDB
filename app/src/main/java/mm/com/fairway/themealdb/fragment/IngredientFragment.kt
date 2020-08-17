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
import kotlinx.android.synthetic.main.fragment_ingredient.*
import mm.com.fairway.themealdb.R
import mm.com.fairway.themealdb.adapter.IngredientAdapter
import mm.com.fairway.themealdb.model.ingredientFilter.IngredientList
import mm.com.fairway.themealdb.model.ingredientFilter.Meal
import mm.com.fairway.themealdb.viewModel.IngredientViewModel


class IngredientFragment : Fragment(),IngredientAdapter.ClickListener{
lateinit var  ingredientAdapter: IngredientAdapter
    var ingredientViewModel= IngredientViewModel()
    var ingredientName:String = "chicken_breast"

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

        recyclerview_Ingredient.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = ingredientAdapter
        }
        ingredientViewModel= ViewModelProvider(this).get(IngredientViewModel::class.java)

       observeIngredient()
        ingredientAdapter.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        ingredientViewModel.setLoadIngredientResult(ingredientName)
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
}