package com.lerma.projectfinal.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.lerma.projectfinal.R
import com.lerma.projectfinal.adapters.TabAdapter
import com.lerma.projectfinal.databinding.FragmentProductBinding

class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = ArrayList<String>()
        options.add(getString(R.string.str_product))
        options.add(getString(R.string.str_car))

        binding.viewPager.adapter = TabAdapter(fragmentManager!!, lifecycle, options)
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = options[0]
                }
                1 -> {
                    tab.text = options[1]
                }
                else -> {
                    tab.text = ""
                }
            }
        }.attach()
    }
}