package com.stockbit.hiring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.stockbit.hiring.penting.PenghubungInterface
import kotlinx.android.synthetic.main.activity_login_page.*

class LoginPage : AppCompatActivity() {

    lateinit var penghubungInterface: PenghubungInterface

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
        loginBtn.setOnClickListener {
            if(userEmailBox.text.toString().isNotEmpty() && passwordBox.text.toString().isNotEmpty()){
                val cursor = penghubungInterface.createDatabase().rawQuery("Select * from userTbl where username = '${userEmailBox.text}' or email = '${userEmailBox.text}' and password = '${penghubungInterface.getMD5(passwordBox.text.toString())}'", null, null)
                if (cursor.count != 0) {
                    if (cursor.moveToFirst()) {
                        if (!cursor.isAfterLast) {
                            val uuid_member = cursor.getString(1)
                            val email = cursor.getString(2)
                            val username = cursor.getString(3)

                            penghubungInterface.editDataPerson("uuid_member", uuid_member)
                            penghubungInterface.editDataPerson("email", email)
                            penghubungInterface.editDataPerson("username", username)

                            penghubungInterface.toastC("Berhasil login")

                            penghubungInterface.go(MainActivity::class.java)
                            overridePendingTransition(
                                R.anim.slide_left_enter,
                                R.anim.slide_left_exit
                            )
                        }
                    }
                }else{
                    penghubungInterface.toastC("Informasi yang anda input salah.")
                }
            }else{
                penghubungInterface.toastC("Harap isi data yang tersedia dengan lengkap dan benar")
            }
        }
    }

    override fun onBackPressed() {
        //penghubungInterface.go(DaftarLoginPage::class.java)
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
    }
}