package com.example.zozamax_app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.zozamax_app.fragments.NowPlayingFragment
import com.example.zozamax_app.fragments.PopularMoviesFragment
import com.example.zozamax_app.fragments.TopRatedFragment
import com.example.zozamax_app.fragments.UpcomingMoviesFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        PopularMoviesFragment(),
        NowPlayingFragment(),
        TopRatedFragment(),
        UpcomingMoviesFragment(),

    )

    private val fragmentTitles = listOf(
        "Popular",
        "Now Playing",
        "Top Rated",
        "Upcoming"
    )

    override fun getItemCount(): Int {
       return fragments.size
    }

    fun getPageTitle(position: Int): CharSequence {
        return fragmentTitles[position]
    }

    override fun createFragment(position: Int): Fragment {
       return fragments[position]
    }
}