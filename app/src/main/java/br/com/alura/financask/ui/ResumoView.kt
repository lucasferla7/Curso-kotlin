package br.com.alura.financask.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import br.com.alura.financask.R
import br.com.alura.financask.extension.formataParaBrasileiro
import br.com.alura.financask.model.Resumo
import br.com.alura.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(private val view: View, private val context: Context, transacoes: List<Transacao>) {

    private val resumo : Resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun Atualiza(){
        AdicionaReceita()
        AdicionaDespesa()
        AdicionaTotal()
    }

    private fun AdicionaReceita() {
        val totalReceita = resumo.receita
        with(view.resumo_card_receita){
            setTextColor(corReceita)
            text = totalReceita.formataParaBrasileiro()
        }
    }

    private fun AdicionaDespesa() {
        var totalDespesa = resumo.despesa
        with(view.resumo_card_despesa){
            setTextColor(corDespesa)
            text = totalDespesa.formataParaBrasileiro()
        }
    }

    private fun AdicionaTotal(){
        var total = resumo.total
        with(view.resumo_card_total){
            setTextColor(CorPor(total))
            text = total.formataParaBrasileiro()
        }
    }

    private fun CorPor(total: BigDecimal): Int {
        return if (total >= BigDecimal.ZERO) {
            corReceita
        } else {
            corDespesa
        }
    }
}