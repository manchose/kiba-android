package la.kiba.kiba.infla.entity

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Setter
import com.github.gfx.android.orma.annotation.Table
import com.squareup.moshi.Json

/**
 * Created by sasaki_nobuya on 2017/05/06.
 */
@Table
class OAuthToken {
    @Column
    @PrimaryKey(auto = false)
    val id: Long

    @Column
    @Json(name = "client_id")
    val clientId: String

    @Column
    @Json(name = "client_secret")
    val clientSecret: String

    @Column
    @Json(name = "redirect_uri")
    val redirectUri: String

    @Column(indexed = true, unique = true)
    val instance: String

    constructor(@Setter id: Long,
                @Setter clientId: String,
                @Setter clientSecret: String,
                @Setter redirectUri: String,
                @Setter instance: String) {
        this.id = id
        this.clientId = clientId
        this.clientSecret = clientSecret
        this.redirectUri = redirectUri
        this.instance = instance
    }
}
