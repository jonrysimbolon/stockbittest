package com.stockbit.hiring

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.hiring.penting.PenghubungInterface

class CoinAdapter(
    val context: Context,
    val dataCoin: ArrayList<HashMap<String, String>>,
    val penghubungInterface: PenghubungInterface
) : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(context)
                .inflate(R.layout.activity_coin_adapter, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataCoin.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameCoinText.text = dataCoin[position]["nameCoin"]
        holder.nameFullCoinText.text = dataCoin[position]["fullNameCoin"]
        val priceCoin = dataCoin[position]["priceCoin"]!!.replace("$ ","")
        holder.priceCoinText.text = priceCoin
        val changeHour = dataCoin[position]["changeHour"]!!.replace("$ ","")
        if(changeHour.contains("+")){
            holder.changeCoinText.setTextColor(ContextCompat.getColor(context,R.color.gray))
        }else if(changeHour.contains("-")){
            holder.changeCoinText.setTextColor(ContextCompat.getColor(context,R.color.red))
        }else{
            holder.changeCoinText.setTextColor(ContextCompat.getColor(context,R.color.grayslow))
        }
        holder.changeCoinText.text = "${changeHour}(${dataCoin[position]["changePcThour"]})"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameCoinText = itemView.findViewById<TextView>(R.id.nameCoinText)
        val nameFullCoinText = itemView.findViewById<TextView>(R.id.nameFullCoinText)
        val priceCoinText = itemView.findViewById<TextView>(R.id.priceCoinText)
        val changeCoinText = itemView.findViewById<TextView>(R.id.changeCoinText)
    }
}