package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.service.InfoService;
import com.github.hfantin.veiculos.infrastructure.web.dto.AppInfoResponse;
import com.github.hfantin.veiculos.infrastructure.web.mapper.AppInfoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
@Tag(name = "Info", description = "API de informações do sistema")
@CrossOrigin(origins = "*")
public class InfoController {

    private final InfoService infoService;
    private final AppInfoMapper appInfoMapper;

    public InfoController(InfoService infoService, AppInfoMapper appInfoMapper) {
        this.infoService = infoService;
        this.appInfoMapper = appInfoMapper;
    }

    @GetMapping
    @Operation(summary = "Obtém informações do sistema", description = "Retorna as nformações do sistema")
    public ResponseEntity<AppInfoResponse> getApplicationInfo() {
        var appInfo = infoService.getApplicationInfo();
        var response = appInfoMapper.toResponse(appInfo);
        return ResponseEntity.ok(response);
    }

 }
