import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class AddPainWindow
{
    List<String> entry = new ArrayList<>();
    private final Pane painMap = new Pane();
    private ImageView currentMask = null;
    private final Label dynamicBodyPartLabel = new Label("-");
    private ChoiceBox<String> painTypeChoice;
    private Slider painScale;
    private TextArea commentBox;

    public void show()
    {
        Stage window = createWindow();
        ImageView imageView = createImageView();
        VBox formLayout = createFormLayout();
        Button saveButton = createSaveButton(window);
        Button exitButton = createExitButton(window);

        setupClickDetection(imageView);

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20px;");
        layout.setAlignment(Pos.TOP_CENTER);

        HBox spaceExitButton = new HBox(1);
        spaceExitButton.setAlignment(Pos.CENTER_LEFT);
        spaceExitButton.getChildren().addAll(exitButton);

        Region spacer1 = new Region();
        Region spacer2 = new Region();
        VBox.setVgrow(spacer1, Priority.ALWAYS);
        VBox.setVgrow(spacer2, Priority.ALWAYS);


        layout.getChildren().addAll(
                spaceExitButton,
                painMap,
                spacer1,
                formLayout,
                spacer2,
                saveButton
        );

        Scene scene = new Scene(layout, 350, 650);
        window.setScene(scene);
        window.showAndWait();
    }

    public void CreatePainEntry()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = LocalDateTime.now().format(formatter);
        String bodyPart = dynamicBodyPartLabel.getText();
        String painType = painTypeChoice.getValue();
        String painLevel = (Integer.toString((int) painScale.getValue()));
        String comment = commentBox.getText();


        if (Objects.equals(bodyPart, "-"))
            return;
        if (Objects.equals(painType, "wybierz..."))
            painType = "-";
        if (Objects.equals(painLevel, 0))
            painLevel = "-";
        if  (Objects.equals(comment, ""))
            comment = "-";


        entry.add(0, dateTime);
        entry.add(1, bodyPart);
        entry.add(2, painType);
        entry.add(3, painLevel);
        entry.add(4, comment);

        System.out.println(entry);

    }

    private Stage createWindow() // tworzy okno
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Dodaj ból");
        return window;
    }

    private ImageView createImageView() // ładuje i konfiguruje obrazek
    {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/female_body/female_body.png")));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(250);
        imageView.setPreserveRatio(true);
        imageView.setLayoutY(20);

        painMap.setPrefHeight(400);
        painMap.getChildren().add(imageView);
        imageView.layoutXProperty().bind(painMap.widthProperty().subtract(imageView.fitWidthProperty()).divide(2));

        return imageView;
    }

    private VBox createFormLayout()
    {
        Label bodyPartLabel = new Label("Część ciała:");
        HBox bodyPartRow = new HBox();
        bodyPartRow.setAlignment(Pos.CENTER_LEFT);
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        bodyPartRow.getChildren().addAll(bodyPartLabel, spacer1, dynamicBodyPartLabel);

        Label painTypeLabel = new Label("Rodzaj bólu:       ");
        painTypeChoice = new ChoiceBox<>();
        painTypeChoice.getItems().addAll("wybierz...", "Kłujący", "Tępy", "Piekący", "Pulsujący", "Inny");
        painTypeChoice.setValue("wybierz...");
        HBox painTypeRow = new HBox();
        painTypeRow.setAlignment(Pos.CENTER_LEFT);
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        painTypeRow.getChildren().addAll(painTypeLabel, spacer2, painTypeChoice);

        Label scaleLabel = new Label("Skala bólu:");
        painScale = new Slider(0, 10, 0);
        painScale.setShowTickLabels(true);
        painScale.setShowTickMarks(true);
        painScale.setMajorTickUnit(1);
        painScale.setMinorTickCount(0);
        painScale.setBlockIncrement(1);
        painScale.setSnapToTicks(true);

        commentBox = new TextArea();
        commentBox.setPromptText("Komentarz");
        commentBox.setWrapText(true);
        commentBox.setPrefRowCount(3);

        VBox formLayout = new VBox(10);
        formLayout.setAlignment(Pos.CENTER_LEFT);
        formLayout.getChildren().addAll(
                bodyPartRow,
                painTypeRow,
                scaleLabel,
                painScale,
                commentBox
        );
        return formLayout;
    }

    private void setupClickDetection(ImageView imageView)
    {
        painMap.setOnMouseClicked(e -> handleClick(e, imageView));
    }

    private void handleClick(MouseEvent e, ImageView imageView)
    {
        double[] relative = BodyPartDetector.getRelativeClick(e, imageView);
        double x = relative[0];
        double y = relative[1];

        String czescCiala = BodyPartDetector.getBodyPart(x, y, imageView.getFitWidth(), imageView.getBoundsInParent().getHeight());
        dynamicBodyPartLabel.setText(czescCiala);

        if (!czescCiala.equals("Poza ciałem"))
        {
            showMaskOverlay(czescCiala);
        }
    }

    private void showMaskOverlay(String czescCiala)
    {
        if (currentMask != null) {
            painMap.getChildren().remove(currentMask);
        }

        Image mask = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/female_body/female_" + translatePartToFileName(czescCiala) + ".png")));
        currentMask = new ImageView(mask);
        currentMask.setFitWidth(250);
        currentMask.setPreserveRatio(true);
        currentMask.setLayoutY(20);
        currentMask.setOpacity(1);
        currentMask.layoutXProperty().bind(painMap.widthProperty().subtract(currentMask.fitWidthProperty()).divide(2));

        painMap.getChildren().add(currentMask);
        currentMask.toFront();
    }

    private String translatePartToFileName(String part)
    {
        return switch (part)
        {
            case "Głowa" -> "head";
            case "Tułów" -> "torso";
            case "Lewa ręka" -> "left_arm";
            case "Prawa ręka" -> "right_arm";
            case "Lewa noga" -> "left_leg";
            case "Prawa noga" -> "right_leg";
            default -> "";
        };
    }

    private void saveClicksAndClose(Stage window)
    {
        CreatePainEntry();
        PainListWindow.SaveToList(entry);

        window.close();
    }

    private Button createSaveButton(Stage window) // przycisk zapisu
    {
        Button saveButton = new Button("Zapisz");
        saveButton.setOnAction(e -> saveClicksAndClose(window));
        return saveButton;
    }

    private Button createExitButton(Stage window)
    {
        Button exitButton = new Button("Wróć");
        exitButton.setOnAction(e -> window.close());
        return exitButton;
    }
}
