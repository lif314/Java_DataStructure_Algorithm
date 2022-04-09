package sparsearray;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {

        /**
         * 创建原始数据
         */
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        System.out.println("===原始二维数据===");
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d ", data);
            }
            System.out.println();
        }


        /**
         * 二维数组转稀疏数组
         *  1、先遍历二维数组，得到非0数据的个数
         *  2、创建对应的稀疏数组
         *  3、稀疏数组赋值
         */
        int sum = 0;
        for (int[] row : chessArray) {
            for (int data : row) {
                if(data != 0){
                    sum++;
                }
            }
        }

        int[][] sparsearray = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparsearray[0][0] = 11; // 二维数组的行数
        sparsearray[0][1] = 11; // 二维数据的列数
        sparsearray[0][2] = sum; // 二维数组的非0值个数

        // 遍历二维数组，将非0值存在稀疏数组中
        int count = 0; // 记录第几个非0值
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArray[i][j] != 0){
                    // 赋值
                    count++;
                    sparsearray[count][0] = i; // 行
                    sparsearray[count][1] = j; // 列
                    sparsearray[count][2] = chessArray[i][j]; // 值
                }
            }
        }

        System.out.println("===稀疏数组===");
        for (int[] row : sparsearray) {
            for (int data : row) {
                System.out.printf("%d ", data);
            }
            System.out.println();
        }


        /**
         * 稀疏数组转为二维数组
         *  1、读取行和列
         *  2、遍历稀疏数组进行取值
         */
        int[][] sparse2array = new int[sparsearray[0][0]][sparsearray[0][1]];
        for(int k = 1; k < sparsearray.length; k++){
            sparse2array[sparsearray[k][0]][sparsearray[k][1]] = sparsearray[k][2];
        }

        System.out.println("===二维数组===");
        for (int[] rowdata : sparse2array) {
            for (int data : rowdata) {
                System.out.printf("%d ", data);
            }
            System.out.println();
        }
    }
}
