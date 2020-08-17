package mm.com.fairway.themealdb.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.meal.model.Details.Details
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_meal.*
import mm.com.fairway.themealdb.R
import mm.com.fairway.themealdb.viewModel.DetailsViewModel

class DetailMealFragment : Fragment() {

    lateinit var detailsViewModel : DetailsViewModel
    lateinit var instruction :String
    lateinit var ingradients :String
    lateinit var youtubeUrl :String
    lateinit var categoryName :String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var item =arguments?.let {
            DetailMealFragmentArgs.fromBundle(it)
        }
        var id=item?.randomId
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        detailsViewModel.loadDetails(id!!)
        observeViewModel()

        btnInstruction.setOnClickListener{
            textUserInputHeader.text="Instruction"
            textUserInputDetails.text=instruction
        }
        btnIngredient.setOnClickListener {
            textUserInputHeader.text="Ingredients"
            textUserInputDetails.text=ingradients
        }
        btnYouTube.setOnClickListener (
            {
                val i= Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
                startActivity(i)
            }
        )
        imgDetailsBack.setOnClickListener {
            var action =DetailMealFragmentDirections.actionDetailMealFragmentToCategoryListFragment(categoryName)
            findNavController().navigate(action)
        }
    }
    fun observeViewModel()
    {
        detailsViewModel.getResult().observe(
            viewLifecycleOwner, Observer <Details>{ details->
                textStrMeal.text=details.meals[0].strMeal
                textStrCategory.text=details.meals[0].strCategory
                categoryName=details.meals[0].strCategory
                textStrRegion.text=details.meals[0].strArea
                Picasso.get().load(details.meals[0].strMealThumb).into(imgStrMealThumb)
                instruction=details.meals[0].strInstructions
                ingradients=details.meals[0].strIngredient1 +"  " +details.meals[0].strIngredient2+"  " +details.meals[0].strIngredient3 +"  " +details.meals[0].strIngredient4 +"  " +
                        details.meals[0].strIngredient5 +"  " + details.meals[0].strIngredient6 +"  " +details.meals[0].strIngredient7 +"  " +details.meals[0].strIngredient8+"  " +
                        details.meals[0].strIngredient9+"  " +details.meals[0].strIngredient10 +"  " +details.meals[0].strIngredient11 +"  " + details.meals[0].strIngredient13 +"  " +
                        details.meals[0].strIngredient14 +"  " +details.meals[0].strIngredient15 +"  " +details.meals[0].strIngredient16 +"  " +details.meals[0].strIngredient17+"  " +
                        details.meals[0].strIngredient18 +"  " +details.meals[0].strIngredient19 +"  " +details.meals[0].strIngredient20
                youtubeUrl=details.meals[0].strYoutube
                textUserInputDetails.text=instruction
                textUserInputHeader.text="Instruction"
            }
        )
    }
}