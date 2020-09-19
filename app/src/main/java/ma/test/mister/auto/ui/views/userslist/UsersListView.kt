package ma.test.mister.auto.ui.views.userslist

import ma.test.mister.auto.data.models.UserItem


interface UsersListView {
    fun showLoading()
    fun onError(message: String)
    fun onSuccess(users: List<UserItem>)
    fun isOffline()
}