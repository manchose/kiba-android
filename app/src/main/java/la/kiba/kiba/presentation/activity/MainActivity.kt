package la.kiba.kiba.presentation.activity

import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v13.app.FragmentStatePagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import la.kiba.kiba.R
import la.kiba.kiba.databinding.ActivityMainBinding
import la.kiba.kiba.presentation.adapter.MenuListAdapter
import la.kiba.kiba.presentation.fragment.NonLoginTopFragment
import la.kiba.kiba.presentation.fragment.TimelineFragment
import la.kiba.kiba.presentation.viewmodel.MainActivityViewModel
import la.kiba.kiba.presentation.viewmodel.MenuListViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var mainActivityViewModel: MainActivityViewModel
    @Inject lateinit var menuListViewModel: MenuListViewModel

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInjector().inject(this)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        // TODO: onCreateでやるのが最適? 外す必要ない?
    }

    override fun onResume() {
        super.onResume()
        initViewPager()
        initTab()
        initDrawer()
    }

    private fun initDrawer() {
        binding.leftDrawer.layoutManager = LinearLayoutManager(applicationContext)
        binding.leftDrawer.adapter = MenuListAdapter(menuListViewModel)
        binding.leftDrawer.adapter.notifyDataSetChanged()
    }

    // TODO: sasaki タブの数が1以下だったらGONEする処理に変更する(isLoginがfalseの場合はタブ数が0と考える)
    private fun initTab() {
        if (!mainActivityViewModel.isLogin()) {
            binding.tabs.setupWithViewPager(binding.pager)
        } else {
            binding.tabs.visibility = View.GONE
        }
    }

    private fun initViewPager() {
        binding.pager.adapter = TimelineFragmentStatePagerAdapter(fragmentManager)
    }

    inner class TimelineFragmentStatePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            if (!mainActivityViewModel.isLogin()) {
                return when (position) {
                    0 -> TimelineFragment.newInstance()
                    1 -> TimelineFragment.newInstance()
                    2 -> TimelineFragment.newInstance()
                    3 -> TimelineFragment.newInstance()
                    else -> TimelineFragment.newInstance()
                }
            } else {
                return NonLoginTopFragment.newInstance()
            }
        }

        override fun getCount(): Int {
            // TODO: 未ログイン状態の場合は、チュートリアル的な画面がでる。その分岐が入る
            if (mainActivityViewModel.isLogin()) {
                return 4
            } else {
                return 4
            }
        }
    }
}
