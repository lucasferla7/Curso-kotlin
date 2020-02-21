package br.com.alura.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import br.com.alura.financask.R
import br.com.alura.financask.extension.formataParaBrasileiro
import br.com.alura.financask.extension.limitaEmAte
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(
    private val transacoes: List<Transacao>,
    private val context: Context
) : BaseAdapter() {

    private val limiteDaCategoria = 14

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCriada =
            LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[position]

        AdicionaValor(transacao, viewCriada)
        AdicionaIcone(transacao, viewCriada)
        AdicionaCategoria(viewCriada, transacao)
        AdicionaData(viewCriada, transacao)

        return viewCriada
    }

    private fun AdicionaData(
        viewCriada: View,
        transacao: Transacao
    ) {
        viewCriada.transacao_data.text = transacao.data.formataParaBrasileiro()
    }

    private fun AdicionaCategoria(
        viewCriada: View,
        transacao: Transacao
    ) {
        viewCriada.transacao_categoria.text = transacao.categoria.limitaEmAte(limiteDaCategoria)
    }

    private fun AdicionaIcone(
        transacao: Transacao,
        viewCriada: View
    ) {
        viewCriada.transacao_icone.setBackgroundResource(IconePor(transacao, viewCriada))
    }

    private fun IconePor(transacao: Transacao, viewCriada: View): Int {
        if (transacao.tipo == Tipo.RECEITA)
            return R.drawable.icone_transacao_item_receita

        return R.drawable.icone_transacao_item_despesa
    }

    private fun AdicionaValor(transacao: Transacao, viewCriada: View) {
        var cor = CorPor(transacao)

        viewCriada.transacao_valor.setTextColor(cor)
        viewCriada.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }

    private fun CorPor(transacao: Transacao): Int {
        if (transacao.tipo == Tipo.RECEITA)
            return ContextCompat.getColor(context, R.color.receita)

        return ContextCompat.getColor(context, R.color.despesa)
    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}