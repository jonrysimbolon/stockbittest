package com.stockbit.hiring

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var mActionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var mDrawer_layout: DrawerLayout
    lateinit var mNavView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main2_activity)

        val toolBar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.abs_layout)

        val view = supportActionBar?.customView
        val menuImg = view?.findViewById<ImageView>(R.id.menuImg)

        mNavView = findViewById(R.id.nav_view)
        mNavView.setNavigationItemSelectedListener(this)

        mDrawer_layout = findViewById(R.id.drawer_layout)
        mActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            mDrawer_layout,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        mDrawer_layout.addDrawerListener(mActionBarDrawerToggle)
        mActionBarDrawerToggle.isDrawerIndicatorEnabled = true
        mActionBarDrawerToggle.syncState()

        menuImg?.setOnClickListener {
            mDrawer_layout.openDrawer(GravityCompat.START)
        }

    }

    override fun onBackPressed() {
        if (mDrawer_layout.isDrawerOpen(GravityCompat.START)) {
            mDrawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun configureNavController() {
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
            R.id.nav_logout -> Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
        }
        //if(item.itemId != 0){
            mDrawer_layout.closeDrawer(GravityCompat.START)
        //}
        return false
    }

}
