package org.SRM.MathTools;

import java.text.DecimalFormat;

import static java.lang.Math.pow;

public class Matrix {

    private final int row;//行
    private final int col;//列
    double[][] data;

    public Matrix(int row, int col,double [][]Data) {
        this.row = row;
        this.col = col;
        this.data = Data;
    }

    public void setMatrix(int row , int col, double value) {
        this.data[row - 1][col - 1] = value;
    }

    public double get(int row, int col) {
        return data[row - 1][col - 1] ;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Matrix add(Matrix matrix) {
        if(this.getCol() != matrix.getCol() && this.getRow() != matrix.getRow()) {
            return null;
        }
        double[][] add = new double[this.row][this.col];
        for(int i = 0;i<col;i++) {
            for(int j = 0;j<row;j++) {
                add[i][j] = this.data[i][j] + matrix.data[i][j];
            }
        }
        Matrix another = new Matrix(this.col,this.row,add);
        System.out.println("after add:");
        return another;
    }

    public Matrix crossMultiply(Matrix matrix) {
        if(matrix instanceof Vector vector){
            Vector result = null;
            if(this.col == vector.getSize()){
                double[] result_data = new double[this.getRow()];
                for (int i = 0; i < result_data.length; i++) {
                    for (int i1 = 0; i1 < this.col; i1++) {
                        result_data[i] += get(i+1,i1+1) * vector.get(i1);
                    }
                }
                result = new Vector(this.getRow(), result_data);
            } else if(this.row == vector.getSize()){
                double[] result_data = new double[this.getCol()];
                for (int i = 0; i < result_data.length; i++) {
                    for (int i1 = 0; i1 < this.row; i1++) {
                        result_data[i] += get(i1+1,i+1) * vector.get(i1);
                    }
                }
                result = new Vector(this.getCol(), result_data);
            }
            return result;
        }
        if(this.col != matrix.row) {
            return null;
        }
        double[][] mul = new double[this.row][matrix.col];
        double temp = 0;
        for(int i = 0;i<this.row;i++) {
            for(int k = 0;k<matrix.col;k++) {
                for(int j = 0;j<this.col;j++) {
                    temp += this.data[i][j] * matrix.data[j][k];
                }
                mul[i][k] = temp;
                temp = 0;
            }
        }
        Matrix another = new Matrix(this.row, matrix.col, mul);
        System.out.println("after crossMultiply:");
        return another;
    }




    public Matrix transpose() {

        double[][] tran = new double[this.row][this.col];
        for(int i = 0;i<this.row;i++) {
            for(int j = 0;j<this.col;j++) {
                tran[j][i] = this.data[i][j];
            }
        }
        Matrix another = new Matrix(this.col,this.row,tran);
        System.out.println("after transpose:");
        return another;
    }

    public Vector toWeightByRootMeanSquare(){
        int n = this.getCol();
        System.out.println(this.toString());
        double[] weight = new double[n];
        for (int i = 0; i < n; i++) {
            weight[i] = 1;
            for (int j = 0; j < n; j++) {
                weight[i] *= this.get(i+1,j+1);
            }
            weight[i] = pow(weight[i], 1.0 / n);
        }
        return new Vector(n, weight);
    }

    public String toString() {

        DecimalFormat df = new DecimalFormat("0.000");
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<this.row;i++) {
            result.append(df.format(data[i][0]));
            for(int j = 1;j<this.col;j++) {
                result.append(" ").append(df.format(data[i][j]));
            }
            result.append("\n");
        }
        return result.toString();
    }

}

