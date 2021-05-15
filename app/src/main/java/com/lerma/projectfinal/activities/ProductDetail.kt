package com.lerma.projectfinal.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.lerma.projectfinal.R
import com.lerma.projectfinal.database.AppDatabase
import com.lerma.projectfinal.database.Car
import com.lerma.projectfinal.databinding.ActivityProductDetailBinding
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ConstantsP {
    companion object {
        const val PRODUCT_ID_KEY = "PRODUCT_ID_KEY"
        const val PRODUCT_TITLE_KEY = "PRODUCT_TITLE_KEY"
        const val PRODUCT_DESCRIPTION_KEY = "PRODUCT_DESCRIPTION_KEY"
        const val PRODUCT_PRICE_KEY = "PRODUCT_PRICE_KEY"
        const val PRODUCT_IMAGE_KEY = "PRODUCT_IMAGE_KEY"
    }
}

class ProductDetail : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailBinding

    var title: String? = null
    var image: String? = null
    var description: String? = null
    var price: Double? = 0.00
    var idProduct: Int? = 0

    companion object {
        @JvmStatic
        fun start(
            context: Context,
            idProduct: Int,
            title: String,
            description: String,
            price: Double,
            image: String
        ) {
            val starter = Intent(context, ProductDetail::class.java)
                .putExtra(ConstantsP.PRODUCT_TITLE_KEY, title)
                .putExtra(ConstantsP.PRODUCT_IMAGE_KEY, image)
                .putExtra(ConstantsP.PRODUCT_DESCRIPTION_KEY, description)
                .putExtra(ConstantsP.PRODUCT_PRICE_KEY, price)
                .putExtra(ConstantsP.PRODUCT_ID_KEY, idProduct)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent != null) {
            title = intent.getStringExtra(ConstantsP.PRODUCT_TITLE_KEY)
            image = intent.getStringExtra(ConstantsP.PRODUCT_IMAGE_KEY)
            description = intent.getStringExtra(ConstantsP.PRODUCT_DESCRIPTION_KEY)
            idProduct = intent.getIntExtra(ConstantsP.PRODUCT_ID_KEY, 0)
            price = intent.getDoubleExtra(ConstantsP.PRODUCT_PRICE_KEY, 0.00)

            initViews()
        }

        binding.addCar.setOnClickListener {
            val car = Car()
            car.name = title.toString()
            car.image = image.toString()
            car.description = description.toString()
            car.idProduct = idProduct!!
            car.price = price!!

            doAsync {
                val database = AppDatabase.getInstance(this@ProductDetail)
                database.carDao().insert(car)

                uiThread {
                    Toast.makeText(
                        this@ProductDetail,
                        getString(R.string.str_added_car),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun initViews() {
        productTitleDetail.text = this.title
        productDescriptionDetail.text = this.description
        Glide.with(this)
            .load(this.image)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(findViewById(R.id.productImageDetail))
        precioDetail.text = this.price.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}