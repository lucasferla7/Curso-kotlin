package br.com.alura.financask.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formataParaBrasileiro(): String {
    return DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
        .format(this)
        .replace("R$", "R$ ")
        .replace("-R$ ", "R$ -")
}