class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length, m = board[0].length;
        int[][] arr = new int[n+1][m+1];

        for (int[] info : skill) {
            int type = info[0];
            int degree = info[5];
            if(type == 1) {
                degree *= -1;
            }
            arr[info[1]][info[2]] += degree;
            arr[info[1]][info[4] + 1] -= degree;
            arr[info[3]+1][info[2]] -= degree;
            arr[info[3]+1][info[4]+1] += degree;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                arr[i][j] += arr[i][j-1];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[j][i] += arr[j-1][i];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] += arr[i][j];
                if(board[i][j] >= 1) {
                    answer++;
                }
            }
        }

        return answer;
    }
}