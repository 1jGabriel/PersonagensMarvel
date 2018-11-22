package marvel.com.br.personagensmarvel.view.adapter

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import marvel.com.br.personagensmarvel.model.consulta.Heroi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recycler_view.view.*
import marvel.com.br.personagensmarvel.R

class HeroAdapter(val heroes: ArrayList<Heroi>?, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
                .load(heroes?.get(position)?.foto?.path + "." + heroes?.get(position)?.foto?.extension)
                .resize(120, 120)
                .centerCrop()
                .into(holder?.imageView)

        holder?.descriptionText?.text = heroes?.get(position)?.description
        holder?.nameText?.text = heroes?.get(position)?.name

        holder?.itemView?.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage(
                    if(heroes?.get(position)?.description==""){
                        "Não há descrição sobre este personagem."
                    }else{
                        heroes?.get(position)?.description
                    }
            )

            builder.show()
        }
    }

    override fun getItemCount(): Int {
        return heroes?.size!!
    }


    // Binds each animal in the ArrayList to a view
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val imageView = view.image_hero
    val nameText = view.name_hero
    val descriptionText = view.description_hero
}