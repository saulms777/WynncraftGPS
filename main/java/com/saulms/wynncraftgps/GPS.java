package com.saulms.wynncraftgps;

import java.util.*;
import java.util.stream.Collectors;

class GPS {

    static Map<String, Integer> locations;
    static List<List<Node>> adj;

    GPS() {
        locations = new HashMap<>(){{
            // wynn
            put("ragni", 0);
            put("DS", 1);
            put("maltic", 2);
            put("IP", 3);
            put("nemract", 4);
            put("UC", 5);
            put("bremminglar", 6);
            put("almuj", 7);
            put("SST", 8);
            put("detlas", 9);
            put("savannah", 10);
            put("ternaves", 11);
            put("rymek", 12);
            put("tempo", 13);
            put("LS", 14);
            put("elkurn", 15);
            put("mines", 16);
            put("troms", 17);
            put("UR", 18);
            put("iboju", 19);
            put("nesaak", 20);
            put("IB", 21);
            put("lusuco", 22);

            // ocean
            put("selchar", 23);
            put("nexus", 24);
            put("bear", 25);
            put("rooster", 26);
            put("zhight", 27);
            put("pirate", 28);
            put("GG", 29);
            put("durum", 30);
            put("volcano", 31);
            put("maro", 32);
            put("skiens", 33);
            put("dead", 34);
            put("nodguj", 35);
            put("dujgon", 36);
            put("mage", 37);
            put("half-moon", 38);
            put("craftmas", 39);

            // gavel
            put("llevigar", 40);
            put("olux", 41);
            put("NotG", 42);
            put("bucie", 43);
            put("gelibord", 44);
            put("efilim", 45);
            put("lexdale", 46);
            put("forgery", 47);
            put("light", 48);
            put("RoL", 49);
            put("NoL", 50);
            put("cinfras", 51);
            put("letvus", 52);
            put("aldorei", 53);
            put("thanos", 54);
            put("qira", 55);
            put("bantisu", 56);
            put("thesead", 57);
            put("eltom", 58);
            put("rodoroc", 59);
            put("maex", 60);
            put("TCC", 61);
            put("kandon-beda", 62);
            put("ahmsord", 63);
            put("jofash", 64);

            // corkus
            put("corkus", 65);
            put("FF", 66);
            put("relos", 67);
            put("LI", 68);

            // silent expanse
            put("lutho", 69);
            put("TNA", 70);
            put("EO", 71);
            put("dern", 72);
        }};

        adj = new ArrayList<>();
        for (int i = 0; i < locations.size(); i++) adj.add(new ArrayList<>());

        { // wynn
            addPath("ragni", "DS", 318, true);
            addPath("ragni", "maltic", 432, true);
            addPath("DS", "maltic", 359, true);
            addPath("maltic", "nemract", 536, true);
            addPath("detlas", "UC", 440, true);
            addPath("nemract", "UC", 280, true);
            addPath("nemract", "IP", 466, true);
            addPath("maltic", "IP", 405, true);
            addPath("UC", "bremminglar", 496, true);
            addPath("detlas", "bremminglar", 566, true);
            addPath("detlas", "savannah", 251, true);
            addPath("almuj", "savannah", 297, true);
            addPath("ternaves", "savannah", 192, true);
            addPath("detlas", "ternaves", 355, true);
            addPath("ternaves", "savannah", 189, true);
            addPath("almuj", "ternaves", 364, true);
            addPath("bremminglar", "almuj", 206, true);
            addPath("bremminglar", "savannah", 377, true);
            addPath("almuj", "SST", 569, true);
            addPath("almuj", "rymek", 930, true);
            addPath("SST", "rymek", 763, true);
            addPath("detlas", "elkurn", 610, true);
            addPath("elkurn", "tempo", 308, true);
            addPath("nesaak", "elkurn", 391, true);
            addPath("mines", "ternaves", 315, true);
            addPath("mines", "detlas", 397, true);
            addPath("elkurn", "mines", 750, true);
            addPath("nesaak", "IB", 151, true);
            addPath("nesaak", "lusuco", 660, true);
            addPath("nesaak", "iboju", 862, true);
            addPath("troms", "iboju", 246, true);
            addPath("troms", "UR", 199, true);
            addPath("ragni", "troms", 677, true);
            addPath("ragni", "tempo", 786, true);
            addPath("tempo", "LS", 218, true);
        }
        { // gavel
            addPath("", "", 0, true);
        }
        { // corkus island

        }
        { // silent expanse
            addPath("mines", "lutho", 1181, true);
            addPath("lutho", "TNA", 274, true);
            addPath("TNA", "EO", 384, true);
            addPath("EO", "dern", 310, true);
        }
        { // seaskipper
            addPath("nesaak", "nemract", 650, true);
        }
        { // fast travel
            addPath("ragni", "detlas", 1, true);
            addPath("IP", "savannah", 1, true);
            addPath("detlas", "letvus", 50, true);
            addPath("light", "RoL", 1, true);
            addPath("RoL", "NoL", 1, true);
            addPath("cinfras", "aldorei", 50, false);
            addPath("cinfras", "thesead", 50, false);
            addPath("cinfras", "rodoroc", 50, false);
            addPath("cinfras", "ahmsord", 50, false);
            addPath("selchar", "corkus", 100, true);
            addPath("corkus", "kandon-beda", 50, true);
            addPath("thesead", "eltom", 1, true);
            addPath("rodoroc", "thanos", 1, true);
            addPath("TCC", "kandon-beda", 1, true);
            addPath("dern", "lutho", 1, false);
        }
        { // nexus

        }
    }

