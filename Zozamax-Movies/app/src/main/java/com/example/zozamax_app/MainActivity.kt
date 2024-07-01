package com.example.zozamax_app

import android.content.Intent
import android.os.Bundle
import android.view.SurfaceHolder
import android.widget.ImageButton
import android.util.Log
import android.view.SurfaceView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.zozamax_app.fragments.SingleMovieFragment
import com.google.android.material.navigation.NavigationView
import androidx.viewpager2.widget.ViewPager2
import com.example.zozamax_app.fragments.NowPlayingFragment
import com.example.zozamax_app.fragments.PopularMoviesFragment
import com.example.zozamax_app.fragments.TopRatedFragment
import com.example.zozamax_app.fragments.UpcomingMoviesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var surfaceView: SurfaceView
    private lateinit var playPauseButton: ImageButton
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView
    private var isPlaying = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setupNavigationView()
        initializeViews()
        setupToolbar()
        setupViewPager()
        setupBottomNavigationView()
        setupSurfaceView()

        playPauseButton.setOnClickListener{
            if (isPlaying){
                pauseTrailer()
                playPauseButton.setImageResource(R.drawable.play)
            } else {
                playTrailer()
                playPauseButton.setImageResource(R.drawable.pause)
            }
            isPlaying=!isPlaying
        }


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
        surfaceView=findViewById(R.id.trailerSurfaceView)
        playPauseButton=findViewById(R.id.playPauseButton)
        tabLayout=findViewById(R.id.tab_layout)
        viewPager=findViewById(R.id.view_pager)
        bottomNavigationView=findViewById(R.id.bottom_navigation_view)
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

    }
    private fun setupViewPager(){
        val adapter=ViewPagerAdapter(this)
        viewPager.adapter=adapter
        TabLayoutMediator(tabLayout,viewPager){
            tab, position->
            tab.text = adapter.getPageTitle(position)
        }.attach()

    }

    private fun setupBottomNavigationView(){
        bottomNavigationView.setOnNavigationItemSelectedListener {
            item-> when(item.itemId){
                R.id.nav_home->{
                    true
                }
            R.id.nav_favorites->{
                true
            }
            R.id.nav_settings->{
                true
            }
            else -> false
            }
        }
    }
    private fun setupSurfaceView(){
        surfaceView.holder.addCallback(object: SurfaceHolder.Callback{
            override fun surfaceCreated(holder: SurfaceHolder) {
                playTrailer()
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
               pauseTrailer()
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
               pauseTrailer()
            }
        })
    }
    private fun playTrailer(){

    }
    private fun pauseTrailer(){

    }
}
