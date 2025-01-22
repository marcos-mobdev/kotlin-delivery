package br.com.appforge.core

import android.app.Activity
import android.content.Intent
import android.widget.Toast

fun Activity.showMessage(text:String){
    Toast.makeText(this,
        text,
        Toast.LENGTH_SHORT)
        .show()
}

fun <T>Activity.navigateTo(destiny: Class<T>){
    startActivity(Intent(this, destiny))
    finish()
}