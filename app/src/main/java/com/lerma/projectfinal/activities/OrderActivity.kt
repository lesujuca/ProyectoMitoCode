package com.lerma.projectfinal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lerma.projectfinal.databinding.ActivityOrderBinding
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrderBinding
    var orders: ArrayList<Order> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initViews()
    }

    private fun initViews() {
        ordersList.setHasFixedSize(true)
        ordersList.adapter = OrdersAdapter(this.orders, this)
    }

    private fun initData() {
        for (i in 1..20) {
            orders.add(Order("Pedido $i", "-->"))
        }
    }
}