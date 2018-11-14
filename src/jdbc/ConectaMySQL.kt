package jdbc

import java.sql.SQLException

import java.sql.Connection
import java.sql.DriverManager
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf


object ConectaMySQL {
    @JvmStatic
    fun main(args: Array<String>) {
        var conexao: Connection? = null
        try {
           Class .forName("com.mysql.jdbc.Driver").newInstance()

            // Registrando a classe JDBC, e os parâmetros de conexão em tempo de execução
            val url = "jdbc:mysql://localhost:3306/"
            val usuario = "root"
            val senha = ""

            conexao = DriverManager.getConnection(url, usuario, senha) //(_1_)
            println("Conectou!")
            conexao!!.close()
        } catch (e: SQLException) {
            println("Ocorreu um erro ao criar a conexão. Erro: " + e.message)
        }

    }
}
