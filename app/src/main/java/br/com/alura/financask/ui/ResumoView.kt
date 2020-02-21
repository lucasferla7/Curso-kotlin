package br.com.alura.financask.ui

import android.view.View
import br.com.alura.financask.extension.formataParaBrasileiro
import br.com.alura.financask.model.Resumo
import br.com.alura.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*

class ResumoView(private val view: View, transacoes: List<Transacao>) {

    private val resumo : Resumo = Resumo(transacoes)

    fun AdicionaReceita() {
        val totalReceita = resumo.receita()
        view.resumo_card_receita.text = totalReceita.formataParaBrasileiro()
    }

    fun AdicionaDespesa() {
        var totalDespesa = resumo.despesa()
        view.resumo_card_despesa.text = totalDespesa.formataParaBrasileiro()
    }

    fun AdicionaTotal(){
        var total = resumo.total()
        view.resumo_card_total.text = total.formataParaBrasileiro()
    }
}