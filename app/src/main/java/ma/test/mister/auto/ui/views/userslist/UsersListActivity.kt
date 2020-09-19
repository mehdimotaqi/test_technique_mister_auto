package ma.test.mister.auto.ui.views.userslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ma.test.mister.auto.R
import ma.test.mister.auto.data.models.UserItem
import ma.test.mister.auto.showToast
import ma.test.mister.auto.ui.adapters.UsersListAdapter

class UsersListActivity : AppCompatActivity(), UsersListView {

    private var usersListPresenter: UserListPresenter? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var usersListAdapter: UsersListAdapter
    private lateinit var userListViewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userListViewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        linearLayoutManager = LinearLayoutManager(this)
        users_list_recyclerView.layoutManager = linearLayoutManager
        usersListPresenter = UserListPresenter(this, this)
        usersListPresenter?.getUserList()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onError(message: String) {
        showToast(message)
        progressBar.visibility = View.GONE
    }

    override fun onSuccess(users: List<UserItem>) {
        usersListAdapter = UsersListAdapter(users)
        users_list_recyclerView.adapter = usersListAdapter
        userListViewModel.insert(users)
        progressBar.visibility = View.GONE
    }

    override fun isOffline() {
        userListViewModel.allUsers?.observe(this, Observer{
            if (it.isEmpty()){
                showToast("No Internet Connection")
                progressBar.visibility = View.GONE
            }else{
                usersListAdapter = UsersListAdapter(it)
                users_list_recyclerView.adapter = usersListAdapter
                progressBar.visibility = View.GONE
            }
        })
    }


    override fun onStop() {
        super.onStop()
        usersListPresenter?.destroy()
    }

}