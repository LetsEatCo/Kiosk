package main.fr.esgi.kiosk.plugin;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.fr.esgi.kiosk.controllers.CommandController;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader {

    private File jarFile;

    public static void importJar(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Your Jar File...");


        File file =  fileChooser.showOpenDialog(stage);

    }


    public void processSkinChange(CommandController commandController, String jarPath, String cssTheme) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        File dir = new File(jarPath);
        File css = new File(cssTheme);

        System.out.println(css.exists());

        URLClassLoader child = new URLClassLoader (new URL[]{dir.toURI().toURL()}, this.getClass().getClassLoader());
        Class pluginClass = Class.forName ("fr.esgi.plugin.PluginImpl", true, child);
        Constructor constructor = pluginClass.getConstructor(CommandController.class);
        Method method = pluginClass.getMethod("process", String.class);
        Object instance = constructor.newInstance(commandController);
        method.invoke(instance, cssTheme);


    }

}
