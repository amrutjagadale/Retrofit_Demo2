package com.example.retrofit_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.retrofit_demo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val userService = UserServices.getInstance()

        activityMainBinding.fetchUsers.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch{
                    val userModel = userService.fetchUsers(
                        activityMainBinding.edtUserId.text.toString().toInt()
                    )
                    withContext(Dispatchers.Main){
                        activityMainBinding.txtEmail.text = userModel.data.email
                        activityMainBinding.txtUserName.text = userModel.data.firstName+" "+userModel.data.lastName
                        Glide.with(this@MainActivity)
                            .load(userModel.data.avatar)
                            .into(activityMainBinding.userImage)
                    }
                }
        }
    }
}