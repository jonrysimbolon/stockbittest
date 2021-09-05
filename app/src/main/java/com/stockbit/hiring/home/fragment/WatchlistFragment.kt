package com.stockbit.hiring.home.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.hiring.CoinAdapter
import com.stockbit.hiring.R
import com.stockbit.hiring.penting.PenghubungInterface
import kotlinx.android.synthetic.main.fragment_watchlist.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response
import java.io.BufferedReader
import java.io.InputStreamReader

class WatchlistFragment : Fragment() {

    lateinit var penghubungInterface: PenghubungInterface
    lateinit var dataKoinArrayList:ArrayList<HashMap<String,String>>
    var limitKoin = "10"
    var lstStatus = ""
    lateinit var coinAdapter: CoinAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watchlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataKoinArrayList = ArrayList()
        penghubungInterface = PenghubungInterface(requireActivity())

        getDataFromAPI(limitKoin)

        listCoinRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    // Scrolling up
                    swipyrefreshlayout.setOnRefreshListener {
                        Log.e("not_frag", "down")
                        swipeDownNotif()
                        swipyrefreshlayout.isRefreshing = false
                    }
                } else {
                    swipyrefreshlayout.setOnRefreshListener {
                        Log.e("not_frag", "up")
                        swipeUpNotif()
                        swipyrefreshlayout.isRefreshing = false
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    // Do something
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // Do something
                } else {
                    // Do something
                }
            }
        })
    }

    private fun swipeUpNotif() {
        lstStatus = "atas"
        getDataFromAPI("10")
    }

    private fun swipeDownNotif() {
        lstStatus = "bawah"
        penghubungInterface.editDataPerson("limitKoinAkhir",(dataKoinArrayList.size + 10).toString())
        getDataFromAPI(penghubungInterface.putData("limitKoinAkhir")!!)
    }


    private fun getDataFromAPI(limitKoinDlm: String) {
        Log.e("WatchListFrag","limikoindlm : $limitKoinDlm")
        progressBarId.visibility = View.VISIBLE
        penghubungInterface.SendRetro().totaltoptiervolfull(
            limitKoinDlm,
            "USD",
            object : Callback<Response> {
                override fun success(t: Response?, response: Response?) {
                    progressBarId.visibility = View.GONE
                    if (lstStatus != "bawah") {
                        dataKoinArrayList.clear()
                    }
                    if (t?.status == 200) {
                        try {
                            var reader: BufferedReader? = null
                            var output = ""
                            reader =
                                BufferedReader(InputStreamReader(response?.body?.`in`()!!))
                            output = reader.readLine()
                            Log.e("totaltoptiervolfull", "hasil nya : $output")
                            val data = JSONObject(output)
                            val messageStatus = data.getString("Message")

                            if(messageStatus == "Success"){
                                val metaData = data.getJSONObject("MetaData")
                                val sponsoredData = data.getJSONArray("SponsoredData")
                                val dataKoin = data.getJSONArray("Data")

                                for(i in dataKoinArrayList.size until dataKoin.length()){
                                    val dataKoinObj = dataKoin.getJSONObject(i)
                                    val coinInfo = dataKoinObj.getJSONObject("CoinInfo")
                                    val nameCoin = coinInfo.getString("Name")
                                    val fullNameCoin = coinInfo.getString("FullName")

                                    val dataHash = HashMap<String,String>()
                                    dataHash["nameCoin"] = nameCoin
                                    dataHash["fullNameCoin"] = fullNameCoin
                                    //val raw = dataKoinObj.getJSONObject("RAW")
                                    if(dataKoinObj.has("DISPLAY")) {
                                        val display = dataKoinObj.getJSONObject("DISPLAY")
                                        val usd = display.getJSONObject("USD")
                                        val priceCoin = usd.getString("PRICE")
                                        val changeHour = usd.getString("CHANGEHOUR")
                                        val changePcThour = usd.getString("CHANGEPCTHOUR")

                                        dataHash["priceCoin"] = priceCoin
                                        dataHash["changeHour"] = changeHour
                                        dataHash["changePcThour"] = changePcThour
                                    }else{
                                        dataHash["priceCoin"] = "$ "
                                        dataHash["changeHour"] = "$ "
                                        dataHash["changePcThour"] = ""
                                    }
                                    dataKoinArrayList.add(dataHash)
                                }

                                when (lstStatus) {
                                    "atas" -> {
                                        listCoin()
                                    }
                                    "bawah" -> {
                                        coinAdapter.notifyItemInserted(dataKoinArrayList.size - 1)
                                    }
                                    "" -> {
                                        listCoin()
                                    }
                                }
                            }else{
                                penghubungInterface.toastC(messageStatus)
                            }

                        } catch (e: Exception) {
                            e.printStackTrace()
                            Log.e("totaltoptiervolfull", "error : " + e.message)
                        }

                    }
                }

                override fun failure(error: RetrofitError?) {
                    progressBarId.visibility = View.GONE
                    penghubungInterface.toastC(resources.getString(R.string.koneksibermasalah))
                    Log.e("totaltoptiervolfull", "url : " + error?.url)
                    Log.e("totaltoptiervolfull", "error : " + error?.message)
                }
            }
        )
    }

    private fun listCoin() {
        coinAdapter = CoinAdapter(requireActivity(), dataKoinArrayList, penghubungInterface)
        listCoinRV.adapter = coinAdapter
        listCoinRV.layoutManager = LinearLayoutManager(requireActivity())
        listCoinRV.setHasFixedSize(true)
    }
}