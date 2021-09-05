package com.stockbit.hiring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import kotlinx.android.synthetic.main.activity_login_page.*

class LoginPage : AppCompatActivity() {

    lateinit var penghubungInterface: PenghubungInterface

    /*penghubungInterface.go(MainActivity::class.java)
    overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        penghubungInterface = PenghubungInterface(this)
        backBtn.setOnClickListener { onBackPressed() }
        supportImg.setOnClickListener { penghubungInterface.toastC("Coming soon") }
        val daftarTxtB = "<font color='black'>Belum punya akun? </font><font color='${ContextCompat.getColor(this,R.color.green)}'>Daftar sekarang</font>."
        daftarBtn.text = HtmlCompat.fromHtml(daftarTxtB,HtmlCompat.FROM_HTML_MODE_LEGACY)
        daftarBtn.setOnClickListener {
            penghubungInterface.go(DaftarPage::class.java)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        }
    }

    override fun onBackPressed() {
        //penghubungInterface.go(DaftarLoginPage::class.java)
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
    }
}