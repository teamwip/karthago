// Vasilij Schneidermann
package de.bg.fhdw.bfwi413a.karthago;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Util {
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

	public static int getOrDefault(Map<String, Integer> map, String key, int def) {
		Integer result = map.get(key);
		if (result == null) {
			result = def;
		}
		return result;
	}
}
