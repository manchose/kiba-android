package la.kiba.kiba.presentation.viewmodel

import android.databinding.Bindable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import la.kiba.kiba.BR
import la.kiba.kiba.domain.usecase.SignInUseCase
import javax.inject.Inject

/**
 * Created by sasaki_nobuya on 2017/05/06.
 */
class LoginActivityViewModel @Inject constructor(val signInUseCase: SignInUseCase) : ViewModel() {
    @Bindable var instance = ""
        set(value) {
            field = value
            if (!instanceWatcher.isEditMode) {
                notifyPropertyChanged(BR.instance)
            }
        }
    @Bindable var email = ""
        set(value) {
            field = value
            if (!emailWatcher.isEditMode) {
                notifyPropertyChanged(BR.email)
            }
        }
    @Bindable var password = ""
        set(value) {
            field = value
            if (!passwordWatcher.isEditMode) {
                notifyPropertyChanged(BR.password)
            }
        }
    val instanceWatcher = object : TwoWayTextWatcher() {
        override fun onTextChanged(text: CharSequence?) {
            instance = text.toString()
        }
    }
    val emailWatcher = object : TwoWayTextWatcher() {
        override fun onTextChanged(text: CharSequence?) {
            email = text.toString()
        }
    }
    val passwordWatcher = object : TwoWayTextWatcher() {
        override fun onTextChanged(text: CharSequence?) {
            password = text.toString()
        }
    }

    fun login(view: View) {
        signInUseCase.login(instance)
    }

    abstract inner class TwoWayTextWatcher : TextWatcher {
        var isEditMode: Boolean = false

        override fun afterTextChanged(text: Editable?) {
        }

        override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            isEditMode = true
            onTextChanged(text)
            isEditMode = false
        }

        abstract fun onTextChanged(text: CharSequence?)
    }
}