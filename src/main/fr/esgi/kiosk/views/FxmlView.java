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
    CONFIRM_SCREEN{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("confirm.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/ConfirmScreen.fxml";
        }

    },
    ACCOMPANIMENT{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("accompaniment.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/ProductComposition.fxml";
        }

    },
    PAYMENT_SCREEN{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("payment.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/PaymentScreen.fxml";
        }

    },
    PAYMENT_REQUEST_SCREEN{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("request.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/PaymentSplashScreen.fxml";
        }

    };

    public abstract String getTitle();
    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
