package com.pscarpellini.model.funcionario

import com.pscarpellini.db.FuncionarioDAO
import com.pscarpellini.suspendTransaction

class FuncionarioRepositoryPostgres : FuncionarioRepository {
    override suspend fun adicionarUsuario(funcionario: Funcionario): Boolean = suspendTransaction {
        runCatching {
            FuncionarioDAO.new {
                idLoja = funcionario.idLoja
                nome = funcionario.nome
                endereco = funcionario.endereco
                cpf = funcionario.cpf
                email = funcionario.email
                telefone = funcionario.telefone
                status = funcionario.status.name
            }
            true
        }.getOrElse { false }
    }
}