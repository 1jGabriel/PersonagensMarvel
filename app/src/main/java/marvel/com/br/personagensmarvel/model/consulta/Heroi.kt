package marvel.com.br.personagensmarvel.model.consulta

import com.google.gson.annotations.SerializedName
import marvel.com.br.personagensmarvel.model.consulta.Foto

class Heroi(@SerializedName("name") var name: String, @SerializedName("id") var id: Int, @SerializedName("description") var description: String, @SerializedName("thumbnail") var foto: Foto)

