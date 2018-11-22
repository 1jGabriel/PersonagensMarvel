package marvel.com.br.personagensmarvel

import android.app.Application
import marvel.com.br.personagensmarvel.module.myModule
import org.koin.android.ext.android.startKoin


class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(myModule))
    }

}