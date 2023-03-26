package com.github.ncr0s.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${env}.properties"})

public interface BrowserstackConfig extends org.aeonbits.owner.Config {

    @Key("userName")
    String getUserName();

    @Key("password")
    String getPassword();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("appUrl")
    String getAppUrl();

    @Key("device")
    String getDevice();

    @Key("os_version")
    String getOsVersion();

    @Key("project")
    String getProjectName();

    @Key("build")
    String getBuildName();

    @Key("name")
    String getTestName();
}

