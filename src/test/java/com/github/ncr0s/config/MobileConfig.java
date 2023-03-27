package com.github.ncr0s.config;

import org.aeonbits.owner.Config;

//@Config.Sources({"classpath:${env}.properties"})
@Config.Sources({"classpath:${deviceHost}.properties"})

public interface MobileConfig extends Config {

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
    @Key("platformVersion")
    String getPlatformVersion();
    @Key("platformName")
    String getPlatformName();
    @Key("deviceName")
    String getDeviceName();
    @Key("appPath")
    String getAppPath();
    @Key("appPackage")
    String getAppPackage();
    @Key("appActivity")
    String getAppActivity();
    @Key("deviceUrl")
    String getDeviceUrl();
}

