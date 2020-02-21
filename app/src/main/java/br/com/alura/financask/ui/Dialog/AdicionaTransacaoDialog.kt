package br.com.alura.financask.ui.Dialog

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import br.com.alura.financask.R
import br.com.alura.financask.delegate.TransacaoDelegate
import br.com.alura.financask.extension.ConverteParaCalendar
import br.com.alura.financask.extension.formataParaBrasileiro
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.util.*

class AdicionaTransacaoDialog(private val tipo: Tipo, private val viewGroup: ViewGroup, private val context: Context) {

    private val viewCriada = CriaLayout()
    private val campoValor = viewCriada.form_transacao_valor
    private val campoData = viewCriada.form_transacao_data
    private val campoCategoria = viewCriada.form_transacao_categoria

    fun ConfiguraDialog(transacaoDelegate: TransacaoDelegate) {
            ConfiguraData()
            ConfiguraCategoria()
            ConfiguraFormulario(transacaoDelegate)
    }

    private fun ConfiguraFormulario(transacaoDelegate: TransacaoDelegate) {
        AlertDialog.Builder(context)
            .setTitle(TituloPor())
            .setView(viewCriada)
            .setPositiveButton("Adicionar") { _, _ ->
                val valor = campoValor.text.toString().toBigDecimal()
                val data = campoData.text.toString().ConverteParaCalendar()
                val categoria = campoCategoria.selectedItem.toString()
                transacaoDelegate.delegate(Transacao(valor, categoria, tipo, data))

            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun TituloPor(): Int {
        return if (tipo == Tipo.RECEITA) {
            R.string.adiciona_receita
        } else {
            R.string.adiciona_despesa
        }
    }

    private fun ConfiguraCategoria() {
        val adapter = ArrayAdapter.createFromResource(
            context,
            CategoriaPor(),
            android.R.layout.simple_spinner_dropdown_item
        )
        viewCriada.form_transacao_categoria.adapter = adapter
    }

    private fun CategoriaPor(): Int {
        return if (tipo == Tipo.RECEITA) {
            R.array.categorias_de_receita
        } else {
            R.array.categorias_de_despesa
        }
    }

    private fun CriaLayout(): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.form_transacao, viewGroup, false)
    }

    private fun ConfiguraData() {
        var hoje = Calendar.getInstance()
        with(viewCriada) {
            form_transacao_data.setText(Calendar.getInstance().formataParaBrasileiro())
            form_transacao_data.setOnClickListener {
                DatePickerDialog(
                    context,
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        var dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(year, month, dayOfMonth)
                        form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
                    },
                    hoje.get(Calendar.YEAR),
                    hoje.get(Calendar.MONTH),
                    hoje.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
    }
}