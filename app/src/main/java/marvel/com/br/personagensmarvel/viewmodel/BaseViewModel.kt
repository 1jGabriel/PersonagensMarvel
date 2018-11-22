package marvel.com.br.personagensmarvel.viewmodel

import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import marvel.com.br.personagensmarvel.model.Service

abstract class BaseViewModel: ViewModel() {

    val disposables = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}