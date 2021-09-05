package com.stockbit.hiring.penting

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.stockbit.hiring.R
import id.viharaumat.FileUtama.InterfaceRetro
import id.viharaumat.FileUtama.MakeDataBase
import retrofit.RestAdapter
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.DecimalFormat
import java.util.*

class PenghubungInterface(val context: Context) {

    lateinit var font: Typeface
    lateinit var fontr: Typeface
    lateinit var fonts: Typeface

    var ip = "https://min-api.cryptocompare.com/data/top"

    var max = 999999
    var min = 111111
    var datapersonal: SharedPreferences =
        context.getSharedPreferences("datapersonal", Context.MODE_PRIVATE)
    lateinit var editDatapersonal: SharedPreferences.Editor
    var rand = Random()
    internal var header: View? = null

    fun typeFaceButton(button: Button) {
        typeFaceUse()
        button.typeface = font
    }

    fun typeFaceButtons(button: Button) {
        typeFaceUse()
        button.typeface = fonts
    }

    fun typeFaceEditText(editText: EditText) {
        typeFaceUse()
        editText.typeface = font
    }

    fun setMoney(str: String): String {
        val dbl = str.toDouble()
        val formatter = DecimalFormat("#,###")
        return formatter.format(dbl)
    }

    fun typeFaceEditTexts(editText: EditText) {
        typeFaceUse()
        editText.typeface = fonts
    }

    fun goBrowser(url2: String) {
        val url = url2
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        context.startActivity(i)
    }

    fun typeFaceTextView(textView: TextView) {
        typeFaceUse()
        textView.typeface = font
    }

    fun typeFaceTextViewr(textView: TextView) {
        typeFaceUse()
        textView.typeface = fontr
    }

    fun typeFaceTextViews(textView: TextView) {
        typeFaceUse()
        textView.typeface = fonts
    }

    fun animationCus(animation: Int): Animation {
        return AnimationUtils.loadAnimation(context, animation)
    }

    fun typeFaceUse() {
        font = Typeface.createFromAsset(context.assets, "fa-brands-400.ttf")
        fonts = Typeface.createFromAsset(context.assets, "fa-solid-900.ttf")
        fontr = Typeface.createFromAsset(context.assets, "fa-regular-400.ttf")
    }

    fun createDatabase(): SQLiteDatabase {
        val makeDataBaseDataAC = MakeDataBase(context)
        return makeDataBaseDataAC.writableDatabase
    }

    protected fun acakNumber(): Int {
        return rand.nextInt(max - min + 1) + min
    }

    fun toast(data: String) {
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show()
    }

    fun toastC(data: String) {
        val toast: Toast = Toast.makeText(context, data, Toast.LENGTH_LONG)
        val view = toast.view
        //view!!.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
        view!!.background.setColorFilter(ContextCompat.getColor(context, R.color.green), PorterDuff.Mode.SRC_IN);
        val text = view.findViewById<TextView>(android.R.id.message)
        text.setTextColor(ContextCompat.getColor(context,R.color.white))
        toast.show()
    }

    fun toastt(data: Int) {
        Toast.makeText(context, context.resources.getString(data), Toast.LENGTH_SHORT).show()
    }

    fun goClear(clas: Class<*>) {
        val intent = Intent(context, clas)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun go(clas: Class<*>) {
        val intent = Intent(context, clas)
        context.startActivity(intent)
    }

    fun goWith1(clas: Class<*>, initial: String, value: String) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        context.startActivity(intent)
    }

    fun PopupWindow.dimBehind() {
        val container = contentView.rootView
        val context = contentView.context
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val p = container.layoutParams as WindowManager.LayoutParams
        p.flags = p.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
        p.dimAmount = 0.3f
        wm.updateViewLayout(container, p)
    }

