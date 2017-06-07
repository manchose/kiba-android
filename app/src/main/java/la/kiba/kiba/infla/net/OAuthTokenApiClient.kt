package la.kiba.kiba.infla.net

import io.reactivex.Observable
import la.kiba.kiba.infla.entity.OAuthToken
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by sasaki_nobuya on 2017/05/30.
 */
interface OAuthTokenApiClient {
    @FormUrlEncoded
    @POST("/oauth/token")
    fun issue(@Field("client_id") clientId: String,
              @Field("client_secret") clientSecret: String,
              @Field("username") userName: String,
              @Field("password") password: String,
              @Field("scope") scope: String = "read write follow",
              @Field("redirect_uri") redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
              @Field("grant_type") grantType: String = "password"): Observable<OAuthToken>
}
