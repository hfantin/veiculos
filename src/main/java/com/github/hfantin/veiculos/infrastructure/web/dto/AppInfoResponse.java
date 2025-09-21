package com.github.hfantin.veiculos.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AppInfoResponse(

        @JsonProperty("app_name")
        String name,

        @JsonProperty("version")
        String version,

        @JsonProperty("description")
        String description) {
}