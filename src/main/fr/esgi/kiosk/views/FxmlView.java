package main.fr.esgi.kiosk.views;

import java.util.ResourceBundle;

public enum FxmlView {

    SPLASH_SCREEN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("home.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/SplashScreen.fxml";
        }
    },HOME {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("home.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Home.fxml";
        }
    }, LOCATION {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("location.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Location.fxml";
        }
    },COMMAND_HOME {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("commandHome.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/CommandHome.fxml";
        }
    },
    ADMIN_LOGIN{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("adminLogin.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/AdminLogin.fxml";
        }
    },
    PRODUCT_ELEMENT{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("productElement.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/ProductElement.fxml";
        }

    };

    public abstract String getTitle();
    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
