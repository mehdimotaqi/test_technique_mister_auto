package ma.test.mister.auto.ui.views.usertasks

import ma.test.mister.auto.data.models.TaskItem


interface UserTasksView {
    fun showLoading()
    fun onError(message: String)
    fun onSuccess(tasks: List<TaskItem>)
    fun isOffline()
}