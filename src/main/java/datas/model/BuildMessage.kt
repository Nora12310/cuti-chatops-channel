package datas.model

import ext.formatTo
import ext.toDate

class BuildMessage(
        private val logs: String,
        private val downloadUrl: String,
        private val buildDate: String,
        private val tags: String
) {

    override fun toString(): String {
        return "New version is available for download: \n" +
                downloadUrl +
                "\n\n`Build Date:` " + buildDate.toDate().formatTo("dd-MMM-yyyy") +
                "\n`Platform:` Android" +
                "\n`Branch:` $tags" +
                "\n\n`Changes:`\n" + "+ $logs" +
                "\n\nNote: Please delete local data before installing the new version. Developers can change fields during development making them incompatible with the old version."
    }
}

data class PullRequestMessage(
        private val username: String,
        private val url: String,
        private val branch: String,
        private val targetBranch: String,
        private val message: String,
        private val mergeStatus: String,
        private val mergeError: String?,
        private val state: String
) {

    override fun toString(): String {
        if (state == "merged") {
            return ""
        }
        var message = "@$username ${if (state == "opened") "make" else "edit"} a pull request to merge `$branch` into `$targetBranch` with changes:" +
                "\n + $message" +
                "\n `Merge status:` $mergeStatus" +
                "\n `State:` $state"

        if (mergeError != null) {
            message += "\n `Merge Error:` $mergeError"
        }

        message += "\n\nMerge request url: $url"
        return message
    }
}