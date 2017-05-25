package la.kiba.kiba.presentation.viewmodel

import la.kiba.kiba.infla.entity.Toot
import javax.inject.Inject

/**
 * Created by sasaki_nobuya on 2017/04/23.
 */
class TimelineFragmentViewModel @Inject constructor(val toots: List<Toot>)
