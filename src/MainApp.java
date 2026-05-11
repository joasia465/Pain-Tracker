import javafx.application.Application;     // 🔧 Klasa bazowa JavaFX – pozwala stworzyć aplikację GUI
import javafx.stage.Stage;                 // 🪟 Reprezentuje główne okno (ramkę) aplikacji
import javafx.scene.Scene;                 // 🎭 Scena, czyli obszar widoczny w oknie
import javafx.scene.control.Button;        // 🔘 Klasa przycisku (element GUI)
import javafx.scene.layout.VBox;           // 📦 Kontener, który układa elementy pionowo
import javafx.geometry.Pos;                // 🎯 Umożliwia ustawienie wyrównania (np. do środka)


public class MainApp extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        Button addPainButton = new Button("➕ Dodaj ból");
        Button painListButton = new Button("📋 Lista bóli");
        Button statsButton = new Button("📊 Statystyki");

        // akcja po kliknieciu w "Dodaj ból"
        addPainButton.setOnAction(e -> {
            AddPainWindow window = new AddPainWindow();
            window.show();
        });

        // akcja po kliknieciu w "Lista bóli"
        painListButton.setOnAction(e -> {
            PainListWindow window = new PainListWindow();
            window.show();
        });

        VBox layout = new VBox(10, addPainButton, painListButton, statsButton);
        layout.setStyle("-fx-padding: 20px;");
        layout.setAlignment(Pos.CENTER); //środkowanie

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("PainTracker – menu główne");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        // Wymuszamy renderer programowy, aby ominąć błędy karty graficznej
        System.setProperty("prism.order", "sw");
        // Wskazujemy folder lib jako miejsce szukania plików natywnych (.dll)
        System.setProperty("java.library.path", "lib");

        launch(args);
    }
}
