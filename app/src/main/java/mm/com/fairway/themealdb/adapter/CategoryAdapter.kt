package mm.com.fairway.themealdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_categories_layout.view.*
import mm.com.fairway.themealdb.R
import mm.com.fairway.themealdb.model.categories.Categories
import mm.com.fairway.themealdb.model.categories.Category

class CategoryAdapter(var categorylist: ArrayList<Category> = ArrayList()) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var mClickListener: ClickListener? = null

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        lateinit var category: Category
        init {
            itemView.setOnClickListener(this)// first run this statement  , need to call
        }
        fun bind(category: Category) {
            this.category = category
            itemView.categoryName_txt.text = category.strCategory
            Picasso.get().load(category.strCategoryThumb).into(itemView.category_img)
        }

        override fun onClick(v: View?) {
            mClickListener?.onClick(category)
        }
    }

    fun setOnClickListener(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }

    fun updateCategory(categoryList: ArrayList<Category>) {
        this.categorylist = categoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_categories_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return categorylist.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categorylist.get(position))
    }

    interface ClickListener {
        fun onClick(category: Category)
    }
}