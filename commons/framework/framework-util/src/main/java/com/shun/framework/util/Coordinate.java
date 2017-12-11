package com.shun.framework.util;

import com.shun.framework.Pair;

/**
 * User: mew <p />
 * Time: 17/11/6 16:38  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Coordinate {

    private static final double RE = 6371.0D;

    public Coordinate() {
    }

    public static double getDistance(Pair<Double, Double> begin, Pair<Double, Double> end) {
        Double begin_lon = toRadians(((Double) begin.getLeft()).doubleValue());
        Double begin_lat = toRadians(((Double) begin.getRight()).doubleValue());
        Double end_lon = toRadians(((Double) end.getLeft()).doubleValue());
        Double end_lat = toRadians(((Double) end.getRight()).doubleValue());
        Double distance = 6371.0D * Math.acos(Math.cos(begin_lat.doubleValue()) * Math.cos(end_lat.doubleValue()) *
                Math.cos(begin_lon.doubleValue() - end_lon.doubleValue()) + Math.sin(begin_lat.doubleValue()) * Math
                .sin(end_lat.doubleValue()));
        return distance.doubleValue();
    }

    private static double toRadians(double degree) {
        return degree * 3.141592653589793D / 180.0D;
    }

    private static double haversin(double theta) {
        double result = Math.sin(theta / 2.0D);
        return result * result;
    }

}
