package marvel.com.br.personagensmarvel

import android.app.Application
import marvel.com.br.personagensmarvel.module.ApiComponent
import marvel.com.br.personagensmarvel.module.ApiModule
import marvel.com.br.personagensmarvel.module.DaggerApiComponent


class BaseApplication: Application() {

    lateinit var graph: ApiComponent

    override fun onCreate() {
        super.onCreate()

        graph = DaggerApiComponent.builder().apiModule(ApiModule(this)).build()
    }

}