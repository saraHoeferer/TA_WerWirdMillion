# Wer Wird Millionär - Team Alphas
Wir haben in unserem Projekt das Spiel "Wer Wird Millionär" nachprogrammiert.
Es ist ein Wissensspiel, wo man unterschiedliche Frage beantworten muss um zu versuchen die Millionen zu gewinnen.
> Momentan befindet sich das Spiel in der Beta-Version.
>> Es ist noch kein graphisches Interface vorhanden, das Spiel wird in der Konsole gespielt

## Spiel
### Spiel starten
Das Spiel würd über die Main Funktion in der "Game" Klasse gestartet (Rechtsklick auf main -> Run main)

### Spiel spielen
Alle Eingaben für den Spielablauf folgen über die Tastatur. 
Es werden je nach Kategorie unterschiedliche Fragen gestellt.
Sollte eine Frage richtig sein, so steigt man eine Stufe auf.

### Joker
Es gibt drei verschiedene Joker.
Fifty-fifty Joker: zwei der falschen Antworten werden ausgeblendet sodass nur die richtige und eine flasche Antwort überbleiben.
Help Joker: Jemand aus unserem Team hilft dir die Frage zu beantworten. (Achtung: Die Antwort die wir geben muss nicht imemr stimmen)
Second Chance Joker: Wenn man eine Frage falsch beantwortet hat, kann man danach nocheinmal eine Antwortmöglichkeit abgeben (Achtung: Joker muss dafür vorher ausgewählt werden)

### Kategorie
Die Kategorie sind:
50€-> 100€ -> 200€ -> 300€ -> 500€ (Sicherheitsstufe) -> 1.000€ -> 2.000€ -> 4.000€ -> 8.000€ -> 16.000€ (Sicherheitsstufe) -> 32.000€ -> 64.000€ -> 125.000€ -> 500.000€ -> 1.000.0000€

### Ende
Man kann das Spiel am Anfang jeder Frage (außer der ersten) verlassen.
Das Spiel endet automatisch beim Erreichen der Million oder wenn man eine Frage falsch beantwortet

### Gewinn
Sollte man freiwillig das Spiel verlassen so gewinnt man den Betrag wo man als letztes gestanden ist.
Sollte man eine Frage falsch beantworten fällt man auf die jeweilige Sicherheitsstufe zurück (Wenn man vor der 500€ Frage verliert so gewinnt man 0€)
Sollte man ein bis zur Million schaffen und gewinnt man die Million

