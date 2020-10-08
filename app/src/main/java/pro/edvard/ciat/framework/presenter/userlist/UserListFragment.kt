package pro.edvard.ciat.framework.presenter.userlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_list.*
import pro.edvard.ciat.R
import pro.edvard.ciat.framework.presenter.adapter.UserRowAdapter

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_user_list), UserRowAdapter.OnItemClickListener {

    private val viewModel: UserListViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var userRowAdapter: UserRowAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupAdapter()
        test()
    }

    private fun test() {
        viewModel.getReqres()
        viewModel.reqres.observe(viewLifecycleOwner) {
            it.users.map { user ->
                println("DEBUG: $user")
            }
        }
    }

    private fun setupAdapter() {
        userRowAdapter = UserRowAdapter(requireContext(), this)
        userlist_recycler_view.adapter = userRowAdapter
    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

}