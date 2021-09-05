package com.stockbit.hiring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stockbit.hiring.penting.PenghubungInterface
import kotlinx.android.synthetic.main.activity_daftar_login_page.*

class DaftarLoginPage : AppCompatActivity() {

    lateinit var penghubungInterface: PenghubungInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_login_page)
        penghubungInterface = PenghubungInterface(this)
        checkLogin()
        loginBtn.setOnClickListener {
            penghubungInterface.go(LoginPage::class.java)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        }
        daftarBtn.setOnClickListener {
            penghubungInterface.go(DaftarPage::class.java)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        }

    }

    private fun checkLogin() {
        val cursor = penghubungInterface.createDatabase().rawQuery("Select * from userTbl where uuid_member = '${penghubungInterface.putData("uuid_member")}'", null, null)
        if (cursor.count != 0) {
            penghubungInterface.go(MainActivity::class.java)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}