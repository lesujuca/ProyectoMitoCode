package com.lerma.projectfinal.preferences

import android.app.Person
import android.content.Context

class SharedPreferencesHelper {
    companion object {
        const val PREFERENCES_NAME = "proyecto-preferences"
        const val KEY_USERNAME = "userName"
        const val KEY_NAME = "name"
        const val KEY_LASTNAME = "lastName"

        fun addUser(context: Context, userName: String, name: String, lastName: String) {
            val edit = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
            edit.putString(KEY_USERNAME, userName)
            edit.putString(KEY_NAME, name)
            edit.putString(KEY_LASTNAME, lastName)
            edit.apply()
        }

        fun getUserName(context: Context): String? {
            val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            return preferences.getString(KEY_USERNAME, "")
        }

        fun getName(context: Context): String? {
            val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            return preferences.getString(KEY_NAME, "")
        }

        fun getLastName(context: Context): String? {
            val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            return preferences.getString(KEY_LASTNAME, "")
        }

        fun deleteAll(context: Context) {
            val edit = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
            edit.clear()
            edit.apply()
        }
    }
}