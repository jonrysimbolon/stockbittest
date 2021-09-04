package com.stockbit.hiring.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stockbit.hiring.R
import com.stockbit.hiring.home.fragment.*


class HomeFragment : Fragment(),BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadFragment(WatchlistFragment())

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.watchlistImg -> fragment = WatchlistFragment()
            R.id.streamImg -> fragment = StreamFragment()
            R.id.searchImg -> fragment = SearchFragment()
            R.id.chatImg -> fragment = ChatFragment()
            R.id.portofolioImg -> fragment = PortofolioFragment()
        }
        return loadFragment(fragment!!)
    }

}