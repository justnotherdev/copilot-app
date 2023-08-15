package com.justanotherdev.copilot_app.presentation.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.google.firebase.FirebaseApp
import com.justanotherdev.copilot_app.R
import com.justanotherdev.copilot_app.databinding.ActivityMainBinding
import com.justanotherdev.copilot_app.presentation.view.fragment.DiagnosticsFragment
import com.justanotherdev.copilot_app.presentation.view.fragment.MapFragment
import com.justanotherdev.copilot_app.presentation.view.fragment.OverviewFragment
import com.justanotherdev.copilot_app.presentation.view.fragment.ProfileFragment

class MainActivity : AppCompatActivity(), OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(applicationContext)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(OverviewFragment.newInstance())
        binding.bottomNavigationView.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.overview -> replaceFragment(OverviewFragment.newInstance())
            R.id.map -> replaceFragment(MapFragment.newInstance())
            R.id.notifications -> replaceFragment(ProfileFragment.newInstance())
            R.id.diagnosis -> replaceFragment(DiagnosticsFragment.newInstance())
        }
        return true
    }

    private fun replaceFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
}
