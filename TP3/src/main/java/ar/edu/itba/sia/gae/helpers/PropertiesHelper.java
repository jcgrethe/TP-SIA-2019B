package ar.edu.itba.sia.gae.helpers;

import java.io.*;
import java.util.Properties;

public class PropertiesHelper {

    public static Configuration initConfiguration(String propFileName){
        try ( InputStream input = new FileInputStream(propFileName);) {
            Properties properties = new Properties();
            properties.load(input);
            return new Configuration(properties);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }
}
