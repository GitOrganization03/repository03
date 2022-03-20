package com.abyky.core.controller.github

import com.abyky.github.hooks.handler.GithubWebhookHandler
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/public/github")
class GithubWebhooksController(
  private val githubWebhookHandler: GithubWebhookHandler
) {

  @PostMapping("/events")
  fun webhook(@RequestHeader headers: HttpHeaders, @RequestBody body: ByteArray): HttpEntity<*> {
    val headersMap = headers.map { it.key.uppercase() to it.value.joinToString() }.toMap()
    githubWebhookHandler.handle(body, headersMap)
    return ResponseEntity.ok().build<Any>()
  }
}