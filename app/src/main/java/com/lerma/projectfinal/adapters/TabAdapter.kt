package com.lerma.projectfinal.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lerma.projectfinal.Fragments.products.CarListFragment
import com.lerma.projectfinal.Fragments.products.ProductListFragment

class TabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, val options:List<String>) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return options.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                ProductListFragment()
            }
            1 -> {
                CarListFragment()
            }
            else -> {
                ProductListFragment()
            }
        }
    }
}