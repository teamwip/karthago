Die Statistiken sind bewusst einfach gehalten und können logisch
unterteilt werden in XML- und Datenbankstatistiken.

Die XML-Statistiken handeln von den Karteien an sich welche von recht
statischer Natur sind.  Dafür wird die XML-Schnittstelle an den
aktuellen Kontext gebunden und nach Fragen sowie Karteinamen
abgefragt.  Daraus lässt sich die Anzahl der Karteien und Fragen
bestimmen sowie die Anzahl der Fragen aufgeschlüsselt nach Fragetyp
anzeigen.  Für diese Aufschlüsselung wurde eine Utility-Methode
geschrieben welche aus einer Liste von Strings ein Histogramm
erzeugt.  Eine weitere Utility-Methode dient dem Zweck aus diesem
Histogramm die Häufigkeit abzufragen und bei Bedarf auf einen
Default-Wert zurückfallen zu können.

Die Datenbankstatistiken sind dynamischer Natur und verwenden eigens
geschriebene Methoden aus der Datenbankschnittstelle.  Es wurde eine
separate Tabelle für sogenannte "Events" erstellt, d.h. Ereignisse
welche mit relevanten Metadaten beim Tätigen jeder statistikrelevanten
Handlung festgehalten werden.  Der Einfachheit halber geschieht dies
nur beim Beantworten jeder Frage mit der Information ob die Antwort
korrekt oder nicht korrekt ist.  Daraus kann auf eine ähnliche Weise
wie bei den XML-Statistiken festgestellt werden wieviele Fragen bisher
beantwortet wurden und wie hoch die Anteile an richtig und falsch
beantworteten Fragen sind.
