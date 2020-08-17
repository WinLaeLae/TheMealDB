package mm.com.fairway.themealdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meal.model.categorySearch.Meal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_search_by_category.view.*
import mm.com.fairway.themealdb.R
import mm.com.fairway.themealdb.fragment.CategoryListFragment

class CategorySearchAdapter (var categoryList :List<Meal> = ArrayList() ): RecyclerView.Adapter<CategorySearchAdapter.CategoryViewHolder>() {

    var catClickListener : ClickListener? =null

    interface ClickListener{
        fun onClick(meal: Meal)
    }

    fun setOnClickListener(clickListener : CategoryListFragment)
    {
        this.catClickListener= clickListener
    }
    inner class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener{

        lateinit var meal : Meal
        init {
            itemView.setOnClickListener(this)
        }
        fun bindCategory(meal: Meal)
        {
            this.meal = meal
            itemView.textSearchCategoryName.text=meal.strMeal
            Picasso.get().load(meal.strMealThumb).into(itemView.imageSearchCategory)
        }

        override fun onClick(p0: View?) {
            catClickListener?.onClick(meal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_search_by_category,parent,false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindCategory(categoryList[position])
    }

    fun updateResult(headerList : List<Meal>)
    {
        this.categoryList=headerList
        notifyDataSetChanged()
    }
}