package com.lerma.projectfinal.Fragments.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.lerma.projectfinal.R
import com.lerma.projectfinal.activities.CarAdap
import com.lerma.projectfinal.activities.CarsAdapter
import com.lerma.projectfinal.database.AppDatabase
import com.lerma.projectfinal.databinding.FragmentCarListBinding
import kotlinx.android.synthetic.main.fragment_car_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CarListFragment : Fragment() {
    private lateinit var binding: FragmentCarListBinding
    var priceSubTotal:Double = 0.00
    var priceIGV:Double = 0.00
    var priceTotal:Double = 0.00

    var cars: ArrayList<CarAdap> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCarListBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setOrder.setOnClickListener {
            setOrder?.setOnClickListener {
                //Go to Product Detail
                doAsync {
                    val database = AppDatabase.getInstance(requireContext())
                    database.carDao().delCar()
                }

                CarListFragment()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initData()

        priceSubTotal = 0.00
        priceIGV = 0.00
        priceTotal = 0.00
    }

    private fun initViews(cars: ArrayList<CarAdap>) {
        carsList.setHasFixedSize(true)
        carsList.layoutManager = GridLayoutManager(context, 1)
        carsList.adapter = context?.let { CarsAdapter(cars, it) }

        subTotal.text =  priceSubTotal.toString()
        binding.igv.text = priceIGV.toString()
        binding.total.text = priceTotal.toString()
    }

    private fun initData() {
        /*for (i in 1..15) {
            cars.add(Car(i, "Car $i", "Descripción $i", 0.00, "https://promart.vteximg.com.br/arquivos/ids/404224-1000-1000/107481.jpg?v=637177415555570000"))
        }*/

        doAsync {
            val database = AppDatabase.getInstance(requireContext())
            val carResponse = database.carDao().getAll()
            cars = ArrayList()

            uiThread {
                if (carResponse.isNullOrEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.str_car_empty),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    for (item in carResponse) {
                        priceSubTotal = priceSubTotal + item.price
                        priceIGV = priceSubTotal * 10 / 100
                        priceTotal = priceSubTotal + priceIGV
                        cars.add(CarAdap(item.id, item.idProduct, item.name, item.description, item.price, item.image))
                    }
                    initViews(cars)
                }
            }
        }
    }
}