package com.stockbit.hiring

import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var mActionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main2_activity)

        val toolBar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.abs_layout)

        val view = supportActionBar?.customView
        val menuImg = view?.findViewById<ImageView>(R.id.menuImg)

        val mDrawer_layout = findViewById<DrawerLayout>(R.id.drawer_layout)
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
            mDrawer_layout.openDrawer(Gravity.START)
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

}
