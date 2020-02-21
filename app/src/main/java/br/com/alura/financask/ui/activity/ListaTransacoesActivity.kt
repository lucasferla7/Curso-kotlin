package br.com.alura.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financask.R
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import br.com.alura.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lista_transacoes)
        ConfiguraLista(TransacoesDeExemplo())
    }

    private fun ConfiguraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun TransacoesDeExemplo(): List<Transacao> {
        return listOf(
            Transacao(
                categoria = "Almoço de final de semana",
                valor = BigDecimal(40.00),
                tipo = Tipo.DESPESA
            ),
            Transacao(BigDecimal(333.00), "Roupas", Tipo.RECEITA, Calendar.getInstance())
        )
    }
}