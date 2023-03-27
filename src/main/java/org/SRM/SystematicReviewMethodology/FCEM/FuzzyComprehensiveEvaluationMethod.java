package org.SRM.SystematicReviewMethodology.FCEM;

import org.SRM.MathTools.Matrix;
import org.SRM.MathTools.Vector;
import org.SRM.SystematicReviewMethodology.SystematicReviewMethodology;

import java.util.List;

public class FuzzyComprehensiveEvaluationMethod extends SystematicReviewMethodology {

    FCEMSystemData data;

    @Override
    public Vector review() {
        Vector result = new Vector(data.F.getSize(),new double[data.F.getSize()]);
        int i = 0;
        for (FCEMMembershipMatrix matrix : data.R) {
            Vector vector = (Vector) matrix.crossMultiply(data.F);
            System.out.println("S:"+vector);
            vector.crossMultiply(data.E);
            System.out.println("S:"+vector);
            Matrix matrix1 = vector.crossMultiply(data.E);
            System.out.println("miu:"+matrix1);
            result.set(i++, matrix1.get(1,1));
        }
        return result;
    }

    public FuzzyComprehensiveEvaluationMethod(List<String> F, List<String> E, double[] E_data, double[][][] R, double[][] decideMat) {
        data = new FCEMSystemData(F,E,E_data,R,decideMat);
    }
}
