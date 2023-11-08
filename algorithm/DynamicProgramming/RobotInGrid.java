import java.util.*;

class Point  {
    // 각 경우하는 위치가 담긴 
    int row, col;
    Point(int row, int col){
        this.row = row;
        this.col = col;
    }
}


class Solution  {
    
    //호출시 grid를 받음 ture면 벌쩡한 블록, false면 갈수 없는 블록
    public ArrayList<Point> findPath(boolean[][] grid){
        if(grid == null || grid.length == 0) return null; //널체크
        ArrayList<Point> path = new ArrayList<Point>(); //경로를 저장할 list
        if(findPath(grid,grid.length -1, grid[0].length -1, path)){ //재귀호출
            //그리드, 시작포인트(맨아래), 저장할 경로 
            return path;
        }else{ //만나는 곳이 없음
            return null;
        }
    
    }

    private boolean findPath(boolean[][] grid, int row, int col, ArrayList<Point> path) {
        if(!isUnRange(grid,row,col) || grid[row][col]) return false; 
        //해당 포인터가 그리드 밖에 있거나, 못들어가게 막아놓은 경우 false 
        if((row == 0 && col == 0) 
            || findPath(grid, row, col -1, path)
            || findPath( grid, row -1, col, path)
        ){ //경로에 추가하는 경우, 최종목적지 이거나 왼쪽으로 가거나, 오른쪽으로 
            //or 연산자를 통해 왼쪽먼저 체크 그다음 위로이동
            Point p = new Point(row, col); //경로를 찾음 패스에 찾음
            path.add(p);
            return true;
        }
        return false;
    }

    private boolean isUnRange(boolean[][] grid, int row, int col) {
        return row >= 0 && row <= grid.length -1 
        && col >= 0  && col <= grid[row].length -1;
    }
    
}


public class RobotInGrid {
   public static void main(String[] args) {
    boolean[][] grid = {
        {false,false,false,false},
        {false,false,false,true},
        {true,true,false,false},
        {false,false,false,false}
    };

    Solution sol = new Solution();
    ArrayList<Point> path = sol.findPath(grid);
    if(path != null){
        for(Point p : path){
            System.out.println(p.row + " " +p.col+" -> ");
        }
    }
    
   } 
}
