package br.com.appforge.core

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog

class LoadingAlert (
    private val context: Context
){

    private val viewLoading = View.inflate(context, R.layout.loading_layout,null)

    private var alertDialog: AlertDialog? = null

    fun show(message:String){
        val alertBuilder = AlertDialog.Builder(context)
            .setTitle(message)
            .setView(viewLoading)
            .setCancelable(false)

        alertDialog = alertBuilder.create()
        alertDialog?.show()

    }
    fun close(){
        alertDialog?.dismiss()
        alertDialog?.setOnDismissListener {
            val viewGroup = viewLoading.parent as ViewGroup
            viewGroup.removeView(viewLoading)
        }
    }
}