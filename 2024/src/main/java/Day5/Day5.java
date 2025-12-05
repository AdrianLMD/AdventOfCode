package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Day5 {
    public static void main(String[] args) throws IOException {
        List<String> rules = new ArrayList<>();
        List<List<Integer>> updates = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./inputs/Day5Input.txt"))) {
            String line;
            boolean parsingUpdates = false;

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    parsingUpdates = true;
                    continue;
                }
                if (!parsingUpdates) {
                    rules.add(line);
                } else {
                    List<Integer> update = new ArrayList<>();
                    for (String page : line.split(",")) {
                        update.add(Integer.parseInt(page));
                    }
                    updates.add(update);
                }
            }
        }

        Map<Integer, Set<Integer>> precedenceMap = buildPrecedenceMap(rules);
        int sumOfMiddlePagesCorrect = 0;
        int sumOfMiddlePagesFixed = 0;

        List<List<Integer>> incorrectUpdates = new ArrayList<>();

        for (List<Integer> update : updates) {
            if (isCorrectOrder(update, precedenceMap)) {
                int middleIndex = update.size() / 2;
                sumOfMiddlePagesCorrect += update.get(middleIndex);
            } else {
                incorrectUpdates.add(update);
            }
        }

        for (List<Integer> update : incorrectUpdates) {
            List<Integer> fixedUpdate = fixOrder(update, precedenceMap);
            int middleIndex = fixedUpdate.size() / 2;
            sumOfMiddlePagesFixed += fixedUpdate.get(middleIndex);
        }

        System.out.println("Sum of middle pages (correct updates): " + sumOfMiddlePagesCorrect);
        System.out.println("Sum of middle pages (fixed updates): " + sumOfMiddlePagesFixed);
    }

    private static Map<Integer, Set<Integer>> buildPrecedenceMap(List<String> rules) {
        Map<Integer, Set<Integer>> precedenceMap = new HashMap<>();

        for (String rule : rules) {
            String[] parts = rule.split("\\|");
            int before = Integer.parseInt(parts[0]);
            int after = Integer.parseInt(parts[1]);

            precedenceMap.putIfAbsent(before, new HashSet<>());
            precedenceMap.get(before).add(after);
        }

        return precedenceMap;
    }

    private static boolean isCorrectOrder(List<Integer> update, Map<Integer, Set<Integer>> precedenceMap) {
        Set<Integer> seen = new HashSet<>();
        for (int current : update) {
            if (precedenceMap.containsKey(current)) {
                for (int mustFollow : precedenceMap.get(current)) {
                    if (seen.contains(mustFollow)) {
                        return false;
                    }
                }
            }
            seen.add(current);
        }
        return true;
    }

    private static List<Integer> fixOrder(List<Integer> update, Map<Integer, Set<Integer>> precedenceMap) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

        for (int page : update) {
            inDegree.put(page, 0);
            adjacencyList.put(page, new ArrayList<>());
        }

        for (int page : update) {
            if (precedenceMap.containsKey(page)) {
                for (int mustFollow : precedenceMap.get(page)) {
                    if (inDegree.containsKey(mustFollow)) {
                        adjacencyList.get(page).add(mustFollow);
                        inDegree.put(mustFollow, inDegree.get(mustFollow) + 1);
                    }
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }
        //Oh, hi Marc :)
        List<Integer> sortedOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sortedOrder.add(current);

            for (int neighbor : adjacencyList.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return sortedOrder;
    }
}