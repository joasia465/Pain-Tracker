# Pain Tracker 🩺

Aplikacja desktopowa do monitorowania i lokalizowania bólu, stworzona w JavaFX. 

### ✨ Funkcjonalności
- **Interaktywna mapa ciała:** Wybieranie konkretnych obszarów (głowa, tułów, kończyny) dzięki technologii masek bitowych.
- **Dziennik bólu:** Zapisywanie daty, lokalizacji, typu bólu oraz intensywności.
- **Baza danych:** Trwały zapis danych w formacie tekstowym (CSV-like).
- **Przenośność:** Dołączone biblioteki natywne pozwalają na uruchomienie aplikacji bez instalacji JavaFX w systemie.

### 🚀 Jak uruchomić?
1. Sklonuj repozytorium.
2. W IntelliJ dodaj pliki `.jar` z folderu `/lib` do bibliotek projektu.
3. Dodaj następujące **VM Options** w konfiguracji uruchamiania:
   `--module-path lib --add-modules javafx.controls,javafx.fxml,javafx.graphics -Dprism.order=sw`
4. Uruchom klasę **`Launcher`**.

*Uwaga: Aplikacja została zoptymalizowana pod kątem stabilności (JavaFX 21 LTS).*

*Projekt stworzony na potrzeby zaliczenia przedmiotu na studiach w roku akademickim 2024/2025.*
