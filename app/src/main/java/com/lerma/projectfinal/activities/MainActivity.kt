package com.lerma.projectfinal.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lerma.projectfinal.R
import com.lerma.projectfinal.database.AppDatabase
import com.lerma.projectfinal.databinding.ActivityMainBinding
import com.lerma.projectfinal.preferences.SharedPreferencesHelper
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            val userName = binding.userName.text
            val password = binding.password.text

            var sw = true

            if (userName.isNullOrBlank()) {
                binding.userNameTil.error = "El Usuario es requerido"
                sw = false
            } else {
                binding.userNameTil.error = null
            }

            if (password.isNullOrBlank()) {
                binding.passwordTil.error = "La Contraseña es requerida"
                sw = false
            } else {
                binding.passwordTil.error = null
            }

            if (sw) {
                doAsync {
                    val database = AppDatabase.getInstance(this@MainActivity)
                    val personResponse =
                        database.personDao().validateUser(userName.toString(), password.toString())

                    uiThread {
                        if (personResponse?.userName != null) {
                            SharedPreferencesHelper.addUser(
                                this@MainActivity,
                                personResponse.userName,
                                personResponse.name,
                                personResponse.lastName
                            )

                            Toast.makeText(
                                this@MainActivity,
                                getString(R.string.str_validUser),
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(this@MainActivity, MenuActivity::class.java)
                            intent.putExtra("KeyUser", personResponse.userName)
                            intent.putExtra("KeyName", personResponse.name)
                            intent.putExtra("KeyLastName", personResponse.lastName)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                getString(R.string.str_invalidUser),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        binding.register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
