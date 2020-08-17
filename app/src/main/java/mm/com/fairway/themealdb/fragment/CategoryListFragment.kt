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
import com.example.meal.model.categorySearch.Meal
import com.example.meal.model.categorySearch.SearchByCategory
import kotlinx.android.synthetic.main.fragment_category_list.*
import mm.com.fairway.themealdb.R
import mm.com.fairway.themealdb.adapter.CategorySearchAdapter
import mm.com.fairway.themealdb.viewModel.CategorySearch

class CategoryListFragment : Fragment(), CategorySearchAdapter.ClickListener {

    lateinit var categoriesViewModel :CategorySearch
    lateinit var  categoryAdapter : CategorySearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter = CategorySearchAdapter()

        var item =arguments?.let {
            CategoryListFragmentArgs.fromBundle(it)
        }
        var categoryName= item?.strCategory
        categoriesViewModel=ViewModelProvider(this).get(CategorySearch::class.java)

        recyclerSearchCategory.apply {
            layoutManager= GridLayoutManager(context,2)
            adapter=categoryAdapter
        }
        categoryAdapter.setOnClickListener(this)
        categoriesViewModel.loadSearchCategory(categoryName!!)
        observeViewModel()
        textCategoryTitle.text=categoryName
        //activity?.actionBar?.title  = categoryName

        imgCateSearch.setOnClickListener {
            var action = CategoryListFragmentDirections.actionCategoryListFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }

    fun observeViewModel()
    {
        categoriesViewModel.getResult().observe(
            viewLifecycleOwner, Observer <SearchByCategory>{ categories->
                categoryAdapter.updateResult(categories.meals)
            }
        )
    }

    override fun onClick(meal: Meal) {
        var action = CategoryListFragmentDirections.actionCategoryListFragmentToDetailMealFragment(meal.idMeal)
        findNavController().navigate(action)
    }
}