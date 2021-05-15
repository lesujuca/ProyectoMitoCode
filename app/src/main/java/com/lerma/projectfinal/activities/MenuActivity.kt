package com.lerma.projectfinal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.lerma.projectfinal.Fragments.ExitFragment
import com.lerma.projectfinal.Fragments.HomeFragment
import com.lerma.projectfinal.Fragments.OrderFragment
import com.lerma.projectfinal.Fragments.ProductFragment
import com.lerma.projectfinal.R
import com.lerma.projectfinal.databinding.ActivityMenuBinding
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.toolbar.*

class MenuActivity : AppCompatActivity() {
    private lateinit var home: HomeFragment
    private lateinit var exit: ExitFragment
    private lateinit var product: ProductFragment
    private lateinit var order: OrderFragment

    private lateinit var binding : ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(toolBar)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolBar, 0, 0)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        HelloUser.text = getString(R.string.str_hello) + intent.getStringExtra("KeyName")?.uppercase() + " " + intent.getStringExtra("KeyLastName")?.uppercase()

        home = HomeFragment()
        changeFragment(home, R.string.str_home)

        setUpNavigation()
    }

    private fun setUpNavigation() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = false
            drawer_layout.closeDrawers()

            when (menuItem.itemId) {
                R.id.navHome -> {
                    home = HomeFragment()
                    changeFragment(home, R.string.str_home)
                }

                R.id.navProduct -> {
                    product = ProductFragment()
                    changeFragment(product, R.string.str_product)
                }

                R.id.navOrder -> {
                    order = OrderFragment()
                    changeFragment(order, R.string.str_order)
                }

                R.id.navExit -> {
                    exit = ExitFragment()
                    changeFragment(exit, R.string.str_exit)
                }

                else -> {
                    false
                }
            }

            true
        }
    }

    private fun changeFragment(fragment: Fragment, title: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerMenu, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        //toolBar.title = getString(title)
    }
}