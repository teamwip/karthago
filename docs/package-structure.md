# Geplante Paketstruktur #

Idee war drei Pakete für MVC zu verwenden sowie eigene Pakete für die
Datenbank, XML-Schnittstelle und den Verwaltungscode zu verwenden.

Präfix: `org.teamwip.karthago`

- `controller`
- `db`
- `gui`
- `main`
- `model`
- `xml`

# Tatsächliche Paketstruktur #

Die Umsetzung von MVC geschah nach Dozentenvorgabe in einem separaten
Paket für das für jede einzelne Activity fünf Java-Klassen erstellt
werden:

- `ApplicationLogic.java`
- `Data.java`
- `EventToListenerMapping.java`
- `Gui.java`
- `Init.java`

Dazu kommen noch die geplanten Pakete für die Datenbank und
XML-Schnittstelle.

Präfix: `de.bg.fhdw.bfwi413a.karthago`

- `activities`
  - `config`
  - `lm1_mc`
  - `lm2_ft`
  - `lm3_g`
  - `login`
  - `menu`
  - `selection`
  - `statistics`
- `db`
- `onboarding`
- `xml`
