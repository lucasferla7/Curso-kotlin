package br.com.alura.financask.model

import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {
    fun receita () : BigDecimal{
        var total = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.RECEITA) {
                total += transacao.valor
            }
        }
        return  total
    }

    fun despesa() : BigDecimal{
        var total = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.DESPESA) {
                total += transacao.valor
            }
        }
        return total
    }

    fun total() : BigDecimal{
        return receita().subtract(despesa())
    }
}