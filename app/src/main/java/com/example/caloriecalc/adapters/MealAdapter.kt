package com.example.caloriecalc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caloriecalc.R
import com.example.caloriecalc.data.Meal

class MealAdapter (
    private val meals: MutableList<Meal>,
    private val onAddProductClick: (Meal) -> Unit
) : RecyclerView.Adapter<MealAdapter.MealViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int ) {
        val meal = meals[position]
        holder.bind(meal)
    }

    override fun getItemCount(): Int = meals.size



    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mealNameTextView: TextView = itemView.findViewById(R.id.meal)
        private val mealCaloriesTextView: TextView = itemView.findViewById(R.id.meal_calories)
        private val addProductButton: ImageButton = itemView.findViewById(R.id.btn_add_meal)
        private val productsRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerProducts)
        val mealProteinTextView: TextView = itemView.findViewById(R.id.productProtein)
        val mealFatTextView: TextView = itemView.findViewById(R.id.productFat)
        val mealCarbsTextView: TextView = itemView.findViewById(R.id.productCarbs)

        fun bind(meal: Meal) {
            mealNameTextView.text = meal.name

            mealCaloriesTextView.text = "${String.format("%.2f", meal.getTotalCalories())} ккал"
            mealProteinTextView.text = "Б: ${String.format("%.2f", meal.getTotalProtein())} г"
            mealFatTextView.text = "Ж: ${String.format("%.2f", meal.getTotalFat())} г"
            mealCarbsTextView.text = "У: ${String.format("%.2f", meal.getTotalCarbs())} г"

            val productListAdapter = ProductListAdapter(meal.products)
            productsRecyclerView.apply {
                layoutManager = LinearLayoutManager(itemView.context)
                adapter = productListAdapter
            }

            addProductButton.setOnClickListener {
                onAddProductClick(meal)
            }
        }
    }
}