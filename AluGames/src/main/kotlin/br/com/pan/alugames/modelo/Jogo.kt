package br.com.pan.alugames.modelo

data class Jogo(val titulo: String, val capa: String) {
    var descricao:String? = null
    override fun toString(): String {
        return "Meu br.com.pan.alugames.modelo.Jogo\n" +
                "Titulo: $titulo \n" +
                "Capa: $capa \n" +
                "Descricao: $descricao \n"
    }


}