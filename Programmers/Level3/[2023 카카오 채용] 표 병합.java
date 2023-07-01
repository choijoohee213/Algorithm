import java.util.*;
import java.util.Map.Entry;

class Solution {
    final int size = 50;
    List<String> answer;
    String[] table;
    Map<Integer, Integer> idx;

    public String[] solution(String[] commands) {
        answer = new ArrayList<>();
        table = new String[(size+1) * (size+1)];
        idx = new HashMap<>();

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                idx.put(i * size + j, i * size + j);
            }
        }

        for (String command : commands) {
            String[] com = command.split(" ");

            if(com[0].equals("UPDATE")) {
                if(com.length == 4) {
                    update(Integer.valueOf(com[1]), Integer.valueOf(com[2]), com[3]);
                } else {
                    update(com[1], com[2]);
                }
            } else if(com[0].equals("MERGE")) {
                merge(Integer.valueOf(com[1]), Integer.valueOf(com[2]), Integer.valueOf(com[3]), Integer.valueOf(com[4]));
            } else if(com[0].equals("UNMERGE")) {
                unmerge(Integer.valueOf(com[1]), Integer.valueOf(com[2]));
            } else {
                print(Integer.valueOf(com[1]), Integer.valueOf(com[2]));
            }
        }
        return answer.toArray(new String[answer.size()]);
    }

    private void update(int r, int c, String value) {
        int index = getIdx(r * size + c);
        table[index] = value;
    }

    private void update(String value1, String value2) {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                int index = getIdx(i * size + j);
                if(table[index] != null && table[index].equals(value1)) {
                    table[index] = value2;
                }
            }
        }
    }

    private void merge(int r1, int c1, int r2, int c2) {
        int idx1 = getIdx(r1 * size + c1);
        int idx2 = getIdx(r2 * size + c2);
        if(idx1 == idx2) return;

        if(table[idx1] != null && table[idx2] != null) {
            idx.put(idx2, idx1);
        } else if(table[idx1] != null) {
            idx.put(idx2, idx1);
        } else {
            idx.put(idx1, idx2);
        }
    }

    private void unmerge(int r, int c) {
        int index = getIdx(r * size + c);
        String val = table[index];
        Map<Integer, Integer> newIdx = new HashMap<>(idx);

        for (Entry<Integer, Integer> entry : idx.entrySet()) {
            if(getIdx(entry.getValue()) == index) {
                newIdx.put(entry.getKey(), entry.getKey());
                table[entry.getKey()] = null;
            }
        }

        if (val != null) {
            table[r * size + c] = val;
        }
        idx = newIdx;
    }

    private void print(int r, int c) {
        int idx = getIdx(r * size + c);
        answer.add(table[idx] == null? "EMPTY" : table[idx]);
    }

    private int getIdx(int index) {
        while(index != idx.get(index)) {
            index = idx.get(index);
        }
        return index;
    }
}