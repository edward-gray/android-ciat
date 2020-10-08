package pro.edvard.ciat.framework.presenter.userdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_detail.*
import pro.edvard.ciat.R
import pro.edvard.ciat.business.domain.model.User

@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    private lateinit var navController: NavController
    private lateinit var user: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        user = arguments?.getParcelable("user")!!
        handleUI()
    }

    private fun handleUI() {
        Glide.with(requireContext()).load(user.avatar).into(user_detail_image_avatar)
        user_detail_text_full_name.text = user.getFullName()
        user_detail_text_email.text = user.email
    }



}