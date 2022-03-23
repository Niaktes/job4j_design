package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

	public static Info diff(Set<User> previous, Set<User> current) {
		int added = 0;
		int changed = 0;
		Map<Integer, String> map = new HashMap<>();
		for (User user : previous) {
			map.put(user.getId(), user.getName());
		}
		for (User user : current) {
			int id = user.getId();
			if (map.containsKey(id)) {
				if (!map.get(id).equals(user.getName())) {
					changed++;
				}
				map.remove(id);
			} else {
				added++;
			}
		}
		int deleted = map.size();
		return new Info(added, changed, deleted);
	}

}
