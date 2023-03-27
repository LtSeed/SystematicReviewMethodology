package org.SRM.MathTools;

public class Vector extends Matrix{



    public double getAvenge(){
        return getAdding() / getSize();
    }

    public double getAdding(){
        double addition = 0;
        for (int i = 0; i < getSize(); i++) {
            addition += data[i][0];
        }
        return addition;
    }

    public int getSize() {
        return getRow();
    }

    public double get(int index) {
        return data[index][0] ;
    }

    private static double[][] getVector(int size, double[] data){
        double[][] aData = new double[size][1];
        for (int i = 0; i < size; i++) {
            aData[i][0] = data[i];
        }
        return aData;
    }
    public Vector(int size, double[] data) {
        super(size, 1, getVector(size, data));
    }

    public void pointDivide(Vector other) {
        if(other.getSize() != getSize()) return;
        for (int i = 0; i < getSize(); i++) {
            this.data[i][0] /= other.get(i);
        }
    }

    public void modify() {
        double adding = getAdding();
        for (int i = 0; i < getSize(); i++) {
            data[i][0] /= adding;
        }
    }
}
