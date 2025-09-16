package com.github.hfantin.veiculos.infrastructure.web.mapper;

import com.github.hfantin.veiculos.domain.entity.AppInfo;
import com.github.hfantin.veiculos.infrastructure.web.dto.AppInfoResponse;
import org.springframework.stereotype.Component;

@Component
public class AppInfoMapper {

    public AppInfoResponse toResponse(AppInfo appInfo) {
        return new AppInfoResponse(
                appInfo.getName(),
                appInfo.getVersion(),
                appInfo.getDescription()
        );
    }
}
