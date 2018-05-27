package marvel.com.br.personagensmarvel.model.consulta

import com.google.gson.annotations.SerializedName

class Dados(@SerializedName("code") var code: Int, @SerializedName("status") var status: String, @SerializedName("data") var data: Resultado)