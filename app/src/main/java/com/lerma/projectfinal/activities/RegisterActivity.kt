package com.lerma.projectfinal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.lerma.projectfinal.R
import com.lerma.projectfinal.database.AppDatabase
import com.lerma.projectfinal.database.Person
import com.lerma.projectfinal.databinding.ActivityRegisterBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Title por dos opciones extras aparte del manifest
        //title = getString(R.string.str_register_now)
        //title = "Aquí un title igual"

        binding.registerNow.setOnClickListener {
            var sw: String = "Si"

            val userName = binding.userName.text
            val name = binding.name.text
            val lastName = binding.lastName.text
            val adress = binding.adress.text
            val email = binding.email.text
            val password = binding.password.text
            val passwordRep = binding.passwordRep.text

            if (userName.isNullOrBlank()) {
                binding.userNameTil.error = getString(R.string.str_val_userName)
                sw = "No"
            } else {
                binding.userNameTil.error = null
            }

            if (name.isNullOrBlank()) {
                binding.nameTil.error = getString(R.string.str_val_name)
                sw = "No"
            } else {
                binding.nameTil.error = null
            }

            if (lastName.isNullOrBlank()) {
                binding.lastNameTil.error = getString(R.string.str_val_lastName)
                sw = "No"
            } else {
                binding.lastNameTil.error = null
            }

            if (adress.isNullOrBlank()) {
                binding.adressTil.error = getString(R.string.str_val_adress)
                sw = "No"
            } else {
                binding.adressTil.error = null
            }

            if (email.isNullOrBlank()) {
                binding.emailTil.error =
                    getString(R.string.str_val_email)
                sw = "No"
            } else {
                binding.emailTil.error = null
            }

            if (password.isNullOrBlank()) {
                binding.passwordTil.error = getString(R.string.str_val_password)
                sw = "No"
            } else {
                binding.passwordTil.error = null
            }

            if (passwordRep.isNullOrBlank() || password.toString() != passwordRep.toString()) {
                binding.passwordRepTil.error = getString(R.string.str_val_passwordRep)
                sw = "No"
            } else {
                binding.passwordRepTil.error = null
            }

            if (sw == "Si") {
                val person = Person()
                person.name = name.toString()
                person.lastName = lastName.toString()
                person.adress = adress.toString()
                person.email = email.toString()
                person.userName = userName.toString()
                person.passWord = password.toString()

                doAsync {
                    val database = AppDatabase.getInstance(this@RegisterActivity)
                    if (database.personDao().existUser(person.userName) == null) {
                        database.personDao().insert(person)

                        uiThread {
                            Toast.makeText(
                                this@RegisterActivity,
                                getString(R.string.str_registerUser),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        uiThread {
                            Toast.makeText(
                                this@RegisterActivity,
                                getString(R.string.str_existsUser),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
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