package view;

import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FileChooserBuilder API creates a prompt when the user wants to choose a file
 * from the user's file system
 * 
 * @author Hunter
 *
 */
public class SLogoFileChooserBuilder extends SLogoBuilder {
    private static final int XPROMPTSIZE = 500;
    private static final int YPROMPTSIZE = 250;
    private static final int PADDING = 30;
    private static final int LABEL_FONTSIZE = 36;
    private static final int TEXT_FONTSIZE = 20;
    private static final String FONT = "Georgia";

    private String mySelectedFile;

    public SLogoFileChooserBuilder () {
        super();
    }

    /**
     * A method called by client to create a prompt screen
     * 
     */
    public void promptUser () {
        Stage prompt = new Stage();
        prompt.setTitle(getResourceLoader().getString("FileChooserTitle"));
        Label label = new Label(getResourceLoader().getString("Select"));
        label.setFont(Font.font(FONT, FontWeight.BOLD, LABEL_FONTSIZE));
        HBox labelHb = new HBox();
        labelHb.setAlignment(Pos.CENTER);
        labelHb.getChildren().add(label);
        Text myActionStatus = new Text();
        myActionStatus.setFont(Font.font(FONT, FontWeight.NORMAL, TEXT_FONTSIZE));
        Button btn1 = new Button(getResourceLoader().getString("FileChoose"));
        btn1.setOnAction(e -> showSingleFileChooser(prompt, myActionStatus));
        HBox buttonHb1 = new HBox(PADDING);
        buttonHb1.setAlignment(Pos.CENTER);
        buttonHb1.getChildren().addAll(btn1);
        VBox vbox = new VBox(PADDING);
        vbox.setPadding(new Insets(PADDING));
        vbox.getChildren().addAll(labelHb, buttonHb1, myActionStatus);
        Scene promptScene = new Scene(vbox, XPROMPTSIZE, YPROMPTSIZE);
        prompt.setScene(promptScene);
        prompt.showAndWait();
    }

    /**
     * Private file chooser method used for prompt screen
     * 
     * @param prompt
     * @param myActionStatus
     */
    private void showSingleFileChooser (Stage prompt, Text myActionStatus) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Logo Files", "*.logo"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            myActionStatus.setText(getResourceLoader().getString("Selected") 
                                   + selectedFile.getName());
            setSelectedFile(getRelativePath(selectedFile.getPath()));
            prompt.close();
        }
        else {
            myActionStatus.setText(getResourceLoader().getString("Cancelled"));
        }
    }

    /**
     * Provides a workable path for LogoFileLoader
     * 
     * @param path
     * @return
     */
    private String getRelativePath (String path) {
        return path.substring(path.indexOf("examples"));
    }
    
    /**
     * @return the selectedFile
     */
    public String getSelectedFile () {
        return mySelectedFile;
    }

    /**
     * @param selectedFile the selectedFile to set
     */
    public void setSelectedFile (String selectedFile) {
        this.mySelectedFile = selectedFile;
    }
}
