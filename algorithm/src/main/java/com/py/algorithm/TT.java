package com.py.algorithm;


import java.io.InputStream;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2021/2/3
 */
public class TT {


    public static void main(String[] args) throws Exception {
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (Exception e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = loader.loadClass("com.py.algorithm.TT").newInstance();
        ClassLoader appLoad = TT.class.getClassLoader();
        Object appObj = appLoad.loadClass("com.py.algorithm.TT").newInstance();

        System.out.println(obj.getClass());
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(obj instanceof TT);

        System.out.println(appObj.getClass());
        System.out.println(appObj.getClass().getClassLoader());
        System.out.println(appObj instanceof TT);
    }
}
