package org.SRM.SystematicReviewMethodology.FCEM;

import org.SRM.MathTools.Matrix;
import org.SRM.SystematicReviewMethodology.SystemMeta;

public class FCEMMembershipMatrix extends Matrix implements SystemMeta {


    FCEMMembershipMatrix(int row, int col, double[][] Data) {
        super(row, col, Data);
        for (int i = 0; i < col; i++) {
            double cnt = 0;
            for (int j = 0; j < row; j++) {
                cnt += Data[j][i];
            }
            for (int j = 0; j < row; j++) {
                Data[j][i] /= cnt;
            }
        }
    }
}
