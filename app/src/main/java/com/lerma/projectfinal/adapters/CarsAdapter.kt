package com.lerma.projectfinal.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lerma.projectfinal.Fragments.products.CarListFragment
import com.lerma.projectfinal.Fragments.products.ProductListFragment
import com.lerma.projectfinal.R
import com.lerma.projectfinal.database.AppDatabase
import org.jetbrains.anko.doAsync

data class CarAdap(
    var id: Int,
    var idProduct: Int,
    var name: String,
    var description: String,
    var price: Double,
    var image: String
)

class CarsAdapter(cars: ArrayList<CarAdap>, context: Context) :
    RecyclerView.Adapter<CarsAdapter.CarsViewHolder>() {
    private val cars = cars
    val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        return CarsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.car_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.bind(this.cars[position], context)
    }

    override fun getItemCount(): Int = this.cars.size

    class CarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var container: LinearLayout? = null
        private var title: TextView? = null
        private var image: ImageView? = null
        private var price: TextView? = null
        private var delCar: ImageButton? = null

        init {
            container = itemView.findViewById(R.id.itemCarContainer)
            title = itemView.findViewById(R.id.productTitle)
            image = itemView.findViewById(R.id.productImage)
            price = itemView.findViewById(R.id.productPrice)
            delCar = itemView.findViewById(R.id.delCart)
        }

        fun bind(car: CarAdap, context: Context) {
            title?.text = car.name
            Glide.with(context)
                .load(car.image)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(itemView.findViewById(R.id.productImage))
            price?.text = car.price.toString()

            delCar?.setOnClickListener {
                //Go to Product Detail
                doAsync {
                    val database = AppDatabase.getInstance(context)
                    database.carDao().delCarId(car.id)
                }

                ProductListFragment()
            }
        }
    }
}