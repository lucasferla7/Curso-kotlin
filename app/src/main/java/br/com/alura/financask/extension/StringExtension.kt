package br.com.alura.financask.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.limitaEmAte(caracteres: Int): String {
    if (this.length > caracteres)
        return "${this.substring(0, caracteres)}..."
    
    return this
}

fun String.ConverteParaCalendar() : Calendar {
    val dataDate = SimpleDateFormat("dd/MM/yyyy").parse(this)
    val data = Calendar.getInstance()
    data.time = dataDate
    return data
}