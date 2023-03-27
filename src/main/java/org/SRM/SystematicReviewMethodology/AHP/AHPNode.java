package org.SRM.SystematicReviewMethodology.AHP;

import org.SRM.MathTools.Matrix;
import org.SRM.MathTools.Vector;
import org.SRM.SystematicReviewMethodology.SystemMeta;

import java.util.*;

import static java.lang.Math.pow;

public class AHPNode implements SystemMeta {

    static class AHPNodeConnection {
        Matrix A;
        Vector weight_0;

        AHPNodeConnection (Matrix a){
            A = a;
            int n = a.getCol();
            System.out.println(a.toString());
            double[] weight = new double[n];
            for (int i = 0; i < n; i++) {
                weight[i] = 1;
                for (int j = 0; j < n; j++) {
                    weight[i] *= a.get(i+1,j+1);
                }
                weight[i] = pow(weight[i], 1.0 / n);
            }
            this.weight_0 = new Vector(n, weight);
            System.out.println(weight_0);
            this.weight_0.modify();
            System.out.println(weight_0);
        }

        public boolean checkConsistency(boolean useRI){
            Matrix multiply = A.multiply(weight_0);
            Vector vector = (Vector) multiply;
            vector.pointDivide(weight_0);
            double lambda = vector.getAvenge();
            int n = this.weight_0.getSize();
            double CI = (lambda - n) / (n - 1);
            System.out.println("CI: " + CI);
            if(!useRI) return CI < 0.1;
            double CR = CI / getRI(n);
            System.out.println("CR: " + CR);
            return CR < 0.1;
        }

        private double getRI(int n) {
            Map<Integer, Double> RImap = new HashMap<>();
            RImap.put(1,0.0);
            RImap.put(2,0.0);
            RImap.put(3,0.52);
            RImap.put(4,0.89);
            RImap.put(5,1.12);
            RImap.put(6,1.26);
            RImap.put(7,1.36);
            RImap.put(8,1.41);
            RImap.put(9,1.46);
            RImap.put(10,1.49);
            RImap.put(11,1.52);
            RImap.put(12,1.54);
            RImap.put(13,1.56);
            RImap.put(14,1.58);
            return RImap.get(n);
        }
    }

    public StringBuilder showAll(StringBuilder sb){
        if(sb == null) sb = new StringBuilder();
        sb.append("\n");
        sb.append("Level: ").append(level).append("\n");
        sb.append("name: ").append(name).append("\n");
        sb.append("w: ").append(getWeight()).append("\n");
        sb.append("\n");
        for (AHPNode child : children) {
            sb = child.showAll(sb);
        }
        return sb;
    }
    AHPSystemData dataIns;
    private final String name;
    private final List<AHPNode> parent;
    private final int level;
    List<AHPNode> children = new ArrayList<>();
    AHPNodeConnection ahpNodeConnection;
    public double getWeight() {
        if(level == 0) return 1.0;
        double w = 0;
        assert parent != null;
        for (AHPNode ahpNode : parent) {
            int i = ahpNode.children.indexOf(this);
            w += ahpNode.getWeight() * ahpNode.ahpNodeConnection.weight_0.get(i);
            //System.out.println(this.name+": "+ahpNode.getWeight()+" X "+ahpNode.ahpNodeConnection.weight_0.get(i));
        }

        return w;
    }
    List<AHPNode> setChildren(List<String> names, Matrix decideMatrix){
        AHPNodeConnection ahpNodeConnection1 = new AHPNodeConnection(decideMatrix);
        if (!ahpNodeConnection1.checkConsistency(true)) {
            System.out.println("Fail to check consistency");
        }
        List<AHPNode> children = new ArrayList<>();
        for (String name : names) {

            AHPNode child = dataIns.AHPIns.getNode(name);
            if(child == null) child = new AHPNode(this, name, dataIns);
            if(!child.parent.contains(this))
                child.parent.add(this);
            children.add(child);
        }
        this.children = children;
        ahpNodeConnection = ahpNodeConnection1;

        return children;
    }

    public List<AHPNode> getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    AHPNode(String name) {
        parent = null;
        this.name = name;
        level = 0;
    }
    private AHPNode(List<AHPNode> parent, String name, AHPSystemData dataIns){
        this.dataIns = dataIns;
        this.parent = parent;
        this.name = name;
        level = parent.get(0).level + 1;
    }
    private AHPNode(AHPNode parent, String name, AHPSystemData dataIns){
        this.dataIns = dataIns;
        ArrayList<AHPNode> parent1 = new ArrayList<>();
        parent1.add(parent);
        this.parent = parent1;
        this.name = name;
        level = parent.level + 1;
    }
}
