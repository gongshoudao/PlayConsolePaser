import java.util.Arrays;

public class TestMain {
    public void main(String[] args) {
        int[][] a = new int[3][4];
        int[][] b = new int[3][4];
        int[][] c = new int[3][4];
        fillMatrix(a);
        fillMatrix(b);
        int[][] result = matrixPlus(a, b);
        System.out.println("result = " + Arrays.deepToString(result));
    }

    public static void fillMatrix(int[][] a) {
        if (a.length == 0)
            return;
        int subSize = a[0].length;
        for (int i = 0; i < a.length; i++) {
            for (int i1 = 0; i1 < subSize; i1++) {
                a[i][i1] = 1+i1;
            }
        }
    }

    public static int[][] matrixPlus(int[][] a, int[][] b) {
        if (a == null || b == null)
            return null;
        if (a.length != b.length)
            return null;
        if (a.length == 0)
            return new int[0][0];
        if (a[0] == null || b[0] == null)
            return null;
        if (a[0].length != b[0].length)
            return null;
        int column = a.length;
        int row = a[0].length;
        //两个行数、列数分别相等的矩阵（即同型矩阵），加减法运算才有意义
        int[][] result = new int[column][row];
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }
}
