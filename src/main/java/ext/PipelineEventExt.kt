package ext

import datas.model.gitlab.pineline.PipelineEvent
import java.net.URI
import java.net.URL

/**
 * referred: https://gitlab.com/gitlab-org/gitlab/-/jobs/artifacts/master/download?job=coverage
 * https://gitlab.legato.co/Legato.nathan.pham/android-cicd-example/-/jobs/1955/artifacts/download
 */
fun PipelineEvent.buildDownloadLink(): String {
    val url = URL(project.webUrl ?: "")
    val hostname = url.host
    val protocol = url.protocol

    val build = this.findBuildByStage("build")
    val username = build?.user?.username
    val runnerId = build?.id
    val projectName = project.name
    return "$protocol://$hostname/$username/$projectName/-/jobs/$runnerId/artifacts/download"
}