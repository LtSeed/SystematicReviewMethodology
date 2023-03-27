package org.example;

import org.SRM.SystematicReviewMethodology.*;
import org.SRM.SystematicReviewMethodology.AHP.AnalyticHierarchyProcess;

import java.util.ArrayList;
import java.util.List;

public class AHPExample {
    public static void main(String[] args) {
        AnalyticHierarchyProcess ahp = new AnalyticHierarchyProcess("综合效益");
        List<String> b = new ArrayList<>();
        b.add("经济效益");
        b.add("环境效益");
        b.add("社会效益");
        ahp.addConnect("综合效益",b ,
                new double[][]{
                        {1,3,5},
                        {1.0/3,1,3},
                        {1.0/5,1.0/3,1}});
        List<String> c = new ArrayList<>();
        c.add("m1");
        c.add("m2");
        c.add("m3");
        c.add("m4");
        c.add("m5");
        ahp.addConnect("经济效益",c,
                new double[][]{
                        {1,1.0/5,1.0/7,2,5},
                        {5,1,1.0/2,6,8},
                        {7,2,1,7,9},
                        {1.0/2,1.0/6,1.0/7,1,4},
                        {1.0/5,1.0/8,1.0/9,1.0/4,1}});
        ahp.addConnect("环境效益",c,
                new double[][]{
                        {1,1.0/3,2,1.0/5,3},
                        {3,1,4,1.0/7,7},
                        {1.0/2,1.0/4,1,1.0/9,2},
                        {5,7,9,1,9},
                        {1.0/3,1.0/7,1.0/2,1.0/9,1}});
        ahp.addConnect("社会效益",c,
                new double[][]{
                        {1,2,4,1.0/9,1.0/2},
                        {1.0/2,1,3,1.0/6,1.0/3},
                        {1.0/4,1.0/3,1,1.0/9,1.0/7},
                        {9,6,9,1,3},
                        {2,3,7,1.0/3,1.0/7}});
        for (String s : b) {
            System.out.println(s+" "+ahp.getNode(s).getWeight());
        }
        for (String s : c) {
            System.out.println(s+" "+ahp.getNode(s).getWeight());
        }
        //System.out.println(ahp.getNode("综合效益").showAll(null));
    }
}