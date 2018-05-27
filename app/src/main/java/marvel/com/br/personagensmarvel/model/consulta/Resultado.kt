package marvel.com.br.personagensmarvel.model.consulta

import com.google.gson.annotations.SerializedName
import marvel.com.br.personagensmarvel.model.consulta.Heroi

class Resultado(@SerializedName("offset") var offset: Int, @SerializedName("total") var total: Int, @SerializedName("results") var results: ArrayList<Heroi>)


