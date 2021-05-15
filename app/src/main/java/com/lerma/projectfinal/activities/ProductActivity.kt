package com.lerma.projectfinal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.lerma.projectfinal.databinding.ActivityProductBinding
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductBinding
    var products: ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initViews()
    }

    private fun initViews() {
        productsList2.setHasFixedSize(true)
        productsList2.layoutManager = GridLayoutManager(this, 2)
        productsList2.adapter = ProductsAdapter(this.products, this)
    }

    private fun initData() {
        for (i in 1..5) {
            //products.add(Product("Producto $i", "https://promart.vteximg.com.br/arquivos/ids/404224-1000-1000/107481.jpg?v=637177415555570000", "Descripción $i"))
        }
    }
}