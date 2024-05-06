package it.beta80group.stud.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationPropertyLoader {

    private Properties props;
    private static ApplicationPropertyLoader INSTANCE = null;

    protected ApplicationPropertyLoader(){
        try(InputStream is = this.getClass().getClassLoader().getResourceAsStream("/application.properties")){
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ApplicationPropertyLoader getInstance() {
        if(INSTANCE == null){
            INSTANCE = new ApplicationPropertyLoader();
        }
        return INSTANCE;
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }
}
