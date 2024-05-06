/*
package com.example.mealsafari.ui.fragment.bottomsheet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import coil.load
import com.bumptech.glide.Glide
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.FragmentMealBottomSheeetBinding
import com.example.mealsafari.ui.fragment.HomeFragment.Companion.CATEGORY_NAME
import com.example.mealsafari.ui.fragment.HomeFragment.Companion.MEAL_AREA
import com.example.mealsafari.ui.fragment.HomeFragment.Companion.MEAL_NAME
import com.example.mealsafari.ui.fragment.HomeFragment.Companion.MEAL_STR
import com.example.mealsafari.ui.fragment.HomeFragment.Companion.MEAL_THUMB
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


private const val MEAL_ID = "param1"

class MealBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMealBottomSheeetBinding
    private var melaId: String? = null
    private lateinit var viewModel: MealViewModel

    private var mealName = ""
    private var mealId = ""
    private var mealImg = ""
    private var mealCountry = ""
    private var mealCategory = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
        val b = arguments
        mealName = b!!.getString(MEAL_NAME).toString()
        mealId = b!!.getString(MEAL_ID).toString()
        mealImg = b!!.getString(MEAL_THUMB).toString()
        mealCategory = b!!.getString(CATEGORY_NAME).toString()
        mealCountry = b!!.getString(MEAL_AREA).toString()
        arguments?.let {
            melaId = it.getString(MEAL_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealBottomSheeetBinding.inflate(inflater)
        //val v = LayoutInflater.from(context).inflate(R.layout.fragment_meal_bottom_sheeet, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        melaId?.let { viewModel.getMealById(it) }
        prepareView(view)
        view.setOnClickListener {
            val intent = Intent(context, MealBottomSheetFragment::class.java)
            intent.putExtra(MEAL_ID,mealId)
            intent.putExtra(MEAL_STR,mealName)
            intent.putExtra(MEAL_THUMB,mealImg)
            startActivity(intent)
        }

        observeBottomSheetMeal()
    }

   private fun observeBottomSheetMeal() {
        viewModel.observeMealBottomSheet().observe(viewLifecycleOwner, Observer { meal ->
        //binding.imageBottomSheet.load(meal[0].strMealThumb)
            Glide.with(this).load(meal[0].strMealThumb).into(binding.imageBottomSheet)
            binding.tvBottomSheetArea.text = meal[0].strArea
            binding.tvBottomSheetCategory.text = meal[0].strCategory
            binding.tvMealNameInBtmsheet.text = meal[0].strMeal



        })
    }

    fun prepareView(view:View){
        val tvMealName = view.findViewById<TextView>(R.id.tv_meal_name_in_btmsheet)
        val tvMealCategory = view.findViewById<TextView>(R.id.tv_bottom_sheet_category)
        val tvMealCountry = view.findViewById<TextView>(R.id.tv_bottom_sheet_area)
        val imgMeal = view.findViewById<ImageView>(R.id.img_category)

        Glide.with(view)
            .load(mealImg)
            .into(imgMeal)
        tvMealName.text = mealName
        tvMealCategory.text = mealCategory
        tvMealCountry.text = mealCountry
    }
}*/
