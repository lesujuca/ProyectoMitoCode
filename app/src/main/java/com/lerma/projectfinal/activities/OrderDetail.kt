package com.lerma.projectfinal.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.lerma.projectfinal.databinding.ActivityOrderDetailBinding
import kotlinx.android.synthetic.main.activity_order_detail.*
import kotlinx.android.synthetic.main.order_item.*
import kotlinx.android.synthetic.main.order_item.orderTitle

class Constants {
    companion object {
        const val ORDER_TITLE_KEY = "ORDER_TITLE_KEY"
        const val ORDER_SIGN_KEY = "ORDER_SIGN_KEY"
    }
}

class OrderDetail : AppCompatActivity() {
    lateinit var binding: ActivityOrderDetailBinding

    var title: String? = null
    var sign: String? = null

    companion object {
        @JvmStatic
        fun start(context: Context, title: String, sign: String) {
            val starter = Intent(context, OrderDetail::class.java)
                .putExtra(Constants.ORDER_TITLE_KEY, title)
                .putExtra(Constants.ORDER_SIGN_KEY, sign)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent != null) {
            title = intent.getStringExtra(Constants.ORDER_TITLE_KEY)
            sign = intent.getStringExtra(Constants.ORDER_SIGN_KEY)

            initViews()
        }
    }

    private fun initViews() {
        orderTitle.text = this.title
        orderImage.text = this.sign
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