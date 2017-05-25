package la.kiba.kiba.infla.repository

import com.github.gfx.android.orma.DatabaseHandle
import io.reactivex.Single
import la.kiba.kiba.infla.entity.OrmaDatabase
import javax.inject.Inject

/**
 * Created by sasaki_nobuya on 2017/05/06.
 */
class AccountRepository @Inject constructor(databaseHandle: DatabaseHandle) {
    var ormaDatabase: OrmaDatabase = databaseHandle as OrmaDatabase

    fun isLogin(): Single<Boolean> {
        return Single.create<Boolean> { ormaDatabase.selectFromAccount().count() > 0 }
    }
}
