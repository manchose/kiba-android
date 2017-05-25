package la.kiba.kiba.presentation.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import la.kiba.kiba.R
import la.kiba.kiba.databinding.TimelineFragmentBinding
import la.kiba.kiba.infla.entity.Toot
import la.kiba.kiba.presentation.adapter.TootListAdapter
import la.kiba.kiba.presentation.di.module.FragmentModule
import la.kiba.kiba.presentation.viewmodel.TootItemsViewModel
import javax.inject.Inject

/**
 * Created by sasaki_nobuya on 2017/04/22.
 */

class TimelineFragment : BaseFragment() {
    @Inject lateinit var tootItemsViewModel: TootItemsViewModel
    lateinit var binding: TimelineFragmentBinding

    companion object {
        fun newInstance(): TimelineFragment {
            return TimelineFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.timeline_fragment, container, false)
        binding = TimelineFragmentBinding.bind(view)
        getInjector().fragmentComponent(FragmentModule(this)).inject(this)
        return view
    }

    override fun onResume() {
        super.onResume()
        // TODO: modelに本当はある
        var i = 0
        val list: MutableList<Toot> = mutableListOf()
        while (i < 20) {
            list.add(Toot(i, "テストの文言 $i"))
            i++
        }
        tootItemsViewModel.setToots(list)
        render()
    }

    fun render() {
        binding.timelineRecyclerView.layoutManager = LinearLayoutManager(activity.applicationContext)
        binding.timelineRecyclerView.adapter = TootListAdapter(tootItemsViewModel)
        binding.timelineRecyclerView.adapter.notifyDataSetChanged()
    }
}
