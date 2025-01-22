package br.com.appforge.core

sealed class UIState<out T> {
    class Success<T>(data: T): UIState<T>()
    class Error(val errorMessage: String):UIState<Nothing>()


}