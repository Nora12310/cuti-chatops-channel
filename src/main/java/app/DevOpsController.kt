package app

import datas.entity.ProjectEntity
import datas.model.Project
import datas.model.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import repository.LineRepository

@RestController
@RequestMapping("/api/v1")
class DevOpsController(private val repository: LineRepository) {

    @GetMapping("/rooms")
    fun getRooms(): ResponseEntity<*> {
        return Response.ok(repository.getRooms())
    }

    @GetMapping("/projects")
    fun getProjects(): ResponseEntity<*> {
        return Response.ok(repository.getProjects())
    }

    @GetMapping("check_line_group")
    fun checkGroupProfile(@RequestParam("group_id") groupId: String): ResponseEntity<*> {
        return try {
            val profile = runBlocking { withContext(Dispatchers.IO) { repository.getGroupProfile(groupId) } }
            Response.ok(profile)
        } catch (ex: Exception) {
            Response.error<Any>(HttpStatus.BAD_REQUEST, ex)
        }
    }

    @RequestMapping(
            value = ["/join"],
            method = [RequestMethod.POST]
    )
    fun addLineGroup(@RequestBody payloads: Map<String, String>): ResponseEntity<*> {
        return try {
            val groupId = payloads.getOrDefault("group_id", "")
            runBlocking { withContext(Dispatchers.IO) { repository.join(groupId) } }
            Response.ok<Any>()
        } catch (ex: Exception) {
            Response.error<Any>(HttpStatus.BAD_REQUEST, ex)
        }
    }

    @RequestMapping(
            value = ["/leave"],
            method = [RequestMethod.POST]
    )
    fun removeLineGroup(@RequestBody payloads: Map<String, String>): ResponseEntity<*> {
        return try {
            val groupId = payloads.getOrDefault("group_id", "")
            runBlocking { withContext(Dispatchers.IO) { repository.leave(groupId) } }
            Response.ok<Any>()
        } catch (ex: Exception) {
            Response.error<Any>(HttpStatus.BAD_REQUEST, ex)
        }
    }

    @RequestMapping(
            value = ["/register"],
            method = [RequestMethod.POST]
    )
    fun register(@RequestBody body: Project): ResponseEntity<*> {
        return try {
            val request = ProjectEntity.convert(body)
            runBlocking { withContext(Dispatchers.IO) { repository.register(request) } }
            Response.ok<Any>()
        } catch (ex: Exception) {
            Response.error<Any>(HttpStatus.BAD_REQUEST, ex)
        }
    }

    @PostMapping("/unregister")
    fun unregister(@RequestBody payloads: Map<String, String>): ResponseEntity<*> {
        return try {
            val url = payloads.getOrDefault("git_url", "")
            val prj = runBlocking { withContext(Dispatchers.IO) { repository.unregister(url) } }
            Response.ok(prj)

        } catch (ex: Exception) {
            Response.error<Any>(HttpStatus.BAD_REQUEST, ex)
        }
    }
}