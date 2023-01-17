import java.util.*;
import java.util.stream.Collectors;

class Structure implements Comparable<Structure>{
    int x,y,type;

    public Structure(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type; //기둥 = 0, 보 = 1
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Structure structure = (Structure) o;
        return x == structure.x && y == structure.y && type == structure.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, type);
    }

    @Override
    public int compareTo(Structure o) {
        if(x == o.x) {
            if(y == o.y) {
                return type - o.type;
            }
            return y - o.y;
        }
        else return x - o.x;
    }
}

class Solution {
    public int[][] solution(int n, int[][] build_frame) {
        int[][] map_pillar = new int[n+1][n+1];
        int[][] map_beam = new int[n+1][n+1];
        Set<Structure> structures = new HashSet<>();

        for (int[] build : build_frame) {
//            new Structure(build[0], build[1], build[2]);
            int r = n - build[1], c = build[0];
            if (build[2] == 0) {  //기둥
                if (build[3] == 1 && (r == n || map_pillar[r][c] != 0 || map_beam[r][c] != 0)) {  //설치 -> 바닥 위 or 보의 한쪽끝부분위 or 다른 기둥 위
                    map_pillar[r][c] = 1;
                    map_pillar[r - 1][c] = 1;
                    structures.add(new Structure(r, c, 0));
                } else if(build[3] == 0) {  //삭제
                    //기둥(위위 : 1이면서 보가 0이면 안댐)
                    //보(위쪽 좌우 조건 확인)
                    if ((structures.contains(new Structure(r - 1, c, 0)) && map_beam[r - 1][c] == 0))
                        continue;
                    if (structures.contains(new Structure(r - 1, c - 1, 1)) && !structures.contains(
                            new Structure(r, c - 1, 0)) && (!structures.contains(new Structure(r - 1, c, 1))
                            || !structures.contains(new Structure(r - 1, c - 2, 1))))
                        continue;
                    if (structures.contains(new Structure(r - 1, c, 1)) && !structures.contains(
                            new Structure(r, c + 1, 0)) && (!structures.contains(new Structure(r - 1, c + 1, 1))
                            || !structures.contains(new Structure(r - 1, c - 1, 1))))
                        continue;
                    if(!structures.contains(new Structure(r+1,c,0))) map_pillar[r][c] = 0;
                    if(!structures.contains(new Structure(r-1,c,0))) map_pillar[r - 1][c] = 0;
                    structures.remove(new Structure(r, c, 0));
                }
            } else { //보
                if (build[3] == 1 && (structures.contains(new Structure(r+1,c,0))|| structures.contains(new Structure(r+1,c + 1,0))
                        || (map_beam[r][c] == 1 && map_beam[r][c + 1] == 1))) {  //설치 -> 한쪽끝부분이 기둥위 or 양쪽끝부분이 다른보와 연결
                    map_beam[r][c] = 1;
                    map_beam[r][c + 1] = 1;
                    structures.add(new Structure(r, c, 1));
                } else if(build[3] == 0){
                    //기둥(양쪽 위로 기둥이 오롯이 보에만 의지하고있는ㄴ지 확인)
                    //보(좌우)
                    if (structures.contains(new Structure(r, c, 0)) && !structures.contains(new Structure(r, c-1, 1)) && !structures.contains(
                            new Structure(r + 1, c, 0)))
                        continue;
                    if (structures.contains(new Structure(r, c + 1, 0)) && !structures.contains(new Structure(r, c+1, 1)) && !structures.contains(
                            new Structure(r + 1, c + 1, 0)))
                        continue;
                    if (structures.contains(new Structure(r, c - 1, 1)) &&
                            !structures.contains(new Structure(r + 1, c - 1, 0)) && !structures.contains(
                            new Structure(r + 1, c, 0)))
                        continue;
                    if (structures.contains(new Structure(r, c + 1, 1)) &&
                            !structures.contains(new Structure(r + 1, c + 1, 0)) && !structures.contains(
                            new Structure(r + 1, c+2, 0)))
                        continue;

                    if(!structures.contains(new Structure(r,c-1,1))) map_beam[r][c] = 0;
                    if(!structures.contains(new Structure(r,c+1,1))) map_beam[r][c + 1] = 0;
                    structures.remove(new Structure(r, c, 1));
                }
            }
        }

        List<Structure> list = new ArrayList<>(structures);
        for (Structure structure : list) {
            int tmp = structure.x;
            structure.x = structure.y;
            structure.y = n - tmp;
        }
        list.sort(Comparator.naturalOrder());

        int[][] answer = new int[list.size()][3];
        for (int i = 0; i < answer.length; i++) {
            answer[i][0] = list.get(i).x;
            answer[i][1] = list.get(i).y;
            answer[i][2] = list.get(i).type;
        }
        return answer;

    }
}