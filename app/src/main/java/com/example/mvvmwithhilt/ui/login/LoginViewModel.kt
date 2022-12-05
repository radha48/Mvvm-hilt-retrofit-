package com.example.mvvmwithhilt.ui.login

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmwithhilt.utils.Helper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel@Inject constructor(): ViewModel() {



    fun validateCredentials(email: String, password: String): Pair<Boolean, String> {
        var result = Pair(true, "")
        if (TextUtils.isEmpty(email)) {
            result = Pair(false, "Please enter email")
        }else if (!Helper.isValidEmail(email)) {
            result = Pair(false, "Email is invalid")
        } else if (TextUtils.isEmpty(password)) {
            result = Pair(false, "Please enter password")
        } else if (password.length < 8 ) {
            result = Pair(false, "Password length should be greater than 8")
        }else if (password.length > 15 ) {
            result = Pair(false, "Password length should be less than 15")
        }
        return result
    }

}