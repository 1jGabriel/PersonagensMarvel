package marvel.com.br.personagensmarvel.model.consulta

import com.google.gson.annotations.SerializedName

class Foto(@SerializedName("path")  var path: String, @SerializedName("extension")  var extension: String)