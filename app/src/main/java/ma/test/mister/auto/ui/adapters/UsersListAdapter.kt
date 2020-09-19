package ma.test.mister.auto.ui.adapters

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item_row.view.*
import ma.test.mister.auto.R
import ma.test.mister.auto.data.models.UserItem
import ma.test.mister.auto.inflate
import ma.test.mister.auto.ui.views.usertasks.UserTasksActivity

class UsersListAdapter(private var users: List<UserItem>) : RecyclerView.Adapter<UsersListAdapter.UsersListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        val inflatedView = parent.inflate(R.layout.user_item_row, false)
        return UsersListViewHolder(inflatedView)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        val userItem = users[position]
        holder.bindUsersList(userItem)
    }

    class UsersListViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View = v
        private var userItem: UserItem? = null
        private var userId = "USER_ID"
        private var userName = "USER_NAME"


        fun bindUsersList(user: UserItem){
            userItem = user
            user.apply {
                view.user_name.text = this.name
                view.user_email.text = this.email
                view.user_user_name.text = this.username
            }

            view.apply {
                this.setOnClickListener {
                    val context = itemView.context
                    val showUserTasks = Intent(context, UserTasksActivity::class.java)
                    showUserTasks.putExtra(userId, userItem!!.id)
                    showUserTasks.putExtra(userName, userItem!!.name)
                    context.startActivity(showUserTasks)
                }
            }
        }
    }
}