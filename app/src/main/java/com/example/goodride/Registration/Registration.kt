package com.example.goodride.Registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.goodride.LOGIN.Login
import com.example.goodride.R
import com.example.goodride.Repository.UserRepository
import com.example.goodride.model.RegisterModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*

private lateinit var Fullname: TextInputEditText
private lateinit var PhoneNumber: TextInputEditText
private lateinit var DateOfBirth: TextInputEditText
private lateinit var Password: TextInputEditText
private lateinit var registerSpinner: Spinner

private var selected_usertype = ""

private lateinit var SignUp: Button

class Registration : AppCompatActivity(), View.OnClickListener {

    private val userType= arrayOf("User", "Rider")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        Fullname= findViewById(R.id.etFullname1)
        PhoneNumber = findViewById(R.id.etPhonenumber1)
        DateOfBirth= findViewById(R.id.etDateOfBirth1)
        Password = findViewById(R.id.etPassword)
        registerSpinner= findViewById(R.id.registerSpinner)
        SignUp = findViewById(R.id.btnSignup1)

        SignUp.setOnClickListener(this)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userType)

        registerSpinner.adapter = adapter

        registerSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        selected_usertype = parent?.getItemAtPosition(position).toString()
                        Toast.makeText(applicationContext, "${selected_usertype}", Toast.LENGTH_SHORT).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnSignup1 ->{
                signUp()
            }
        }
    }

    private fun signUp() {
        try {
            val FullName = Fullname.text.toString()
            val PhoneNumber = PhoneNumber.text.toString()
            val DateOfBirth = DateOfBirth.text.toString()
            val Password = Password.text.toString()

            val userRegister = RegisterModel(FullName = "$FullName",PhoneNumber =  "$PhoneNumber", DateOfBirth = "${DateOfBirth}",
                   Password =  "$Password", UserType = "$selected_usertype", Latitude = "", Longitude = "")

            CoroutineScope(Dispatchers.IO).launch {
                val repository = UserRepository()
                val response = repository.registerUser(userRegister)

                if(response.success== true){
                    CoroutineScope(Dispatchers.Main).launch{
                        val intent = Intent(this@Registration, Login::class.java)
                        startActivity(intent)
                    }

                }
            }

        }
        catch (ex:Exception){
            CoroutineScope(Dispatchers.Main).launch{
                Toast.makeText(
                        this@Registration,
                        ex.toString(), Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}