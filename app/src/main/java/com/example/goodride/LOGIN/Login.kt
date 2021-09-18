package com.example.goodride.LOGIN

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.example.goodride.API.RetrofitService
import com.example.goodride.AppActivity
import com.example.goodride.R
import com.example.goodride.Registration.Registration
import com.example.goodride.Repository.UserRepository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private lateinit var etPhonenumber: TextInputEditText
private lateinit var etLoginPassword: TextInputEditText


private lateinit var btnLogin: Button
private lateinit var btnSignup: Button

class Login : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etPhonenumber =findViewById(R.id.etLoginPhonenumber)
        etLoginPassword = findViewById(R.id.etLoginPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup = findViewById(R.id.btnSignup)
      checkPermission()
        btnLogin.setOnClickListener(this)

        btnSignup.setOnClickListener(this)

    }
    fun checkPermission(){
        var permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.CAMERA,android.Manifest.permission.ACCESS_NETWORK_STATE)

        for (permission in permissions)
        {
            if(ActivityCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, permissions,1)

            }
        }

    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnLogin->{
                login()
            }

            R.id.btnSignup->{
                val intent = Intent(this, Registration::class.java)
                startActivity(intent)
            }
        }
    }


    private fun login() {
        val Phonenumber = etPhonenumber.text.toString()
        val password = etLoginPassword.text.toString()


        CoroutineScope(Dispatchers.IO).launch {
            val repository =UserRepository()
            val response = repository.loginUser(Phonenumber, password)
            if(response.success==true){

                RetrofitService.user = response.data
                RetrofitService.token = "Bearer " + response.token
                    withContext(Main){
                    val sharePref =
                            getSharedPreferences("MyPref", MODE_PRIVATE)
                    val editor = sharePref.edit()
                    editor.putString("phonenumber", Phonenumber)
                    editor.putString("password", password)
                    editor.apply()
                            //Toast.makeText(this@Login, "${response.data}", Toast.LENGTH_SHORT).show()
                    startActivity(
                            Intent(this@Login, AppActivity::class.java)
                    )
                }
            }
        }

    }



}