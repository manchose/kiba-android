package la.kiba.kiba.presentation.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import la.kiba.kiba.BR
import la.kiba.kiba.R
import la.kiba.kiba.databinding.ActivityLoginBinding
import la.kiba.kiba.presentation.viewmodel.LoginActivityViewModel
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private lateinit var binding: ActivityLoginBinding
    @Inject
    lateinit var loginActivityViewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInjector().inject(this)
        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.setVariable(BR.viewModel, loginActivityViewModel)
    }

    override fun onResume() {
        super.onResume()
    }
}
