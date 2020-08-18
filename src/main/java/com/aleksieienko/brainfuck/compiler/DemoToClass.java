package com.aleksieienko.brainfuck.compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class DemoToClass {

    public static void main(String[] args) {
        try(Reader in = new BufferedReader(new InputStreamReader(System.in))){
            CompilerToClass compilerToClass = new CompilerToClass();
            if(compilerToClass.compile(in)) {

                JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
                javaCompiler.run(null, null, null, compilerToClass.getFile().getCanonicalPath());

                URLClassLoader classLoader = new URLClassLoader(new URL[]{new File("src/main/java/").toURI().toURL()});
                Class cls = Class.forName("com.aleksieienko.brainfuck.compilerToClass.result." + compilerToClass.getResultClassName(), true, classLoader);
                Method method = cls.getMethod("main", String[].class);
                method.invoke(null, new Object[]{new String[]{}});
                File file = new File("src/main/java/com/aleksieienko/brainfuck/compilerToClass/result/" + compilerToClass.getResultClassName() + ".class");
                file.delete();
            } else {
                System.err.println("Wrong input line or problem to create compiled file!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
