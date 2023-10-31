package com.ctu.se.oda.model11;

import com.ctu.se.oda.model11.models.AppInfo;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AppInformationService {

    private static AppInfo appInfo;

    public static AppInfo getAppInfo() {
        if (Objects.isNull(appInfo)) {
            appInfo = new AppInfo("Production Process Management Service", "0.1.0");
        }
        return appInfo;
    }
}
