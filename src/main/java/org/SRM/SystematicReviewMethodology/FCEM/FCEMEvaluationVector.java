package org.SRM.SystematicReviewMethodology.FCEM;

import org.SRM.MathTools.Vector;
import org.SRM.SystematicReviewMethodology.SystemMeta;

import java.util.List;

public class FCEMEvaluationVector extends Vector implements SystemMeta {

    List<String> evaluation_name;

    public FCEMEvaluationVector(int size,List<String> evaluation_name, double[] data) {
        super(size, data);
        this.evaluation_name = evaluation_name;
        this.modify();
    }
}
