package jdbc
import java.sql.*
import java.util.ArrayList


class ContatoCrudJDBC {
    fun salvar(contato: Contato) {
        val conexao = this.geraConexao()
        var insereSt: PreparedStatement? = null
        val sql = "insert into contato (nome, telefone, email, dt_cad, obs) values (?, ?, ?, ?, ?)"
        try {
            insereSt = conexao!!.prepareStatement(sql)
            insereSt!!.setString(1, contato.nome)
            insereSt!!.setString(2, contato.telefone)
            insereSt!!.setString(3, contato.email)
            insereSt!!.setDate(4, contato.dataCadastro)
            insereSt!!.setString(5, contato.observacao)
            insereSt!!.executeUpdate()
        } catch (e: SQLException) {
            println("Erro ao incluir contato. Mensagem: " + e.message)
        } finally {
            try {
                insereSt!!.close()
                conexao!!.close()
            } catch (e: Throwable) {
                println("Erro ao fechar operações de inserção. Mensagem: " + e.message)
            }

        }
    }

    fun atualizar(contato: Contato) {
        val conexao = this.geraConexao()
        var atualizaSt: PreparedStatement? = null

        // Aqui não atualizamos o campo data de cadastro
        val sql = "update contato set nome=?, telefone=?, email=?, obs=? where codigo=?"

        try {
            atualizaSt = conexao!!.prepareStatement(sql)
            atualizaSt!!.setString(1, contato.nome)
            atualizaSt!!.setString(2, contato.telefone)
            atualizaSt!!.setString(3, contato.email)
            atualizaSt!!.setString(4, contato.observacao)
            atualizaSt!!.setInt(5, contato.codigo!!)
            atualizaSt!!.executeUpdate()
        } catch (e: SQLException) {
            println("Erro ao atualizar contato. Mensagem: " + e.message)
        } finally {
            try {
                atualizaSt!!.close()
                conexao!!.close()
            } catch (e: Throwable) {
                println("Erro ao fechar operações de atualização. Mensagem: " + e.message)
            }

        }
    }

    fun excluir(contato: Contato) {
        val conexao = this.geraConexao()
        var excluiSt: PreparedStatement? = null

        val sql = "delete from contato where codigo = ?"

        try {
            excluiSt = conexao!!.prepareStatement(sql)
            excluiSt!!.setInt(1, contato.codigo!!)
            excluiSt!!.executeUpdate()
        } catch (e: SQLException) {
            println("Erro ao excluir contato. Mensagem: " + e.message)
        } finally {
            try {
                excluiSt!!.close()
                conexao!!.close()
            } catch (e: Throwable) {
                println("Erro ao fechar operações de exclusão. Mensagem: " + e.message)
            }

        }
    }

    fun listar(): List<Contato> {
        val conexao = this.geraConexao()
        val contatos = ArrayList<Contato>()
        var consulta: Statement? = null
        var resultado: ResultSet? = null
        var contato: Contato? = null
        val sql = "select * from contato"
        try {
            consulta = conexao!!.createStatement()
            resultado = consulta!!.executeQuery(sql)
            while (resultado!!.next()) {
                contato = Contato()
                contato.codigo = resultado!!.getInt("codigo")
                contato.nome = resultado.getString("nome")
                contato.telefone = resultado.getString("telefone")
                contato.email = resultado.getString("email")
                contato.dataCadastro = resultado.getDate("dt_cad")
                contato.observacao = resultado.getString("obs")
                contatos.add(contato)
            }
        } catch (e: SQLException) {
            println("Erro ao buscar código do contato. Mensagem: " + e.message)
        } finally {
            try {
                consulta!!.close()
                resultado!!.close()
                conexao!!.close()
            } catch (e: Throwable) {
                println("Erro ao fechar operações de consulta. Mensagem: " + e.message)
            }

        }
        return contatos
    }

    fun buscaContato(valor: Int): Contato? {
        val conexao = this.geraConexao()
        var consulta: PreparedStatement? = null
        var resultado: ResultSet? = null
        var contato: Contato? = null

        val sql = "select * from contato where codigo = ?"

        try {
            consulta = conexao!!.prepareStatement(sql)
            consulta!!.setInt(1, valor)
            resultado = consulta!!.executeQuery()

            if (resultado!!.next()) {
                contato = Contato()
                contato.codigo = resultado!!.getInt("codigo")
                contato.nome = resultado.getString("nome")
                contato.telefone = resultado.getString("telefone")
                contato.email = resultado.getString("email")
                contato.dataCadastro = resultado.getDate("dt_cad")
                contato.observacao = resultado.getString("obs")
            }
        } catch (e: SQLException) {
            println("Erro ao buscar código do contato. Mensagem: " + e.message)
        } finally {
            try {
                consulta!!.close()
                resultado!!.close()
                conexao!!.close()
            } catch (e: Throwable) {
                println("Erro ao fechar operações de consulta. Mensagem: " + e.message)
            }

        }
        return contato
    }

    fun geraConexao(): Connection? {
        var conexao: Connection? = null

        try {
            // Registrando a classe JDBC no sistema em tempo de execução
            Class.forName("com.mysql.jdbc.Driver")
            val url = "jdbc:mysql://localhost/agenda"
            val usuario = "root"
            val senha = ""
            conexao = DriverManager.getConnection(url, usuario, senha)
        } catch (e: ClassNotFoundException) {
            println("Classe não encontrada. Erro: " + e.message)
        } catch (e: SQLException) {
            println("Ocorreu um erro de SQL. Erro: " + e.message)
        }

        return conexao
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val contatoCRUDJDBC = ContatoCrudJDBC()

            // Criando um primeiro contato
            val beltrano = Contato()
            beltrano.nome = "Beltrano Solar"
            beltrano.telefone = "(47) 5555-3333"
            beltrano.email = "beltrano@teste.com.br"
            beltrano.dataCadastro = Date(System.currentTimeMillis())
            beltrano.observacao = "Novo cliente"
            contatoCRUDJDBC.salvar(beltrano)

            // Criando um segundo contato
            val fulano = Contato()
            fulano.nome = "Fulano Lunar"
            fulano.telefone = "(47) 7777-2222"
            fulano.email = "fulano@teste.com.br"
            fulano.dataCadastro = Date(System.currentTimeMillis())
            fulano.observacao = "Novo contato – possível cliente"
            contatoCRUDJDBC.salvar(fulano)
            println("Contatos cadastrados: " + contatoCRUDJDBC.listar().size)
        }
    }

}
