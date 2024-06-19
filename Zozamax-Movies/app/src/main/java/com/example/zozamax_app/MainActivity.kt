package com.example.zozamax_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.zozamax_app.fragments.SingleMovieFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setupNavigationView()
        initializeViews()
        setupToolbar()

    }
    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }
    private fun initializeViews() {
        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)

    }


    private fun setupNavigationView() {
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            handleNavigationItemSelected(menuItem.itemId)
        }
    }
    private fun handleNavigationItemSelected(itemId: Int): Boolean {
        return when (itemId){
            R.id.nowPlaying -> {
                navigateTo(SingleMovieFragment::class.java)
                true
            }

            else -> {
                Log.w("Navigation", "Unhandled navigation item: $itemId")
                false
            }
        }
    }
    private fun navigateTo(activityClass: Class<*>) {
        startActivity(Intent(this, activityClass))
        true
    }
}