    fun goWith11(clas: Class<*>, initial: String, value: ArrayList<*>) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        context.startActivity(intent)
    }

    fun goWith111(clas: Class<*>, initial: String, value: HashMap<String,String>) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        context.startActivity(intent)
    }

    fun goWith111(
        clas: Class<*>,
        initial: String,
        value: ArrayList<*>,
        initial2: String,
        value2: ArrayList<*>
    ) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        intent.putExtra(initial2, value2)
        context.startActivity(intent)
    }

    fun goWith12(
        clas: Class<*>,
        initial: String,
        value: ArrayList<*>,
        initial2: String,
        value2: String
    ) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        intent.putExtra(initial2, value2)
        context.startActivity(intent)
    }

    fun goWith15(
        clas: Class<*>,
        initial: String,
        value: ArrayList<*>,
        initial2: String,
        value2: String,
        inital3: String,
        value3: String,
        inital4: String,
        value4: String,
        inital5: String,
        value5: String
    ) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        intent.putExtra(initial2, value2)
        intent.putExtra(inital3, value3)
        intent.putExtra(inital4, value4)
        intent.putExtra(inital5, value5)
        context.startActivity(intent)
    }

    fun goWith14(
        clas: Class<*>,
        initial: String,
        value: ArrayList<*>,
        initial2: String,
        value2: String,
        inital3: String,
        value3: String,
        inital4: String,
        value4: String
    ) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        intent.putExtra(initial2, value2)
        intent.putExtra(inital3, value3)
        intent.putExtra(inital4, value4)
        context.startActivity(intent)
    }

    fun goWith13(
        clas: Class<*>,
        initial: String,
        value: ArrayList<*>,
        initial2: String,
        value2: String,
        inital3: String,
        value3: String
    ) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        intent.putExtra(initial2, value2)
        intent.putExtra(inital3, value3)
        context.startActivity(intent)
    }

    fun goWith2(clas: Class<*>, initial: String, value: String, intial2: String, value2: String) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        intent.putExtra(intial2, value2)
        context.startActivity(intent)
    }

    fun goWith3(
        clas: Class<*>,
        initial: String,
        value: String,
        intial2: String,
        value2: String,
        initial3: String,
        value3: String
    ) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        intent.putExtra(intial2, value2)
        intent.putExtra(initial3, value3)
        context.startActivity(intent)
    }

    fun goWith4(
        clas: Class<*>,
        initial: String,
        value: String,
        intial2: String,
        value2: String,
        initial3: String,
        value3: String,
        initial4: String,
        value4: String

    ) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        intent.putExtra(intial2, value2)
        intent.putExtra(initial3, value3)
        intent.putExtra(initial4, value4)
        context.startActivity(intent)
    }

    fun goWith5(
        clas: Class<*>,
        initial: String,
        value: String,
        intial2: String,
        value2: String,
        initial3: String,
        value3: String,
        initial4: String,
        value4: String,
        initial5: String,
        value5: String

    ) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        intent.putExtra(intial2, value2)
        intent.putExtra(initial3, value3)
        intent.putExtra(initial4, value4)
        intent.putExtra(initial5, value5)
        context.startActivity(intent)
    }

    fun goWith6(
        clas: Class<*>,
        initial: String,
        value: String,
        intial2: String,
        value2: String,
        initial3: String,
        value3: String,
        initial4: String,
        value4: String,
        initial5: String,
        value5: String,
        initial6: String,
        value6: String

    ) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        intent.putExtra(intial2, value2)
        intent.putExtra(initial3, value3)
        intent.putExtra(initial4, value4)
        intent.putExtra(initial5, value5)
        intent.putExtra(initial6, value6)
        context.startActivity(intent)
    }

    fun goWith7(
        clas: Class<*>,
        initial: String,
        value: String,
        intial2: String,
        value2: String,
        initial3: String,
        value3: String,
        initial4: String,
        value4: String,
        initial5: String,
        value5: String,
        initial6: String,
        value6: String,
        initial7: String,
        value7: String

    ) {
        val intent = Intent(context, clas)
        intent.putExtra(initial, value)
        intent.putExtra(intial2, value2)
        intent.putExtra(initial3, value3)
        intent.putExtra(initial4, value4)
        intent.putExtra(initial5, value5)
        intent.putExtra(initial6, value6)
        intent.putExtra(initial7, value7)
        context.startActivity(intent)
    }

    fun putData(data: String): String? {
        return datapersonal.getString(data, "")
    }

    public fun editDataPerson(initial: String, data: String) {
        editDatapersonal = datapersonal.edit()
        editDatapersonal.putString(initial, data)
        editDatapersonal.apply()
    }

    public fun delDataPerson(initial: String) {
        editDatapersonal = datapersonal.edit()
        editDatapersonal.remove(initial)
        editDatapersonal.apply()
    }

    fun SendRetro(): InterfaceRetro {
        val restAdapter = RestAdapter.Builder()
            .setEndpoint(ip)
            /*.setClient(OkClient(trustTestClient))
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setLog(AndroidLog("RETROFIT"))*/
            .build()

        return restAdapter.create(InterfaceRetro::class.java)
    }

    fun getMD5(input: String): String {
        try {
            val md = MessageDigest.getInstance("MD5")
            val messageDigest = md.digest(input.toByteArray())
            val number = BigInteger(1, messageDigest)
            var hashtext = number.toString(16)
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length < 32) {
                hashtext = "0$hashtext"
            }
            return hashtext
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }

    }

    fun hideAll() {
        if (Build.VERSION.SDK_INT < 16) {
            (context as Activity).window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        } else {
            val decorView = (context as Activity).window.decorView
            val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
            decorView.systemUiVisibility = uiOptions
            val actionBar = (context as AppCompatActivity).supportActionBar
            actionBar?.hide()
        }
        context.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun aturUang(nilai: String, nilaiText: TextView) {
        if (nilai.length > 6) {
            val nilaiLen = nilai.length
            val jlh = nilaiLen - 6
            val buffer1 = CharArray(jlh)
            val buffer2 = CharArray(3)
            val buffer3 = CharArray(3)
            nilai.toCharArray(buffer1, 0, 0, jlh)
            nilai.toCharArray(buffer2, 0, jlh, jlh + 3)
            nilai.toCharArray(buffer3, 0, jlh + 3, jlh + 6)
            nilaiText.text =
                "Rp " + String(buffer1) + "." + String(buffer2) + "." + String(buffer3) + ",-"
        } else if (nilai.length > 3) {
            val nilaiLen = nilai.length
            val jlh = nilaiLen - 3
            val buffer1 = CharArray(jlh)
            val buffer2 = CharArray(3)
            nilai.toCharArray(buffer1, 0, 0, jlh)
            nilai.toCharArray(buffer2, 0, jlh, jlh + 3)
            nilaiText.text = "Rp " + String(buffer1) + "." + String(buffer2) + ",-"
        } else {
            nilaiText.text = "Rp $nilai,-"
        }
    }

    fun aturUangCustom(tambahanTextDepan: String, nilai: String, nilaiText: TextView) {
        if (nilai.length > 6) {
            val nilaiLen = nilai.length
            val jlh = nilaiLen - 6
            val buffer1 = CharArray(jlh)
            val buffer2 = CharArray(3)
            val buffer3 = CharArray(3)
            nilai.toCharArray(buffer1, 0, 0, jlh)
            nilai.toCharArray(buffer2, 0, jlh, jlh + 3)
            nilai.toCharArray(buffer3, 0, jlh + 3, jlh + 6)
            nilaiText.text =
                tambahanTextDepan + "Rp " + String(buffer1) + "." + String(buffer2) + "." + String(
                    buffer3
                ) + ",-"
        } else if (nilai.length > 3) {
            val nilaiLen = nilai.length
            val jlh = nilaiLen - 3
            val buffer1 = CharArray(jlh)
            val buffer2 = CharArray(3)
            nilai.toCharArray(buffer1, 0, 0, jlh)
            nilai.toCharArray(buffer2, 0, jlh, jlh + 3)
            nilaiText.text =
                tambahanTextDepan + "Rp " + String(buffer1) + "." + String(buffer2) + ",-"
        } else {
            nilaiText.text = tambahanTextDepan + "Rp $nilai,-"
        }
    }

    fun aturKm(nilai: String, nilaiText: TextView) {
        if (nilai.length > 6) {
            val nilaiLen = nilai.length
            val jlh = nilaiLen - 6
            val buffer1 = CharArray(jlh)
            val buffer2 = CharArray(3)
            val buffer3 = CharArray(3)
            nilai.toCharArray(buffer1, 0, 0, jlh)
            nilai.toCharArray(buffer2, 0, jlh, jlh + 3)
            nilai.toCharArray(buffer3, 0, jlh + 3, jlh + 6)
            nilaiText.text = String(buffer1) + "." + String(buffer2) + "." + String(buffer3)
        } else if (nilai.length > 3) {
            val nilaiLen = nilai.length
            val jlh = nilaiLen - 3
            val buffer1 = CharArray(jlh)
            val buffer2 = CharArray(3)
            nilai.toCharArray(buffer1, 0, 0, jlh)
            nilai.toCharArray(buffer2, 0, jlh, jlh + 3)
            nilaiText.text = String(buffer1) + "." + String(buffer2)
        } else {
            nilaiText.text = "$nilai"
        }
    }

    fun aturTgl(tgl: String): String {
        var tgl = tgl
        if (tgl.length > 1) {
            val tanggal = tgl.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            tgl = tanggal[2] + "-" + tanggal[1] + "-" + tanggal[0]
        }
        return tgl
    }

    fun showMessageOK(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setCancelable(false)
            .create()
            .show()
    }

    fun showMessageOKCustom(
        message: String,
        yes: String,
        okListener: DialogInterface.OnClickListener
    ) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton(yes, okListener)
            .setCancelable(false)
            .create()
            .show()
    }

    fun showMessageOKTitleCustom(
        title: String,
        message: String,
        yes: String,
        okListener: DialogInterface.OnClickListener
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(yes, okListener)
            .setCancelable(false)
            .create()
            .show()
    }

    fun showMessageOKCustom1(
        title: String,
        message: String,
        yes: String,
        no: String,
        okListener: DialogInterface.OnClickListener,
        noListener: DialogInterface.OnClickListener
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(yes, okListener)
            .setNegativeButton(no, noListener)
            .setCancelable(false)
            .create()
            .show()
    }

    fun showMessageOKCustom2(
        title: String,
        message: String,
        yes: String,
        no: String,
        out: String,
        okListener: DialogInterface.OnClickListener,
        noListener: DialogInterface.OnClickListener,
        outListener: DialogInterface.OnClickListener
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(yes, okListener)
            .setNegativeButton(no, noListener)
            .setNeutralButton(out, outListener)
            .setCancelable(false)
            .create()
            .show()
    }

    fun showMessageOKFont(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setCancelable(false)
            .create()
            .show()
    }

    fun showMessageOKCancel(
        message: String,
        okListener: DialogInterface.OnClickListener,
        cancelListener: DialogInterface.OnClickListener
    ) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton("Yes", okListener)
            .setNegativeButton("No", cancelListener)
            .setCancelable(false)
            .create()
            .show()
    }

    fun showMessageOKCancelCustom(
        message: String,
        yes: String,
        no: String,
        okListener: DialogInterface.OnClickListener,
        cancelListener: DialogInterface.OnClickListener
    ) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton(yes, okListener)
            .setNegativeButton(no, cancelListener)
            .setCancelable(false)
            .create()
            .show()
    }

    fun buang(masuk: String, jumlah: Int): String {
        return masuk.substring(0, masuk.length - jumlah)
    }

    fun insertAt(target: String, position: Int, insert: String): String {
        val targetLen = target.length
        if (position < 0 || position > targetLen) {
            throw IllegalArgumentException("position=$position")
        }
        if (insert.isEmpty()) {
            return target
        }
        if (position == 0) {
            return insert + target
        } else if (position == targetLen) {
            return target + insert
        }
        val insertLen = insert.length
        val buffer = CharArray(targetLen + insertLen)
        target.toCharArray(buffer, 0, 0, position)
        insert.toCharArray(buffer, position, 0, insertLen)
        target.toCharArray(buffer, position + insertLen, position, targetLen)
        return String(buffer)
    }

    fun goExit() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
        onQuitPressed()
    }

    fun onQuitPressed() {
        val pid = android.os.Process.myPid()
        android.os.Process.killProcess(pid)
    }

    companion object {

        fun insertAtt(target: String, position: Int, insert: String): String {
            val targetLen = target.length
            if (position < 0 || position > targetLen) {
                throw IllegalArgumentException("position=$position")
            }
            if (insert.isEmpty()) {
                return target
            }
            if (position == 0) {
                return insert + target
            } else if (position == targetLen) {
                return target + insert
            }
            val insertLen = insert.length
            val buffer = CharArray(targetLen + insertLen)
            target.toCharArray(buffer, 0, 0, position)
            insert.toCharArray(buffer, position, 0, insertLen)
            target.toCharArray(buffer, position + insertLen, position, targetLen)
            return String(buffer)
        }
    }
}
