package br.com.appforge.core

import android.app.Activity
import android.widget.Toast

fun Activity.showMessage(text:String){
    Toast.makeText(this,
        text,
        Toast.LENGTH_SHORT)
        .show()
}