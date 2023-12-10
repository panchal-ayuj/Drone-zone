import java.util.*;

public class Main {
    static class Pair
    {
        int first, second;

        public Pair(int first, int second)
        {
            this.first = first;
            this.second = second;
        }
    }
    static int nextY[] = { -1, 0, 1, 0 };
    static int nextX[] = { 0, 1, 0, -1 };

    public static void main(String[] args) {
        List<List<Integer>> grid = new ArrayList<List<Integer>>();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cols and rows: ");
        int cols = scanner.nextInt();
        int rows = scanner.nextInt();

        for (int i = 0; i < rows; i++){
            List<Integer> l1 = new ArrayList<>();
            for (int j = 0; j < cols; j++){
                l1.add(0);
            }
            grid.add(l1);
        }

        int noOfDrones = 4;
        List<Pair> droneList = new ArrayList<Pair>();
        for (int i = 0; i < noOfDrones; i++){
            System.out.println("Enter drone's x and y pos: ");
            int droneX = scanner.nextInt();
            int droneY = scanner.nextInt();
            grid.get(droneY).set(droneX, 1);
            Pair newPair = new Pair(droneX, droneY);
            droneList.add(newPair);
        }

//        System.out.println(grid);
        System.out.println("Enter target's x and y pos: ");
        int targetX = scanner.nextInt();
        int targetY = scanner.nextInt();
        grid.get(targetY).set(targetX, 2);

        List<List<Boolean>> vis = new ArrayList<List<Boolean>>();
        for (int i = 0; i < rows; i++){
            List<Boolean> l1 = new ArrayList<>();
            for (int j = 0; j < cols; j++){
                l1.add(false);
            }
            vis.add(l1);
        }

        for (int k = 0; k<noOfDrones; k++){
            BFS(grid, vis, droneList.get(k).second, droneList.get(k).first, rows, cols);
            System.out.println(vis);
            for (int i = 0; i < cols; i++){
                for (int j = 0; j < rows; j++){
                    vis.get(j).set(i, false);
                }
            }
        }
    }

    public static Boolean isValid(List<List<Boolean>> vis, int row, int col, int rows, int cols){
        if (row < 0 || col < 0 || row >= rows || col >= cols) {
            return false;
        }
        if (vis.get(row).get(col)) {
            return false;
        }
        return true;
    }

    public static void BFS(List<List<Integer>> grid, List<List<Boolean>> vis, int row, int col, int rows, int cols){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row,col));
        vis.get(row).set(col, true);
        Boolean targetFound = false;
        while (!q.isEmpty() && !targetFound){
            int y = q.peek().first;
            int x = q.peek().second;
            q.remove();
            System.out.println("X axis: " + x + ", Y axis: " + y);
            for(int i=0; i < 4; i++){
                int newY = y + nextY[i];
                int newX = x + nextX[i];

                if (isValid(vis, newY, newX, rows, cols)){
                    q.add(new Pair(newY, newX));
                    vis.get(newY).set(newX, true);
                    if(grid.get(newY).get(newX) == 2){
                        System.out.println("Target found!!");
                        targetFound = true;
                    }
                }
            }
        }
    }
}

//t,t,t,t
//t,t,t,0
//t,t,0,0
//t,0,0,0