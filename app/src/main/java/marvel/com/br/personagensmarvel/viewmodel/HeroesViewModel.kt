package marvel.com.br.personagensmarvel.viewmodel

import android.arch.lifecycle.MutableLiveData
import marvel.com.br.personagensmarvel.model.consulta.Heroi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import marvel.com.br.personagensmarvel.model.Service

class HeroesViewModel(val service : Service) : BaseViewModel() {
    var status = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()
    var heroi = MutableLiveData<Heroi>()
    var herois = MutableLiveData<ArrayList<Heroi>>()


    fun getHero(id: Int) {
        //homem de ferro = 1009368
        disposables.add(service.getHero(id)!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { status.value = true }
                .doFinally { status.value = false }
                .subscribe(
                        {
                            heroi.value = it.data.results[0]
                        },
                        {
                            error.value = it.message
                        }
                )
        )
    }

    fun getAllHeroes(offset : Int) {
        disposables.add(service.getHeroes(offset.toString())!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { status.value = true }
                .doFinally { status.value = false }
                .subscribe(
                        {
                            herois.value = it.data.results
                        },
                        {
                            error.value = it.message
                        }
                )
        )
    }
}