package com.hrmelo.service.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.info.GitProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.ofInstant;
import static org.springframework.http.ResponseEntity.ok;

@RestController
class UtilitiesController {

    private final HealthEndpoint healthEndpoint;

    private final GitProperties gitProperties;

    public UtilitiesController(final HealthEndpoint healthEndpoint,
                               final GitProperties gitProperties) {
        this.healthEndpoint = healthEndpoint;
        this.gitProperties = gitProperties;
    }

    @GetMapping(value = "/ping", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getPing() {
        return ok("pong");
    }

    @GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HealthComponent> getStatus() {
        return ok(healthEndpoint.health());
    }

    @GetMapping(value = "/version", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VersionResponse> getVersion() {
        final String commitId = gitProperties.getCommitId();
        final ZonedDateTime commitTime = ofInstant(gitProperties.getCommitTime(), UTC);
        final String commitMessage = gitProperties.get("commit.message.full");

        return ok(new VersionResponse(commitId, commitMessage, commitTime));
    }

    static class VersionResponse {
        private final String commitId;
        private final String commitMessage;
        private final ZonedDateTime commitTime;

        VersionResponse(final String commitId,
                        final String commitMessage,
                        final ZonedDateTime commitTime) {
            this.commitId = commitId;
            this.commitMessage = commitMessage;
            this.commitTime = commitTime;
        }

        @JsonProperty
        String getCommitId() {
            return commitId;
        }

        @JsonProperty
        String getCommitMessage() {
            return commitMessage;
        }

        @JsonProperty
        ZonedDateTime getCommitTime() {
            return commitTime;
        }
    }
}
