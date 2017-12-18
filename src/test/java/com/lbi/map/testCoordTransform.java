package com.lbi.map;

import com.vividsolutions.jts.geom.Coordinate;
import org.junit.Test;

public class testCoordTransform {
    @Test
    public void test(){
        Coordinate pt=new Coordinate(121.526159,31.221569);
        Coordinate pt2=CoordTransform.Bd09ToGcj02(pt);
        System.out.println(pt2.toString());
    }

}
