package br.com.alura.financask.extension

import java.text.SimpleDateFormat
import java.util.Calendar

fun Calendar.formataParaBrasileiro(): String {
    return SimpleDateFormat("dd/MM/yyy").format(this.time)
}