package ma.test.mister.auto.ui.adapters

import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_item_row.view.*
import kotlinx.android.synthetic.main.user_item_row.view.*
import ma.test.mister.auto.R
import ma.test.mister.auto.data.models.TaskItem
import ma.test.mister.auto.data.models.UserItem
import ma.test.mister.auto.inflate
import ma.test.mister.auto.ui.views.usertasks.UserTasksActivity

class TaskListAdapter(private var tasks: List<TaskItem>) : RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val inflatedView = parent.inflate(R.layout.task_item_row, false)
        return TaskListViewHolder(inflatedView)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val taskItem = tasks[position]
        holder.bindUsersList(taskItem)
    }

    class TaskListViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View = v
        private var taskItem: TaskItem? = null


        fun bindUsersList(task: TaskItem){
            taskItem = task
            taskItem.apply {
                view.task_title.text = this!!.title
                if (this.completed) {
                    view.task_title.setBackgroundColor(Color.GREEN)
                }
            }
        }
    }}