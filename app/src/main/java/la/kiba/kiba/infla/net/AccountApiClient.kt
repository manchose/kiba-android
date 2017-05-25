package la.kiba.kiba.infla.net

import io.reactivex.Observable
import la.kiba.kiba.infla.entity.Account
import la.kiba.kiba.infla.net.helper.MastodonApiVersion
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by sasaki_nobuya on 2017/05/06.
 */
interface AccountApiClient {
    @GET("/api/${MastodonApiVersion.VERSION}/accounts/{id}")
    fun getAccount(@Path("id") id: Long): Observable<Account>
}
