
public class TwoDimensionalArray {

    public static void main(String[] args) {
        int[][] matrix = {
            {1,1,1,1},
            {1,0,1,1},
            {1,1,1,0},
            {1,0,1,1}
        };
        printImage(matrix);
        setZeroToAllZero(matrix);
        printImage(matrix);
    }

    private static void setZeroToAllZero(int[][] matrix){
        int fc = -1; //0이 들어갈 위치를 저장할 곳
        int fr = -1;
        for(int row = 0; row < matrix.length; row++){//행단위

            for(int col = 0; col<matrix[row].length; col++){ //열단위로
                if(matrix[row][col] == 0){
                    if(fc == -1){ //처음찾은 0이라면 ㅈ
                        fc = col;
                        fr = row;
                    }
                    matrix[fr][col] = 0; //해당정보를 저장할 열 0으로 표시
                    matrix[row][fc] = 0;
                }
            }
        }
        if( fc == -1) return; //하나도 못찾음

        //0으로 세팅
        for(int col = 0; col<matrix[0].length; col++){
            if(matrix[fr][col] == 0 && col != fc){ //0이 세팅될 곳에 있다면
                //정보가 들어가 있는 방은 제외!
                setColsToZero(col, matrix);
            }
        }
        for(int row = 0; row <matrix.length; row++){
            if(matrix[row][fc] == 0){
                setRowToZero(row, matrix);
            }
        }
        //정보를 들고 있던 배열(후 순위)
        setColsToZero(fc, matrix);
    }

    private static void setColsToZero(int col, int[][] matrix) {
        for(int row = 0; row < matrix.length; row++){
            matrix[row][col] = 0;
        }
    }

    private static void setRowToZero(int row, int[][] matrix) {
        for(int col = 0; col < matrix.length; col++){
            matrix[row][col] = 0;
        }
    }

    private static void printImage(int[][] image){
        for(int i = 0; i <image.length; i++){
            for(int j =0; j<image[i].length; j++){
                System.out.print(image[i][j]+" ");
            }
            System.out.println();
        }
    }
}