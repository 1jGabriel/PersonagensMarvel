package marvel.com.br.personagensmarvel.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_heroes.*
import marvel.com.br.personagensmarvel.BaseApplication
import marvel.com.br.personagensmarvel.R
import marvel.com.br.personagensmarvel.view.adapter.HeroAdapter
import marvel.com.br.personagensmarvel.viewmodel.HeroesViewModel
import marvel.com.br.personagensmarvel.viewmodel.ViewModelFactory
import javax.inject.Inject

class HeroesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<HeroesViewModel>

    private var viewModel: HeroesViewModel? = null

    private var offset: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)
        (application as BaseApplication).graph.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(HeroesViewModel::class.java)
        doBindings()
        viewModel?.getAllHeroes(offset)
    }

    fun doBindings() {
        super.onStart()
        observeLoading()
        observeError()
        observeHeroes()
        observeHero()

        previous_btn.setOnClickListener {
            if (offset > 0) {
                offset -= 20
                viewModel?.getAllHeroes(offset)
            }
            if (offset == 0) {
                previous_btn.visibility = View.INVISIBLE
                next_btn.visibility = View.VISIBLE
            }
        }

r        next_btn.setOnClickListener {
            offset += 20
            viewModel?.getAllHeroes(offset)
        }
    }

    fun observeLoading() {
        viewModel?.status?.observe(this, Observer {
            val loadingFlag = it ?: false

            loading.visibility = if (loadingFlag) View.VISIBLE else View.GONE
            recycler.visibility = if (loadingFlag) View.INVISIBLE else View.VISIBLE
            if (offset >19) {
                previous_btn.visibility = if (loadingFlag) View.GONE else View.VISIBLE
                next_btn.visibility = if (loadingFlag) View.GONE else View.VISIBLE
            }
        })
    }


    fun observeError() {
        viewModel?.error?.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    fun observeHero() {
        viewModel?.heroi?.observe(this, Observer {

        })
    }

    fun observeHeroes() {
        viewModel?.herois?.observe(this, Observer {
            recycler.layoutManager = LinearLayoutManager(this)
            recycler.layoutManager = GridLayoutManager(this, 3)
            recycler.adapter = HeroAdapter(it, this)
            if(offset == 0){
                next_btn.visibility = View.VISIBLE
            }
        })
    }
}