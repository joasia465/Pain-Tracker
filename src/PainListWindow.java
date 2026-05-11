import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class PainListWindow
{
    private static final List<List<String>> painList = new ArrayList<>();

    public void show()
    {
        Stage window = createWindow();

        TextArea painListText = new TextArea(loadFromFile());
        painListText.setEditable(false);
        painListText.setWrapText(true);
        painListText.setPrefRowCount(35);

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20px;");

        layout.setAlignment(Pos.TOP_CENTER);
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Button exitButton = createExitButton(window);
        HBox spaceExitButton = new HBox(1);
        spaceExitButton.setAlignment(Pos.CENTER_LEFT);
        spaceExitButton.getChildren().addAll(exitButton);

        layout.getChildren().addAll(
                spaceExitButton,
                spacer,
                painListText
        );

        Scene scene = new Scene(layout, 600, 650);
        window.setScene(scene);
        window.showAndWait();

    }

    private String loadFromFile()
    {
        StringBuilder allPains = new StringBuilder(); // wszystkie linie
        painList.clear(); // czyści listę przed załadowaniem

        try (BufferedReader reader = new BufferedReader(new FileReader("pain_list.txt"))) {
            String line;
            while ((line = reader.readLine()) != null)
            {
                line = line.trim(); // Usuwamy zbędne spacje na początku i końcu
                if (line.isEmpty()) continue; // Pomijamy puste linie

                // Dodajemy linię do StringBuilder (wyświetlanie w TextArea)
                allPains.append(line).append("\n");

                String[] parts = line.split(", ");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "Błąd podczas wczytywania pliku.";
        }
        return allPains.toString();
    }

    public static void SaveToList(List<String> entry)
    {
        painList.add(entry);

        try (FileWriter writer = new FileWriter("pain_list.txt", true))
        {
            writer.write(String.valueOf(entry));
            writer.write(System.lineSeparator());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    private Stage createWindow()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Lista bóli");
        return window;
    }

    private Button createExitButton(Stage window) // przycisk powrotu
    {
        Button exitButton = new Button("Wróć");
        exitButton.setOnAction(e -> window.close());
        return exitButton;
    }

}
