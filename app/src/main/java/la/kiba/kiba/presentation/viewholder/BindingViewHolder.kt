package la.kiba.kiba.presentation.viewholder

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by sasaki_nobuya on 2017/04/22.
 */
class BindingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var binding: ViewDataBinding = DataBindingUtil.bind(itemView)

}
