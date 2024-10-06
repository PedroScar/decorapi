package com.pscarpellini.db

import com.pscarpellini.enums.ContaStatusEnum
import com.pscarpellini.model.funcionario.Funcionario
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object FuncionariosTable : IntIdTable("funcionarios") {
    val idLoja = integer("idloja")
    val nome = varchar("nome", 255)
    val endereco = varchar("endereco", 255)
    val cpf = varchar("cpf", 255)
    val email = varchar("email", 255).uniqueIndex()
    val telefone = varchar("telefone", 255)
    val status = varchar("status", 255)
}

class FuncionarioDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FuncionarioDAO>(FuncionariosTable)

    var idLoja by  FuncionariosTable.idLoja
    var nome by FuncionariosTable.nome
    var endereco by FuncionariosTable.endereco
    var cpf by FuncionariosTable.cpf
    var email by FuncionariosTable.email
    var telefone by FuncionariosTable.telefone
    var status by FuncionariosTable.status
}

fun funcionarioDaoToModel(dao: FuncionarioDAO) = Funcionario(
    dao.idLoja,
    dao.nome,
    dao.endereco,
    dao.cpf,
    dao.email,
    dao.telefone,
    ContaStatusEnum.valueOf(dao.status)
)