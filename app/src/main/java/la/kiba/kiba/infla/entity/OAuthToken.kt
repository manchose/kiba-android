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
    @PrimaryKey(auto = true)
    val id: Long

    @Column
    @Json(name = "access_token")
    val accessToken: String

    @Column
    @Json(name = "scope")
    val scope: String

    @Column(indexed = true)
    var email: String

    @Column
    var password: String

    @Column(indexed = true)
    var instance: String

    constructor(@Setter id: Long,
                @Setter accessToken: String,
                @Setter scope: String,
                @Setter email: String,
                @Setter password: String,
                @Setter instance: String) {
        this.id = id
        this.accessToken = accessToken
        this.scope = scope
        this.email = email
        this.password = password
        this.instance = instance
    }
}
