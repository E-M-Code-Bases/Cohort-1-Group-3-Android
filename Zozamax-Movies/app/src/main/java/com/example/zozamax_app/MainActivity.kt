package com.example.zozamax_app

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.zozamax_app.fragments.SingleMovieFragment
import com.google.android.material.navigation.NavigationView
import androidx.viewpager2.widget.ViewPager2
import com.example.zozamax_app.fragments.FavoritesFragment
import com.example.zozamax_app.fragments.NowPlayingFragment
import com.example.zozamax_app.fragments.PopularMoviesFragment
import com.example.zozamax_app.fragments.SettingsFragment
import com.example.zozamax_app.fragments.TopRatedFragment
import com.example.zozamax_app.fragments.TrailerFragment
import com.example.zozamax_app.fragments.UpcomingMoviesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupNavigationView()
        setupToolbar()
        setupViewPager()
        setupBottomNavigationView()


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
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
    }

    private fun setupNavigationView() {
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            val handled = handleNavigationItemSelected(menuItem.itemId)
            if (handled) {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            handled
        }
    }


    private fun handleNavigationItemSelected(itemId: Int): Boolean {
        return when (itemId) {
            R.id.home->{
                navigateToMainActivity()
                true
            }
            R.id.nowPlaying -> {
           navigateToFragment(NowPlayingFragment())
                true
            }
            R.id.popular->{
                navigateToFragment(PopularMoviesFragment())
                true
            }
            R.id.upcoming->{
                navigateToFragment(UpcomingMoviesFragment())
                true
            }
            R.id.topRated->{
                navigateToFragment(TopRatedFragment())
                true
            }
            R.id.favorites->{
                navigateToFragment(FavoritesFragment())
                true
            }
            R.id.trailers->{
                navigateToFragment(TrailerFragment())
                true
            }

            else -> {
                Log.w("Navigation", "Unhandled navigation item: $itemId")
                false
            }
        }
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }

    private fun setupBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home ->{
                    navigateToMainActivity()
                    true
                }
                R.id.nav_favorites ->{
                    navigateToFragment(FavoritesFragment())
                    true
                }
                R.id.nav_settings ->{
                    navigateToFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }
    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        findViewById<FrameLayout>(R.id.fragment_container).visibility = View.VISIBLE
        findViewById<ViewPager2>(R.id.view_pager).visibility = View.GONE
        findViewById<TabLayout>(R.id.tab_layout).visibility = View.GONE
        findViewById<VideoView>(R.id.VideoView).visibility = View.GONE
    }
    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
