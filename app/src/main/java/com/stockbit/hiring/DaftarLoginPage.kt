package com.stockbit.hiring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_daftar_login_page.*

class DaftarLoginPage : AppCompatActivity() {

    lateinit var penghubungInterface: PenghubungInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_login_page)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.top_daflog_layout)

        penghubungInterface = PenghubungInterface(this)
        loginBtn.setOnClickListener {
            penghubungInterface.go(LoginPage::class.java)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        }
        daftarBtn.setOnClickListener {
            penghubungInterface.toastC("Daftar")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}