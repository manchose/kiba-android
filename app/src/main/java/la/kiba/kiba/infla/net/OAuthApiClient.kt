package la.kiba.kiba.infla.net

import io.reactivex.Observable
import la.kiba.kiba.infla.entity.OAuthToken
import la.kiba.kiba.infla.net.helper.MastodonApiVersion
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by sasaki_nobuya on 2017/05/06.
 */
interface OAuthApiClient {
    @FormUrlEncoded
    @POST("/api/${MastodonApiVersion.VERSION}/apps")
    fun register(@Field("client_name") clientName: String = "kiba",
                 @Field("redirect_uris") redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
                 @Field("scopes") scopes: String = "read write follow"): Observable<OAuthToken>
}
