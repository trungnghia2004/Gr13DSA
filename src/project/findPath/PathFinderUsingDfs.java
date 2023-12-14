package project.findPath;



import project.test2.GamePanel;

import java.util.ArrayList;
import java.util.Stack;

public class PathFinderUsingDfs {
    GamePanel gp;
    public static class Node {
        public int x;
        public int y;
        public int direction;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.direction = 0;
        }
    }
    public PathFinderUsingDfs(GamePanel gp) {
        this.gp = gp;
    }
    Stack<Node> stack = new Stack<>();

    public ArrayList<String> path;
    public void findPathUsingDfs(int startCol, int startRow, int colTh, int rowTh) {
        boolean[][] visited = new boolean[gp.tileManager.map.length][gp.tileManager.map[0].length];
        stack.push(new Node(startRow, startCol));
        visited[startRow][startCol]=true;
        Node temp;

        while (!stack.isEmpty()) {
            temp = stack.pop();
            int x = temp.x;
            int y = temp.y;
            int dir = temp.direction;
            temp.direction += 1;
            stack.push(temp);
//            if (x == gp.player.worldX/48 && y == gp.player.worldY/48) {
//                return;
//            }
            if (x == rowTh && y == colTh) {
                return;
            }
            if (dir == 0) {//up
                if (x - 1 > 0 && gp.tileManager.map[x - 1][y] != 1 && gp.tileManager.map[x-1][y] != 4
                        && gp.tileManager.map[x-1][y] != 5&& !visited[x - 1][y]) {
                    visited[x - 1][y] = true;
                    stack.push(new Node(x - 1, y));
                }
            } else if (dir == 1) {//left
                if (y - 1 > 0 && gp.tileManager.map[x][y - 1] != 1
                        && gp.tileManager.map[x][y - 1] != 4&& gp.tileManager.map[x][y - 1] != 5
                        && !visited[x][y - 1]) {
                    visited[x][y - 1] = true;
                    stack.push(new Node(x, y - 1));
                }
            } else if (dir == 2) {//down
                if (x + 1 < gp.tileManager.map.length && gp.tileManager.map[x + 1][y] != 1
                        && gp.tileManager.map[x + 1][y] != 4&& gp.tileManager.map[x + 1][y] != 5 && !visited[x + 1][y]) {
                    visited[x + 1][y] = true;
                    stack.push(new Node(x + 1, y));
                }
            } else if (dir == 3) {//right
                if (y + 1 < gp.tileManager.map[0].length && gp.tileManager.map[x][y + 1] != 1
                        && gp.tileManager.map[x][y + 1] != 4&& gp.tileManager.map[x][y + 1] != 5 && !visited[x][y + 1]) {
                    visited[x][y + 1] = true;
                    stack.push(new Node(x, y + 1));
                }
            } else {
                stack.pop();
            }
        }
    }
    public void getPath(int startCol, int startRow, int colTh, int rowTh) {

            findPathUsingDfs(startCol, startRow, colTh, rowTh);

//            gp.keyH.moved=false;
            path = new ArrayList<>();
            while (!stack.isEmpty()) {
                switch (stack.pop().direction - 1) {
                    case 0 -> path.add("up");
                    case 1 -> path.add("left");
                    case 2 -> path.add("down");
                    case 3 -> path.add("right");
                }
            }
    }
}


