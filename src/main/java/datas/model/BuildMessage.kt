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
                "\n\n*Build Date:* " + buildDate.toDate().formatTo("dd-MMM-yyyy") +
                "\n*Platform:* Android" +
                "\n*Version:* $tags" +
                "\n\n*Changes:*\n" + logs +
                "\n\n*Note:* Please delete local data before installing the new version. Developers can change fields during development making them incompatible with the old version."
    }
}