    private void addPath(String source, String target, int distance, boolean bidirectional) {
        adj.get(locations.get(source)).add(new Node(locations.get(target), distance));
        if (bidirectional) adj.get(locations.get(target)).add(new Node(locations.get(source), distance));
    }

    String outputPath(String source, String target) {
        Map<Integer, String> locationsReverse =
                locations.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        int sourceNum;
        int targetNum;
        try {
            sourceNum = locations.get(source);
            targetNum = locations.get(target);
        } catch (NullPointerException e) {
            return "No node found";
        }
        StringBuilder path = new StringBuilder();
        for (int node : dijkstra(sourceNum, targetNum)) {
            path.append(locationsReverse.get(node)).append(" - ");
        }
        path.delete(path.length() - 2, path.length());
        return path.toString();
    }

    private List<Integer> dijkstra(int source, int target) {
        int nodeAmount = adj.size();
        PriorityQueue<Node> queue = new PriorityQueue<>(nodeAmount, new Node());
        int[] distances = new int[nodeAmount];
        Node[] previous = new Node[nodeAmount];

        for (int i = 0; i < nodeAmount; i++) distances[i] = Integer.MAX_VALUE; // set each distance to infinity
        distances[source] = 0; // set distance to source to 0
        queue.add(new Node(source, 0)); // add source node to the priority queue

        // main loop
        Set<Integer> explored = new HashSet<>();
        int shortestNum;
        do {
            // find shortest distance node in priority queue
            Node shortest = queue.remove();
            shortestNum = shortest.getNode();

            // calculate neighbours for node
            explored.add(shortestNum);
            for (int i = 0; i < adj.get(shortestNum).size(); i++) {
                Node neighbour = adj.get(shortestNum).get(i);
                int neighbourNum = neighbour.getNode();
                int neighbourDistance = neighbour.getCost();
                if (explored.contains(neighbourNum)) continue;

                // find new distance and check if shorter
                int newDistance = distances[shortestNum] + neighbourDistance;
                if (newDistance < distances[neighbourNum]) {
                    distances[neighbourNum] = newDistance;
                    previous[neighbourNum] = shortest;
                }

                // add current node to queue
                queue.add(new Node(neighbourNum, distances[neighbourNum]));
            }
        } while (target != shortestNum);

        // find path
        LinkedList<Integer> path = new LinkedList<>(){{add(target);}};
        int current = target;
        do {
            current = previous[current].getNode();
            path.addFirst(current);
        } while (current != source);
        return path;
    }

}

class Node implements Comparator<Node> {

    private int node;
    private int cost;

    public Node() {}

    public Node(int node, int distance) {
        this.node = node;
        this.cost = distance;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return Integer.compare(node1.cost, node2.cost);
    }

    int getNode() {
        return node;
    }

    int getCost() {
        return cost;
    }

}
