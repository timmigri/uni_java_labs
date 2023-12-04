package labs.grishchenko;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Injector {
    private String resourcePath;

    public Injector(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public <T> T inject(T object) throws InjectionException {
        Class<?> objectClass = object.getClass();
        try {
            Properties properties = loadProperties();
            for (Field field : objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(AutoInjectable.class)) {
                    String interfaceName = field.getType().getName();
                    String implementationName = properties.getProperty(interfaceName);
                    if (implementationName == null) { continue; }
                    field.set(object, Class.forName(implementationName).getDeclaredConstructor().newInstance());
                }
            }
            return object;
        } catch (
            ClassNotFoundException | 
            IllegalAccessException |
            InstantiationException | 
            IOException | 
            InvocationTargetException | 
            NoSuchMethodException e
        ) {
            throw new InjectionException(e.getMessage());
        }
    }

    private Properties loadProperties() throws InjectionException, IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new InjectionException("Input stream is null");
        }
        properties.load(inputStream);
        return properties;
    }
}
