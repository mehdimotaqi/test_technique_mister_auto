package ma.test.mister.auto.ui.views.userslist

import android.content.Context
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ma.test.mister.auto.data.apiservice.Api
import ma.test.mister.auto.data.models.UserItem
import ma.test.mister.auto.hasNetwork

class UserListPresenter(usersListView: UsersListView, context: Context) {

    private var usersLView = usersListView
    private var ctx = context
    private var disposable: Disposable? = null



    fun getUserList(){
        usersLView.showLoading()
        if (hasNetwork(ctx)!!){
            Api.invoke().getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: Observer<List<UserItem>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(user: List<UserItem>) {
                        usersLView.onSuccess(user)

                    }

                    override fun onError(e: Throwable) {
                        usersLView.onError(e.localizedMessage!!)
                    }

                })
        }else {
                usersLView.isOffline()
        }


    }

    fun destroy(){
            disposable!!.dispose()
    }
}