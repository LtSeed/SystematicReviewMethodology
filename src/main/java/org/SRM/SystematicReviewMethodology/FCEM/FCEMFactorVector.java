package org.SRM.SystematicReviewMethodology.FCEM;

import org.SRM.MathTools.Vector;
import org.SRM.SystematicReviewMethodology.SystemMeta;

import java.util.List;

public class FCEMFactorVector extends Vector implements SystemMeta {

    List<String> factor_name;

    public FCEMFactorVector(int size,List<String> factor_name, double[] data) {
        super(size, data);
        this.factor_name = factor_name;
        this.modify();
    }
}
