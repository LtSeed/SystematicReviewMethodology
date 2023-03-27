package org.SRM.SystematicReviewMethodology.AHP;

import lombok.Getter;
import org.SRM.SystematicReviewMethodology.SystemData;
import org.SRM.SystematicReviewMethodology.SystemMeta;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AHPSystemData implements SystemData {
    final AnalyticHierarchyProcess AHPIns;
    private final AHPNode root;
    private final List<AHPNode> nodes = new ArrayList<>();

    @Override
    public void addData(SystemMeta systemMeta) {
        if(!(systemMeta instanceof AHPNode node)) return;
        nodes.add(node);
    }

    AHPSystemData(AnalyticHierarchyProcess ahpIns, AHPNode root){
        AHPIns = ahpIns;
        this.root = root;
        nodes.add(root);
    }

}
