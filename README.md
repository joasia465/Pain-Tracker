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


## Symulacja korzystania (krok po kroku)

Jeśli nie możesz uruchomić aplikacji lokalnie, oto jak wygląda standardowa interakcja użytkownika:

1. Menu Główne: Po uruchomieniu wita Cię minimalistyczne menu z trzema opcjami: Dodaj ból, Lista bóli oraz Statystyki (WIP).


2. Lokalizacja na mapie ciała: Po kliknięciu "Dodaj ból" otwiera się okno z interaktywną mapą sylwetki.
- Użytkownik klika myszką bezpośrednio na część ciała, która go boli (rozróżniana są głowa, tłów, ręce prawa i lewa, logi prawa i lewa).
- Program, dzięki zastosowaniu masek bitowych, natychmiast rozpoznaje kliknięty obszar (np. "Prawa noga" lub "Głowa") i wyświetla tę informację.

3. Szczegóły wpisu: Po wybraniu miejsca pojawia się formularz, w którym użytkownik:
- Określa typ bólu (np. pulsujący, kłujący, tępy).
- Wybiera intensywność w skali 0-10.
- Może dodać krótki opis (np. "ból po treningu" lub doprecyzowana część ciała).

4. Zapis i Podgląd: Po kliknięciu "Zapisz", dane lądują w pliku tekstowym. Użytkownik może wtedy wejść w "Listę bóli", gdzie w przejrzystym oknie TextArea widzi całą historię swoich wpisów wraz z datami i godzinami.

5. Przycisk Statystyki na razie nie ma wykorzystania, jest to jedna z możliwych ścieżek dalszego rozwoju aplikacji.




Indywidualna praca Joanny Czeluśniak
