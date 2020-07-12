package com.example.retrofit_kotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofit_kotlin.R
import com.example.retrofit_kotlin.api.RetrofitClient
import com.example.retrofit_kotlin.models.DefaultResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSignUp.setOnClickListener {

            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val name = editTextName.text.toString().trim()
            val school = editTextSchool.text.toString().trim()

            if (email.isEmpty()){
                editTextEmail.error = "Preencha o email por favor"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                editTextPassword.error = "Preencha o email por favor"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }

            if (name.isEmpty()){
                editTextName.error = "Preencha o email por favor"
                editTextName.requestFocus()
                return@setOnClickListener
            }

            if (school.isEmpty()){
                editTextSchool.error = "Preencha o email por favor"
                editTextSchool.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.createUser(email, name, password, school)
                .enqueue(object: Callback<DefaultResponse>{
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<DefaultResponse>,
                        response: Response<DefaultResponse>
                    ) {
                        Toast.makeText(applicationContext,response.body()?.message,Toast.LENGTH_LONG).show()
                    }

                })

        }
    }
}