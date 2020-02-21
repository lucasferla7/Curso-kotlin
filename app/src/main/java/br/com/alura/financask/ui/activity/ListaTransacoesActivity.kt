package br.com.alura.financask.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financask.R
import br.com.alura.financask.delegate.TransacaoDelegate
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import br.com.alura.financask.ui.Dialog.AdicionaTransacaoDialog
import br.com.alura.financask.ui.ResumoView
import br.com.alura.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        ConfiguraResumo()
        ConfiguraLista()
        ConfiguraFab()
    }

    private fun ConfiguraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            ChamaDialogAdicao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            ChamaDialogAdicao(Tipo.DESPESA)
        }
    }

    private fun ChamaDialogAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(tipo, window.decorView as ViewGroup, this)
            .ConfiguraDialog(object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    AtualizaTransacoes(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }

    private fun AtualizaTransacoes(transacao: Transacao) {
        transacoes.add(
            Transacao(
                categoria = transacao.categoria,
                valor = transacao.valor,
                data = transacao.data,
                tipo = transacao.tipo
            )
        )
        ConfiguraLista()
        ConfiguraResumo()
    }

    private fun ConfiguraResumo() {
        val resumoView = ResumoView(window.decorView, this, transacoes)
        resumoView.Atualiza()
    }

    private fun ConfiguraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }
}