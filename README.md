# Wer Wird Millionär - Team Alphas
Wir haben in unserem Projekt das Spiel "Wer Wird Millionär" nachprogrammiert.
Es ist ein Wissensspiel, wo man unterschiedliche Frage beantworten muss, um zu versuchen die Millionen zu gewinnen.
> momentan befindet sich das Spiel in der Beta-Version.
> Es ist noch kein graphisches Interface vorhanden, das Spiel wird in der Konsole gespielt

## Spiel
### Spiel starten
Das Spiel wird über die Main Funktion in der "Game" Klasse gestartet (Rechtsklick auf main -> Run main)
`public static void main(String[] args)`

### Spiel spielen
> Alle Eingaben für den Spielablauf folgen über die Tastatur. 
Es werden je nach Kategorie unterschiedliche Fragen gestellt.
Sollte eine Frage richtig sein, so steigt man eine Stufe auf.

### Joker
Es gibt drei verschiedene Joker.
- **Fifty-fifty** Joker: zwei der falschen Antworten werden ausgeblendet sodass nur die richtige und **eine** falsche Antwort überbleiben.
- **Help** Joker: Jemand aus unserem Team hilft dir die Frage zu beantworten. 
> (Achtung: Die Antwort, die wir geben muss nicht immer stimmen)
- **Second Chance** Joker: Wenn man eine Frage falsch beantwortet hat, kann man danach noch einmal eine Antwortmöglichkeit abgeben
> (Achtung: Joker muss dafür vorher ausgewählt werden)

### Kategorie
Die Kategorien sind:

50€-> 100€ -> 200€ -> 300€ -> **500€ (Sicherheitsstufe)** -> 1.000€ -> 2.000€ -> 4.000€ -> 8.000€ -> **16.000€ (Sicherheitsstufe)** -> 32.000€ -> 64.000€ -> 125.000€ -> 500.000€ -> 1.000.0000€

### Ende
Man kann das Spiel am Anfang jeder Frage (außer der ersten) verlassen.
> Das Spiel endet automatisch beim Erreichen der Million oder wenn man eine Frage falsch beantwortet

### Gewinn
- Sollte man freiwillig das Spiel verlassen so gewinnt man den Betrag bei dem man als letztes gestanden ist.
- Sollte man eine Frage falsch beantworten fällt man auf die jeweilige Sicherheitsstufe zurück (Wenn man vor der 500€ Frage verliert, so gewinnt man 0€)
- Sollte man es bis zur Million schaffen, gewinnt man die Million

## Code
### Klassen
Wir haben 4 Klassen in unserem Programm
- Player: Regelt alle Eingabe des Spielers sowie die Ausgabe der Geldbeträge
- Question: Zuständig für die Ausgabe der Frage, überprüfen der richtigen Antwort und verstecken von Antwortmöglichkeiten für den fifty-fifty Joker
- Joker: Regelt die Funktionen der einzelnen Joker
- Game: Zuständig für die Erstellung des Fragen Arrays und den Spielablauf

### Fragen
Unsere Fragen werden in einer JSON Datei gespeichert, die in der Game Klasse aufgerufen wird, um dem Fragenarray zu erstellen. `private Question[] createQuestions()`
> Es kann vorkommen, dass man einige Frage nach mehreren Spieldurchgängen doppelt beantwortet. Wir haben in unserem JSON insgesamt 166 Fragen

### JavaFX
> Noch haben wir nicht begonnen ein graphisches Interface für unser Programm zu erstellen. Die HelloFX Klasse ist nur für Testzwecke da
