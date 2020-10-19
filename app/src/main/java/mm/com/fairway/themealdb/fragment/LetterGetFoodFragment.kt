package mm.com.fairway.themealdb.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_letter_get_food.*
import mm.com.fairway.themealdb.R
import mm.com.fairway.themealdb.adapter.LetterAdapter
import mm.com.fairway.themealdb.model.firstLetter.Letter
import mm.com.fairway.themealdb.model.firstLetter.Meal
import mm.com.fairway.themealdb.viewModel.LetterViewModel


class LetterGetFoodFragment : Fragment(), LetterAdapter.ClickListener,
    AdapterView.OnItemSelectedListener {
    lateinit var letterAdapter: LetterAdapter
    var letterViewModel = LetterViewModel()

    // lateinit var firstLetter: String
   // var firstLetter: String = " "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_letter_get_food, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        letterAdapter = LetterAdapter()

        recyclerview_Letter.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = letterAdapter
        }
        letterViewModel = ViewModelProvider(this).get(LetterViewModel::class.java)
        //    val spinner= letterSpinner
        val alpha = R.array.letter
        Log.d("letter", alpha.toString())
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.letter,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        letterSpinner.adapter = spinnerAdapter
        letterSpinner.onItemSelectedListener = this

        observeLetter()

        letterAdapter.setOnClickListener(this)
    }

//    override fun onResume() {
//        super.onResume()
//        letterViewModel.setLoadLetterResult(firstLetter)
//    }

    override fun onClick(meal: Meal) {

        var action =
            LetterGetFoodFragmentDirections.actionLetterGetFoodFragmentToDetailMealFragment(meal.idMeal)
        findNavController().navigate(action)
    }

    fun observeLetter() {
        letterViewModel.getLoadLetterResult()
            .observe(viewLifecycleOwner, Observer<Letter> { letter ->
                letterAdapter.updateLetter(
                    letter.meals as ArrayList<Meal>
                )
                Log.d("Letter>>", letter.meals.get(0).toString())
            })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

      var  firstLetter = parent?.getItemAtPosition(position).toString()
        letterViewModel.setLoadLetterResult(firstLetter)
       // firstLetter= ""
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        choosetxt.text = "Plese choose a Letter!!! "
    }


}