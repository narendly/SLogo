package exception;

import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import model.ResourceLoader;

/**
 * SLogo's Exception class that extends Exception abstract class in the Java
 * library
 * 
 * @author Hunter Lee
 *
 */
public class SLogoException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ResourceLoader myErrorLoader;
    private Optional<String> unresolvedException;

    /**
     * SLogoException inherits from its superclass Exception
     * 
     * @param arg0
     * Alerts user to error with pop-up window
     */
    public SLogoException(String arg0) {
        super(arg0);
        myErrorLoader = new ResourceLoader("error.properties");
        showErrorDialog(arg0);
    }

    /**
     * @param instruction, numParams
     * Resource file 
     */
    public SLogoException(String instruction, int numParams){	
        super(instruction);
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle(getErrorLoader().getString("ErrorTitle"));
        dialog.setHeaderText(instruction);
        dialog.setContentText(getErrorLoader().getString("Content"));
        unresolvedException = dialog.showAndWait();
    }

    public Optional<String> getNewInput(){
        return unresolvedException;
    }

    /**
     * Shows the user an error message
     * 
     * @param message
     */
    public void showErrorDialog (String message) {
        Dialog<Object> alert = new Dialog<>();
        alert.setTitle(getErrorLoader().getString("AlertTitle"));
        alert.setHeaderText("ERROR: " + message);
        ButtonType buttonTypeOk = new ButtonType(getErrorLoader().getString("OkayButton"));
        alert.getDialogPane().getButtonTypes().add(buttonTypeOk);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("myDialog.css")
                                                            .toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    /**
     * @return the myErrorLoader
     */
    public ResourceLoader getErrorLoader () {
        return myErrorLoader;
    }
}
