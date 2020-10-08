package pro.edvard.ciat.framework.presenter.userlist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pro.edvard.ciat.R
import pro.edvard.ciat.framework.presenter.adapter.UserRowAdapter

@ExperimentalPagingApi
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
        lifecycleScope.launch {
            viewModel.users.collect {
                userRowAdapter.submitData(it)
            }
        }
    }

    private fun setupAdapter() {
        userRowAdapter = UserRowAdapter(requireContext(), this)
        userlist_recycler_view.adapter = userRowAdapter
        userlist_recycler_view.layoutManager = LinearLayoutManager(activity?.applicationContext)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(requireContext(), position.toString(), Toast.LENGTH_SHORT).show()
    }

}