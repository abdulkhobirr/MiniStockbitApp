package com.example.ministockbitapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ministockbitapp.utils.pref.PrefManager
import com.example.ministockbitapp.utils.pref.UserPreferenceKey

class LoginViewModel(
        private val prefManager: PrefManager
): ViewModel() {

    fun login(username: String){
        prefManager.saveString(UserPreferenceKey.USERNAME, username)
        prefManager.saveBoolean(UserPreferenceKey.IS_LOGGED_IN, true)
    }

    fun isLoggedIn(): Boolean{
        if (prefManager.getBoolean(UserPreferenceKey.IS_LOGGED_IN)) return true
        return false
    }

    fun logout(){
        prefManager.removeKey(UserPreferenceKey.USERNAME)
        prefManager.removeKey(UserPreferenceKey.IS_LOGGED_IN)
    }
}