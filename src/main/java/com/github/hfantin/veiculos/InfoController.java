package com.github.hfantin.veiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.hfantin.veiculos.config.AppConfig;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final AppConfig appConfig;

    @Autowired
    public InfoController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping
    public AppInfo getAppInfo() {
        return new AppInfo(
                appConfig.getName(),
                appConfig.getVersion(),
                appConfig.getDescription()
        );
    }
}
