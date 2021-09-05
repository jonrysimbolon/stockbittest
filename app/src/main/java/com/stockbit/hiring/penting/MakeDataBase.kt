package id.viharaumat.FileUtama

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Created by Lenovo on 10/25/2017.
 */

class MakeDataBase(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val sql1 = "create table userTbl(id integer primary key autoincrement, " +
                "uuid_member text null," +
                "email text null," +
                "username text null," +
                "password text null," + //md5
                "type text null" + //google, fb, default
                ");"
        Log.d("Data", "sql: $sql1")
        sqLiteDatabase.execSQL(sql1)

    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

    }

    companion object {
        private val DATABASE_NAME = "Stockbit.db"
        private val DATABASE_VERSION = 1
    }
}
