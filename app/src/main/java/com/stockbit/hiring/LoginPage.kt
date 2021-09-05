package com.stockbit.hiring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_page.*

class LoginPage : AppCompatActivity() {

    lateinit var penghubungInterface: PenghubungInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        penghubungInterface = PenghubungInterface(this)
        loginBtn.setOnClickListener {
            penghubungInterface.go(MainActivity::class.java)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        }
    }

    override fun onBackPressed() {
        penghubungInterface.go(DaftarLoginPage::class.java)
        overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
    }
}