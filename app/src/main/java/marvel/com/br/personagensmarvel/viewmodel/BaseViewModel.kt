package marvel.com.br.personagensmarvel.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import marvel.com.br.personagensmarvel.model.Service
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {

    var disposables = CompositeDisposable()

    @Inject
    lateinit var service: Service

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
        Log.d("ViewModel", "onCleared")
    }

    abstract fun refreshItens()

}