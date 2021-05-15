package com.lerma.projectfinal.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lerma.projectfinal.R

data class Product(
    var idProduct: Int,
    var title: String,
    var description: String,
    var price: Double,
    var image: String
)

class ProductsAdapter(products: ArrayList<Product>, context: Context) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {
    private val products = products
    val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(this.products[position], context)
    }

    override fun getItemCount(): Int = this.products.size

    class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var container: LinearLayout? = null
        private var title: TextView? = null
        private var image: ImageView? = null

        init {
            container = itemView.findViewById(R.id.itemProductContainer)
            title = itemView.findViewById(R.id.productTitle)
            image = itemView.findViewById(R.id.productImage)
        }

        fun bind(product: Product, context: Context) {
            title?.text = product.title
            Glide.with(context)
                .load(product.image)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(itemView.findViewById(R.id.productImage))

            container?.setOnClickListener {
                //Go to Product Detail
                ProductDetail.start(context, product.idProduct, product.title, product.description, product.price, product.image)
            }
        }
    }
}