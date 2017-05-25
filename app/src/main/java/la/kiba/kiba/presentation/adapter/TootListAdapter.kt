package la.kiba.kiba.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import la.kiba.kiba.BR
import la.kiba.kiba.R
import la.kiba.kiba.presentation.viewholder.BindingViewHolder
import la.kiba.kiba.presentation.viewmodel.TootItemsViewModel

/**
 * Created by sasaki_nobuya on 2017/04/22.
 */
class TootListAdapter(val tootItemsViewModel: TootItemsViewModel) : RecyclerView.Adapter<BindingViewHolder>() {
    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.binding.setVariable(BR.viewModel, tootItemsViewModel.getViewModel(position))
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.toot_item, parent, false)
        return BindingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tootItemsViewModel.viewModelSize()
    }
}
