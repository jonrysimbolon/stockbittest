package com.stockbit.hiring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stockbit.hiring.penting.PenghubungInterface
import kotlinx.android.synthetic.main.activity_daftar_page.*

class DaftarPage : AppCompatActivity() {

    lateinit var penghubungInterface: PenghubungInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_page)
        penghubungInterface = PenghubungInterface(this)
        backBtn.setOnClickListener { onBackPressed() }
        supportImg.setOnClickListener { penghubungInterface.toastC("Coming soon") }
    }

    override fun onBackPressed() {
        //penghubungInterface.go(DaftarLoginPage::class.java)
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
    }
}