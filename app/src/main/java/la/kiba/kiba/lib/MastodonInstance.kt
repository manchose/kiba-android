package la.kiba.kiba.lib

/**
 * Created by sasaki_nobuya on 2017/05/14.
 */
class MastodonInstance(instanceName: String) {
    private var instance = instanceName

    fun validatedInstance(): String {
        trimScheme()
        trimSlash()
        return instance
    }

    private fun trimScheme() {
        val regex = Regex("://")
        val splitInstance = regex.split(instance, 2)
        instance = splitInstance.last()
    }

    private fun trimSlash() {
        val regex = Regex("/")
        instance = regex.replace(instance, "")
    }
}
