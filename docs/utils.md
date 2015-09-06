Die folgenden Helfermethoden sind *nicht* generisch da Generics nicht
im Unterricht behandelt wurden und aufgrund dessen nicht im Code
verwendet werden dürfen.

# Häufigkeitsverteilung

Iteriert über eine Liste von Strings und inkrementiert einen Counter
in einer HashMap für jeden einzigartigen Schlüssel.  Das Resultat ist
ein Histogramm welches für Statistiken verwendet werden kann.

```java
public static Map<String, Integer> frequencies (ArrayList<String> list) {
    Map<String, Integer> result = new HashMap<String, Integer>();
    for (String key: list) {
        Integer count = result.get(key);
        if (count == null) {
            result.put(key, 1);
        }
        else {
            result.put(key, count + 1);
        }
    }
    return result;
}
```

# Map-Zugriff mit Rückfallwert

Verhält sich wie `Map.get`, akzeptiert aber einen Rückgabewert für den
Fall dass der Zugriff auf den Schlüssel `null` zurückgibt und gibt
diesen stattdessen zurück.

```java
public static int getOrDefault(Map<String, Integer> map, String key, int def) {
    Integer result = map.get(key);
    if (result == null) {
        result = def;
    }
    return result;
}
```
