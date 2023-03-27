package org.SRM.SystematicReviewMethodology.AHP;

import org.SRM.MathTools.Matrix;
import org.SRM.MathTools.Vector;
import org.SRM.SystematicReviewMethodology.SystematicReviewMethodology;

import java.util.List;
import java.util.Objects;

public class AnalyticHierarchyProcess extends SystematicReviewMethodology {

    AHPSystemData ahpSystemData;

    @Override
    public Vector review() {

        return null;
    }

    public void addConnect(String parent, List<String> children, double[][] decideMat){
        AHPNode fNode = getNode(parent);
        if(fNode == null) return;
        List<AHPNode> ahpNodes = fNode.setChildren(children, new Matrix(decideMat.length, decideMat.length, decideMat));
        ahpNodes.forEach(n -> ahpSystemData.addData(n));
    }

    public AHPNode getNode(String name){
        for (AHPNode node : ahpSystemData.getNodes()) {
            if(Objects.equals(node.getName(), name)) return node;
        }
        return null;
    }

    public AnalyticHierarchyProcess(String rootName){
        AHPNode root = new AHPNode(rootName);
        ahpSystemData = new AHPSystemData(this, root);
        root.dataIns = ahpSystemData;
    }

}
