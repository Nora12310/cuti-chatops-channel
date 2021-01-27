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
                "\n\nBuild Date: " + buildDate.toDate().formatTo("dd-MMM-yyyy") +
                "\nPlatform: Android" +
                "\nBranch: $tags" +
                "\n\nChanges:\n" + logs +
                "\n\nNote: Please delete local data before installing the new version. Developers can change fields during development making them incompatible with the old version."
    }
}

data class PullRequestMessage(
        private val username: String,
        private val url: String,
        private val branch: String,
        private val targetBranch: String,
        private val message: String
) {

    override fun toString(): String {
        return "@$username make a pull request to merge `$branch` into `$targetBranch` with changes:" +
                "\n +$message" +
                "\n\nMerge request url: $url"
    }
}