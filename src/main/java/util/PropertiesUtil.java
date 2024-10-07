package util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    // Загружаем файл до обращения к классу
    static {
        loadProperties();
    }

    private PropertiesUtil() {}

    // По обращению к ключю возвращаем значение
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    // Загружаем файл application.properties из папки resourses
    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
