package br.com.appforge.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast


fun View.hideKeyboard(){
    val inputMethodManager = context.getSystemService(
        Context.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        windowToken, 0
    )
}

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