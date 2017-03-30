package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by sheenly on 16/3/9.
 */
public class ReflectUtil {

    public static void invoke(Object aObject, String aMethod) {
        try {
            Class<?> lClass = aObject.getClass();
            Method lMethod = lClass.getDeclaredMethod(aMethod);
            lMethod.invoke(aObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getInstance(String aClassName) {
        try {
            Class<?> lClass = Class.forName(aClassName);
            return lClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Constructor<?> getConstructor(String aClassName, Class<?>... aParameterTypes) {
        try {
            Class<?> lClass = Class.forName(aClassName);
            return lClass.getConstructor(aParameterTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object getInstance(Constructor<?> aConstructor, Object... aArgs) {
        try {
            return aConstructor.newInstance(aArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getInstance(String aClassName, Object aObject) {
        if (aObject == null) {
            return getInstance(aClassName);
        }
        Constructor<?> lConstructor = getConstructor(aClassName, aObject.getClass());
        return getInstance(lConstructor, aObject);
    }

    public static Object getInstance(String aClassName, Class<?> aClass, Object aObject) {
        if (aClass == null) {
            return getInstance(aClassName);
        }
        Constructor<?> lConstructor = getConstructor(aClassName, aClass);
        return getInstance(lConstructor, aObject);
    }
}
