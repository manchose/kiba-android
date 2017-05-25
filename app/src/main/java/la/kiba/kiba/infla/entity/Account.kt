package la.kiba.kiba.infla.entity

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Setter
import com.github.gfx.android.orma.annotation.Table
import com.squareup.moshi.Json
import java.sql.Time

/**
 * Created by sasaki_nobuya on 2017/05/05.
 */
@Table
class Account {

    @PrimaryKey(autoincrement = true)
    val id: Long

    @Column
    val username: String

    @Column
    val acct: String

    @Json(name = "display_name")
    @Column
    val displayName: String

    @Column
    val locked: Boolean

    @Json(name = "created_at")
    @Column
    val createdAt: Time

    @Json(name = "followers_count")
    @Column
    val followersCount: Long

    @Json(name = "following_count")
    @Column
    val followingCount: Long

    @Json(name = "statuses_count")
    @Column
    val statusesCount: Long

    @Column
    val note: String

    @Column
    val url: String

    @Column
    val avatar: String

    @Json(name = "avatar_static")
    @Column
    val avatarStatic: String

    @Column
    val header: String

    @Json(name = "header_static")
    @Column
    val headerStatic: String

    constructor(@Setter id: Long,
                @Setter username: String,
                @Setter acct: String,
                @Setter displayName: String,
                @Setter locked: Boolean,
                @Setter createdAt: Time,
                @Setter followersCount: Long,
                @Setter followingCount: Long,
                @Setter statusesCount: Long,
                @Setter note: String,
                @Setter url: String,
                @Setter avatar: String,
                @Setter avatarStatic: String,
                @Setter header: String,
                @Setter headerStatic: String) {
        this.id = id
        this.username = username
        this.acct = acct
        this.displayName = displayName
        this.locked = locked
        this.createdAt = createdAt
        this.followersCount = followersCount
        this.followingCount = followingCount
        this.statusesCount = statusesCount
        this.note = note
        this.url = url
        this.avatar = avatar
        this.avatarStatic = avatarStatic
        this.header = header
        this.headerStatic = headerStatic
    }
}
