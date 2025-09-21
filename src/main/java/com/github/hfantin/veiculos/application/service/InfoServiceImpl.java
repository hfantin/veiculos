package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.config.AppConfig;
import com.github.hfantin.veiculos.domain.model.AppInfo;
import com.github.hfantin.veiculos.domain.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    private final AppConfig appConfig;

    @Autowired
    public InfoServiceImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public AppInfo getApplicationInfo() {
        return new AppInfo(
                appConfig.getName(),
                appConfig.getVersion(),
                appConfig.getDescription()
        );
    }
}
