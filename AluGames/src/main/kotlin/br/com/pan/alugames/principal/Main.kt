package br.com.pan.alugames.principal

import br.com.pan.alugames.modelo.InfoJogo
import br.com.pan.alugames.modelo.Jogo
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner


fun main() {

    val leitura = Scanner(System.`in`)
    print("Digite o código de jogo que deseja buscar:")
    val busca = leitura.nextLine()
    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build()

    val response = client
        .send(request, BodyHandlers.ofString())
    val json = response.body()
    //println(json)

    val gson = Gson()
    //val meuInfoJogo = gson.fromJson(json, br.com.pan.alugames.modelo.InfoJogo::class.java)

//    val meuJogo = br.com.pan.alugames.modelo.Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb)
//    print(meuJogo)
//    try {
//        val meuJogo = br.com.pan.alugames.modelo.Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb)
//        print(meuJogo)
//    } catch (ex: IllegalStateException){
//        println("Código de jogo inexistente, tente outro id.")
//    }
    var meuJogo: Jogo? = null

    val resultado = runCatching {
        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
        meuJogo = Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb)

        print(meuJogo)
    }
    resultado.onFailure {
        println("Código inexistente, tente outro id.")
    }

    resultado.onSuccess {
        println("Deseja fazer uma descrição personalizada? S/N")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)){
            println("Digite a descição personalizada:")
            val descPersonalizada = leitura.nextLine()
            meuJogo?.descricao = descPersonalizada
        }else {
            meuJogo?.descricao = meuJogo?.titulo
        }
        print(meuJogo)
    }





}
