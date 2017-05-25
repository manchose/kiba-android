package la.kiba.kiba.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import la.kiba.kiba.R
import la.kiba.kiba.databinding.NonLoginTopFragmentBinding
import la.kiba.kiba.presentation.di.module.FragmentModule

/**
 * Created by sasaki_nobuya on 2017/05/06.
 */
class NonLoginTopFragment : BaseFragment() {
    lateinit var binding: NonLoginTopFragmentBinding

    companion object {
        fun newInstance(): NonLoginTopFragment {
            return NonLoginTopFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.non_login_top_fragment, container, false)
        binding = NonLoginTopFragmentBinding.bind(view)
        getInjector().fragmentComponent(FragmentModule(this)).inject(this)
        return view
    }

    override fun onResume() {
        super.onResume()
    }
}
