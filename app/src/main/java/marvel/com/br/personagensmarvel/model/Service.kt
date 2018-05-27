package marvel.com.br.personagensmarvel.model

import marvel.com.br.personagensmarvel.model.consulta.Dados
import io.reactivex.Observable
import retrofit2.http.*

interface Service {

    @GET("v1/public/characters/{id}?ts=1&apikey=4e3649488eca2670e69ff9e2d527bc4e&hash=afa953bc4bb3a148854666b4e5280ca2")
    @Headers("Content-Type: application/json")
    fun getHero(@Path("id")id: Int ) : Observable<Dados>

    @GET("v1/public/characters?")
    @Headers("Content-Type: application/json")
    fun getHeroes(@Query("offset")offset: String,
                  @Query("ts")ts: String = "1",
                  @Query("apikey")apikey: String = "4e3649488eca2670e69ff9e2d527bc4e",
                  @Query("hash")hash: String = "afa953bc4bb3a148854666b4e5280ca2") : Observable<Dados>

}