package mm.com.fairway.themealdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_categories_layout.view.*
import kotlinx.android.synthetic.main.item_letterlist_layout.view.*
import mm.com.fairway.themealdb.R
import mm.com.fairway.themealdb.model.categories.Category
import mm.com.fairway.themealdb.model.firstLetter.Meal

class LetterAdapter(var mealList: ArrayList<Meal> = ArrayList()):RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {
    var mClickListener: ClickListener? = null
  inner  class  LetterViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview),View.OnClickListener{
      lateinit var meal:Meal
      init {
          itemView.setOnClickListener(this)// first run this statement  , need to call
      }
        fun bind(meal: Meal){
            this.meal= meal
            itemView.letter_mealNametxt.text= meal.strMeal
            Picasso.get().load(meal.strMealThumb).into(itemView.letter_Img)
        }

      override fun onClick(v: View?) {
          mClickListener?.onClick(meal)
      }
  }
    fun setOnClickListener(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }
    fun updateLetter(mealList: ArrayList<Meal>) {
        this.mealList= mealList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        return LetterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_letterlist_layout,parent,false))
    }

    override fun getItemCount(): Int {
      return  mealList.size
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
      holder.bind(mealList.get(position))
    }
    interface ClickListener {
        fun onClick(meal: Meal)
    }
}