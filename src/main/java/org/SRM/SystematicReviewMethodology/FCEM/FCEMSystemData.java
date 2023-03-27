package org.SRM.SystematicReviewMethodology.FCEM;

import org.SRM.MathTools.Matrix;
import org.SRM.MathTools.Vector;
import org.SRM.SystematicReviewMethodology.SystemData;
import org.SRM.SystematicReviewMethodology.SystemMeta;

import java.util.ArrayList;
import java.util.List;

public class FCEMSystemData implements SystemData {

    List<FCEMMembershipMatrix> R = new ArrayList<>();
    FCEMFactorVector F;
    FCEMEvaluationVector E;

    public FCEMSystemData(List<String> F, List<String> E, double[] E_data, double[][][] R, double[][] decideMat) {
        for (double[][] doubles : R) {
            FCEMMembershipMatrix e = new FCEMMembershipMatrix(F.size(), E.size(), doubles);
            this.R.add(e);
        }
        this.E = new FCEMEvaluationVector(E.size(),E,E_data);
        this.E.modify();
        System.out.println("E:"+this.E.toString());
        Vector vector = new Matrix(F.size(),E.size(),decideMat).toWeightByRootMeanSquare();
        double[] data = new double[vector.getSize()];
        for (int i = 0; i < vector.getSize(); i++) {
            data[i] = vector.get(i);
        }
        this.F = new FCEMFactorVector(F.size(),F, data);
        System.out.println("F:"+this.F);
    }

    @Override
    public void addData(SystemMeta systemMeta) {

    }

}
