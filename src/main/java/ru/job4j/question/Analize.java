package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        Map<Integer, String> previousM = previous.stream().collect(Collectors.toMap(User::getId, User::getName));
        Map<Integer, String> currentM = current.stream().collect(Collectors.toMap(User::getId, User::getName));

        if (previous.hashCode() != current.hashCode()) {
            for (Integer key : previousM.keySet()) {
                if (!currentM.containsKey(key)) {
                    deleted++;
                }
                if (currentM.containsKey(key) && !currentM.get(key).equals(previousM.get(key))) {
                    changed++;
                }
            }
            for (Integer key : currentM.keySet()) {
                if (!previousM.containsKey(key)) {
                    added++;
                }
            }
        }
        return new Info(added, changed, deleted);
    }

}