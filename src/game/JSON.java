package game;

import java.util.HashMap;
import java.util.Map.Entry;

public class JSON {

	private HashMap<String, Integer> integers = new HashMap<String, Integer>();
	private HashMap<String, String> strings = new HashMap<String, String>();
	private HashMap<String, Boolean> booleans = new HashMap<String, Boolean>();
	private HashMap<String, JSON> json = new HashMap<String, JSON>();

	public JSON() {

	}

	public JSON(String data) {
		System.out.println("string json in: " + data);
		char[] chars = data.toCharArray();
		boolean store = false;
		String stored = "";
		String type = "";
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == "(".charAt(0)) {
				store = true;
				type = "" + chars[i - 1];
				stored = "";
				continue;
			}
			if (c == ")".charAt(0)) {
				store = false;
				String[] dat;
				if (type != "J") {
					for (String sto : stored.split(",")) {
						switch (type) {
						case "I":
							dat = sto.split(":");
							integers.put(dat[0], Integer.parseInt(dat[1]));
							break;
						case "S":
							dat = sto.split(":");
							strings.put(dat[0], dat[1]);
							break;
						case "B":
							dat = sto.split(":");
							booleans.put(dat[0], dat[1] == "true");
							break;
						}
					}
				} else {
					String da = "";
					for (int j = i; j < chars.length; j++) {
						da = da + chars[i];
					}
					dat = da.split(":");
					String[] dat2 = new String[dat.length - 1];
					for (int j = 1; j < dat.length; j++) {
						dat2[j - 1] = dat[j];
					}
					json.put(dat[0], new JSON(String.join(":", dat2)));
					break; // break loop to not loop through saved json in json
				}
			}
			if (store) {
				stored = stored + c;
			}
		}
	}

	@Override
	public String toString() {
		String data = "";

		if (integers.size() != 0) {
			data = data + "I(";
			for (Entry<String, Integer> entry : integers.entrySet()) {
				data = data + entry.getKey() + ":" + entry.getValue() + ",";
			}
			data = data.substring(0, data.length() - 1);
			data = data + ")";
		}

		if (strings.size() != 0) {
			data = data + "S(";
			for (Entry<String, String> entry : strings.entrySet()) {
				data = data + entry.getKey() + ":" + entry.getValue() + ",";
			}
			data = data.substring(0, data.length() - 1);
			data = data + ")";
		}

		if (booleans.size() != 0) {
			data = data + "B(";
			for (Entry<String, Boolean> entry : booleans.entrySet()) {
				data = data + entry.getKey() + ":" + entry.getValue() + ",";
			}
			data = data.substring(0, data.length() - 1);
			data = data + ")";
		}

		if (json.size() != 0) {
			data = data + "J(";
			for (Entry<String, JSON> entry : json.entrySet()) {
				data = data + entry.getKey() + ":" + entry.getValue() + ",";
			}
			data = data.substring(0, data.length() - 1);
			data = data + ")";
		}

		System.out.println("to string: " + data);

		return data;
	}

	public void setInteger(String key, Integer value) {
		if (key.contains(" ")) {
			System.err.println("pls no spaces in Metadata  (JSON)");
		} else {
			integers.put(key, value);
		}

	}

	public void setString(String key, String value) {
		if (key.contains(" ")) {
			System.err.println("pls no spaces in Metadata  (JSON)");
		} else {
			strings.put(key, value);
		}
	}

	public void setBoolean(String key, Boolean value) {
		if (key.contains(" ")) {
			System.err.println("pls no spaces in Metadata  (JSON)");
		} else {
			booleans.put(key, value);
		}
	}

	public void setJSON(String key, JSON value) {
		if (key.contains(" ")) {
			System.err.println("pls no spaces in Metadata  (JSON)");
		} else {
			json.put(key, value);
		}
	}

	public Integer getInteger(String key) {
		return integers.get(key);
	}

	public String getString(String key) {
		return strings.get(key);
	}

	public boolean getBoolean(String key) {
		return booleans.get(key);
	}

	public JSON getJSON(String key) {
		return json.get(key);
	}
}
