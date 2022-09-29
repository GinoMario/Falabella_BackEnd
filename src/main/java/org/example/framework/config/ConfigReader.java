package org.example.framework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    public static void PopulateSettings() throws IOException {
        ConfigReader reader = new ConfigReader();
        reader.ReadProperty();
    }

    private void ReadProperty() throws IOException {

        Properties p = new Properties();
        String propEnv = "Config.properties";

        String localPath = System.getProperty("user.dir") + "/src/main/resources/";
        InputStream inputStream = new FileInputStream(localPath + propEnv);
        p.load(inputStream);

        Settings.PathLogs = p.getProperty("PathLogs");
        Settings.PathConfig = p.getProperty("PathConfig");
        Settings.UrlApiBase = p.getProperty("UrlApiBase");
        Settings.UrlApiPath = p.getProperty("UrlApiPath");
    }

}