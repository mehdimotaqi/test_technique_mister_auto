package ma.test.mister.auto.ui.views.usertasks

import android.content.Context
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ma.test.mister.auto.data.apiservice.Api
import ma.test.mister.auto.data.models.TaskItem
import ma.test.mister.auto.hasNetwork


class UserTasksPresenter(userTasksView: UserTasksView, context: Context) {

    private var userTView = userTasksView
    private var ctx = context
    private var disposable: Disposable? = null

    fun getTasksByUserId(userId: String){
        userTView.showLoading()
        if (hasNetwork(ctx)!!){
            Api.invoke().getTasksByUserId(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<TaskItem>>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(task: List<TaskItem>) {
                        userTView.onSuccess(task)
                    }

                    override fun onError(e: Throwable) {
                        userTView.onError(e.localizedMessage!!)
                    }

                })
        }else{
            userTView.isOffline()
        }

    }

    fun destroy(){
            disposable?.dispose()
    }
}