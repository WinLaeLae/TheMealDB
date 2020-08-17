package mm.com.fairway.themealdb.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import mm.com.fairway.themealdb.R
import mm.com.fairway.themealdb.adapter.CategoryAdapter
import mm.com.fairway.themealdb.model.categories.Categories
import mm.com.fairway.themealdb.model.categories.Category
import mm.com.fairway.themealdb.model.random.Random
import mm.com.fairway.themealdb.viewModel.CategoryViewModel
import mm.com.fairway.themealdb.viewModel.RandomViewModel

class HomeFragment : Fragment(), CategoryAdapter.ClickListener {
    lateinit var categoryAdapter: CategoryAdapter
    var categoryViewModel = CategoryViewModel()
    var randomViewModel = RandomViewModel()
    var randomMealID: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryAdapter = CategoryAdapter()
//create viewmodel object
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        randomViewModel = ViewModelProvider(this).get(RandomViewModel::class.java)
        recyclerview_category.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = categoryAdapter
        }
        random_cardView.setOnClickListener {
            var action =
                HomeFragmentDirections.actionHomeFragmentToDetailMealFragment(randomMealID.toString())
            findNavController().navigate(action)
        }
        fistletter_btn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_letterGetFoodFragment)
        }
        country_btn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_countryFragment)
        }
        ingredient_btn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_ingredientFragment)
        }
        categoryAdapter.setOnClickListener(this)

//        recyclerview_category.setOnClickListener() {
//            categoryAdapter.setOnClickListener(this)
//        }
        observeCategory()
        observeRandom()
    }

    override fun onResume() {
        super.onResume()
        categoryViewModel.setLoadResult()
        randomViewModel.setLoadRandom()

    }

    fun observeRandom() {
        randomViewModel.getLoadRandom()
            .observe(viewLifecycleOwner, Observer<Random> { random ->
                randomMealID = random.meals[0].idMeal
                Log.d("RandomMealID>>>>", randomMealID.toString())
                random_mealNametxt.text = random.meals[0].strMeal
                Picasso.get().load(random.meals[0].strMealThumb).into(random_img)
            })
    }

    fun observeCategory() {
        categoryViewModel.getLoadResult()
            .observe(viewLifecycleOwner, Observer<Categories> { category ->
                categoryAdapter.updateCategory(
                    category.categories as ArrayList<Category>
                )
                Log.d("Category>>", category.categories.get(0).toString())
            })
    }

    override fun onClick(category: Category) {
        Log.d("CategoryList>>>>>", category.strCategory)
        var action =HomeFragmentDirections.actionHomeFragmentToCategoryListFragment(category.strCategory)
        findNavController().navigate(action)

    }
}