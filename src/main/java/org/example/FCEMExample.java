package org.example;

import org.SRM.MathTools.Vector;
import org.SRM.SystematicReviewMethodology.FCEM.FuzzyComprehensiveEvaluationMethod;

import java.util.List;

public class FCEMExample {
    public static void main(String[] args) {
        List<String> F = List.of("价格","质量","外观");
        List<String> E = List.of("低","中","高");
        double[] E_data = new double[]{0.3, 0.2, 0.1};
        double[][] decideM = new double[][]{
                {1,1.0/3,2},
                {3,1,5},
                {1.0/2,1.0/5,1}
        };
        double[][][] R = new double[][][]{
                {{2, 1, 2}, {2, 4, 3}, {1, 0, 0}},
                {{2, 4, 3}, {1, 0, 0}, {2, 1, 2}},
                {{2, 1, 3}, {2, 3, 2}, {1, 1, 0}}
        };
        FuzzyComprehensiveEvaluationMethod fcem = new FuzzyComprehensiveEvaluationMethod(F, E, E_data,R, decideM);
        Vector review = fcem.review();
        System.out.println(review);

    }
}
