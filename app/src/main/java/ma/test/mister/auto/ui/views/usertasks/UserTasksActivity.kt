package ma.test.mister.auto.ui.views.usertasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_user_tasks.*
import ma.test.mister.auto.R
import ma.test.mister.auto.data.models.TaskItem
import ma.test.mister.auto.showToast
import ma.test.mister.auto.ui.adapters.TaskListAdapter

class UserTasksActivity : AppCompatActivity(), UserTasksView {

    private var actionBar: ActionBar? = null
    private var userTasksPresenter: UserTasksPresenter? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var taskListAdapter: TaskListAdapter
    private lateinit var userTasksViewModel: UserTasksViewModel

    private var userName: String? = null
    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_tasks)
        userName = intent.getStringExtra("USER_NAME")
        userId = intent.getIntExtra("USER_ID", 0)
        userTasksViewModel = UserTasksViewModel(application)
        userTasksPresenter = UserTasksPresenter(this, this)
        userTasksPresenter?.getTasksByUserId(userId.toString())
        actionBar = supportActionBar
        actionBar?.apply {
            this.title = userName
            setDisplayHomeAsUpEnabled(true)
        }

        linearLayoutManager = LinearLayoutManager(this)
        task_recyclerView.layoutManager = linearLayoutManager
    }

    override fun showLoading() {
        task_progressBar.visibility = View.VISIBLE
    }

    override fun onError(message: String) {
        showToast(message)
        task_progressBar.visibility = View.GONE
    }

    override fun isOffline() {
        userTasksViewModel.getTasksByUserId(userId!!).observe(this, Observer {
            if (it.isEmpty()){
                showToast("No Internet Connection")
                task_progressBar.visibility = View.GONE
            }else{
                taskListAdapter = TaskListAdapter(it)
                task_recyclerView.adapter = taskListAdapter
                task_progressBar.visibility = View.GONE
            }

        })
    }

    override fun onSuccess(tasks: List<TaskItem>) {
        taskListAdapter = TaskListAdapter(tasks)
        task_recyclerView.adapter = taskListAdapter
        userTasksViewModel.insert(tasks)
        task_progressBar.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onStop() {
        super.onStop()
        userTasksPresenter?.destroy()
    }
}