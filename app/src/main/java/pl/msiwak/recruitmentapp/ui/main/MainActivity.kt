package pl.msiwak.recruitmentapp.ui.main

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import pl.msiwak.recruitmentapp.R
import pl.msiwak.recruitmentapp.ui.main.list.ListFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(R.id.mainContainer, ListFragment.TAG, ListFragment.newInstance())
    }

    private fun showFragment(
        @IdRes containerId: Int,
        tag: String,
        fragment: Fragment
    ) {
        supportFragmentManager.commit {
            replace(containerId, fragment, tag)
        }
    }

}