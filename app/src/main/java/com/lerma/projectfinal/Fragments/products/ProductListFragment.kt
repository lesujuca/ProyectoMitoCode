package com.lerma.projectfinal.Fragments.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.lerma.projectfinal.activities.Product
import com.lerma.projectfinal.activities.ProductsAdapter
import com.lerma.projectfinal.databinding.FragmentProductListBinding
import com.lerma.projectfinal.network.ProductDataResponse
import com.lerma.projectfinal.network.ProductResponse
import com.lerma.projectfinal.network.ProductServices
import com.lerma.projectfinal.network.RetrofitConfiguration
import kotlinx.android.synthetic.main.fragment_product_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListFragment : Fragment() {
    private lateinit var binding: FragmentProductListBinding
    var products: ArrayList<Product> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductListBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        //initViews()
    }

    private fun initViews(products:ArrayList<Product>) {
        productsList.setHasFixedSize(true)
        productsList.layoutManager = GridLayoutManager(context, 2)
        productsList.adapter = context?.let { ProductsAdapter(products, it) }
    }

    private fun initData() {
        /*
        for (i in 1..5) {
            products.add(Product("Producto $i", "https://promart.vteximg.com.br/arquivos/ids/404224-1000-1000/107481.jpg?v=637177415555570000", "Descripción $i"))
        }
        */

        /*val bindingLoading = binding.loadingLayout
        bindingLoading.loading.visibility = View.VISIBLE*/

        val retrofit = RetrofitConfiguration.getConfiguration().create(ProductServices::class.java)
        val call = retrofit.getRetrofitProduct()
        call.enqueue(object:Callback<ProductResponse>{
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                val body = response.body()
                if (body != null) {
                    //bindingLoading.loading.visibility = View.GONE
                    if (body.status) {
                        val data: ArrayList<ProductDataResponse> = body.data
                        products = convertProduct(data)
                        initViews(products)
                        //validateEmpty()
                    } else {
                        //.......
                        //validateEmpty()
                    }
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.d("lerma-juan", "onResponse")
            }

        })
    }

    private fun convertProduct(data: ArrayList<ProductDataResponse>): ArrayList<Product> {
        val response = ArrayList<Product>()
        if (data != null) {
            for (item in data) {
                response.add(Product(item.id, item.name, item.des, item.price, item.urlImage))
            }
        }
        return response
    }
}