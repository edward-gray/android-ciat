package pro.edvard.ciat.presenter.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import pro.edvard.ciat.R

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        start()
    }

    private fun start() {
        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate(R.id.action_splashFragment_to_userListFragment)
        }, 2000)
    }

}