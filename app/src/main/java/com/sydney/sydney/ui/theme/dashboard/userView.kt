package com.sydney.sydney.ui.theme.dashboard


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> get() = _location

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> get() = _imageUrl

    private val _campus = MutableLiveData<String>()
    val campus: LiveData<String> get() = _campus

    fun setUserData(name: String, email: String, location: String, imageUrl: String, campus: String) {
        _name.value = name
        _email.value = email
        _location.value = location
        _imageUrl.value = imageUrl
        _campus.value = campus
    }
    fun accountExists(email: String): Boolean {
        // Replace this with actual logic to check if the account exists
        return email == "existing@example.com"
    }
}