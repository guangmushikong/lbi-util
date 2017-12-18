package com.lbi.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.triangulate.ConformingDelaunayTriangulationBuilder;
import org.junit.Test;

public class testPolygon {
    @Test
    public void 三角网(){
        Coordinate[] coordinates = new Coordinate[]{
                new Coordinate(116.47931,39.97846),
                new Coordinate(116.45819,39.8837199),
                new Coordinate(116.47007,39.9925799),
                new Coordinate(116.6599,40.11215),
                new Coordinate(116.46552,39.95028),
                new Coordinate(116.47563,40.05835),
                new Coordinate(116.47666,39.85863),
                new Coordinate(116.46282,39.9454),
                new Coordinate(116.45628,39.99106)};
        GeometryFactory gf = new GeometryFactory();
        MultiPoint mp = gf.createMultiPoint(coordinates);
        ConformingDelaunayTriangulationBuilder builder = new ConformingDelaunayTriangulationBuilder();
        builder.setSites(mp);
        //实际为GeometryCollection（组成的geometry紧密相连）
        Geometry ts = builder.getTriangles(gf);
        System.out.println(ts.toText());
        //以0的距离进行缓冲（因为各多边形两两共边），生成一个多边形
        //此时则将点云构造成了多边形
        Geometry union = ts.buffer(0);
        String text = union.toText();
        System.out.println(text);
    }
}
