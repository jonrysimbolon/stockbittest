package com.stockbit.hiring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.stockbit.hiring.penting.PenghubungInterface
import kotlinx.android.synthetic.main.activity_daftar_page.*
import java.util.*

class DaftarPage : AppCompatActivity() {

    lateinit var penghubungInterface: PenghubungInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_page)
        penghubungInterface = PenghubungInterface(this)
        backBtn.setOnClickListener { onBackPressed() }
        supportImg.setOnClickListener { penghubungInterface.toastC("Coming soon") }
        val loginTextB = "<font color='black'>Sudah punya akun? </font><font color='${
            ContextCompat.getColor(
                this,
                R.color.green
            )
        }'>Masuk</font>."
        loginBtn.text = HtmlCompat.fromHtml(loginTextB, HtmlCompat.FROM_HTML_MODE_LEGACY)
        loginBtn.setOnClickListener {
            penghubungInterface.go(LoginPage::class.java)
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }
        daftarBtn.setOnClickListener {
            if (usernameBox.text.toString().isNotEmpty() && emailBox.text.toString()
                    .isNotEmpty() && passwordBox.text.toString().isNotEmpty()
            ) {
                val uuid_member = UUID.randomUUID().toString()
                penghubungInterface.createDatabase().execSQL("insert into userTbl(uuid_member,email,username,password,type) values('$uuid_member','${emailBox.text}','${usernameBox.text}','${penghubungInterface.getMD5(passwordBox.text.toString())}','default')")
                penghubungInterface.showMessageOK("Pendaftaran berhasil dilakukan.\nSilahkan melakukan proses login.") { dialogInterface, i ->
                    penghubungInterface.go(LoginPage::class.java)
                    overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
                }
            } else {
                penghubungInterface.toastC("Silahkan isi data diatas dengan lengkap dan benar")
            }
        }
    }

    override fun onBackPressed() {
        //penghubungInterface.go(DaftarLoginPage::class.java)
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
    }
}