package com.lerma.projectfinal.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lerma.projectfinal.R

data class Order(val title: String, val sign: String)

class OrdersAdapter(orders: ArrayList<Order>, context: Context) :
    RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {
    val orders = orders
    val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        return OrdersViewHolder(LayoutInflater.from(context).inflate(R.layout.order_item, parent, false))
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.bind(this.orders[position], context)
    }

    override fun getItemCount(): Int = this.orders.size

    class OrdersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container: LinearLayout? = null
        var title: TextView? = null
        var sign: TextView? = null

        init {
            container = itemView.findViewById(R.id.itemOrderContainer)
            title = itemView.findViewById(R.id.orderTitle)
            sign = itemView.findViewById(R.id.orderSign)
        }

        fun bind(order: Order, context: Context) {
            title?.text = order.title
            sign?.text = order.sign
            container?.setOnClickListener {
                //Go to Order Detail
                OrderDetail.start(context, order.title, order.sign)
            }
        }
    }
}