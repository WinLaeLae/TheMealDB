package mm.com.fairway.themealdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_letterlist_layout.view.*
import mm.com.fairway.themealdb.R
import mm.com.fairway.themealdb.model.countryMeals.Meal


class CountryAdapter(var mealList: ArrayList<Meal> = ArrayList()):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(){
    var mClickListener: ClickListener? = null
  inner  class CountryViewHolder(itemView:View):RecyclerView.ViewHolder(itemView), View.OnClickListener{
       lateinit var meal: Meal
      init {
          itemView.setOnClickListener(this)// first run this statement  , need to call
      }
        fun bind(meal:Meal){
            this.meal=meal
            itemView.letter_mealNametxt.text= meal.strMeal
            Picasso.get().load(meal.strMealThumb).into(itemView.letter_Img)
        }

        override fun onClick(v: View?) {
            mClickListener?.onClick(meal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_letterlist_layout, parent, false)
        )
    }
    fun setOnClickListener(clickListener: ClickListener) {
        this.mClickListener= clickListener
    }
    fun updateCountry(mealList: ArrayList<Meal>) {
        this.mealList= mealList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return  mealList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(mealList.get(position))
    }
    interface ClickListener {
        fun onClick(meal:Meal)
    }
}