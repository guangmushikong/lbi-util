package com.lbi.util;

import com.lbi.map.TMap;
import com.lbi.map.Tile;
import com.lbi.map.TileSystem;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import org.junit.Test;
import com.vividsolutions.jts.geom.Geometry;

public class testGeoUtils {
    @Test
    public void test(){
        String wkt="POLYGON((116.43368 39.91194,116.43368 39.91179,116.43396 39.91179,116.43396 39.91145,116.43368 39.91145,116.43368 39.91127,116.4344 39.91127,116.4344 39.91195,116.43368 39.91194))";
        WKTReader reader=new WKTReader();
        try{
            Polygon geom=(Polygon)reader.read(wkt);
            Polygon polygon=GeoUtils.transPolygon(geom,3);
            System.out.println(polygon.toText());
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
    @Test
    public void geohash(){
        GeoHash geohash = new GeoHash();
        String s = geohash.encode(39.9069671024828, 116.260378447603);
        System.out.println(s);

    }
    @Test
    public void distance(){
        Coordinate pt1=new Coordinate(116.260378447603,39.9069671024828);
        Coordinate pt2=new Coordinate(116.43368,39.91194);
        double dis=TMap.getDistance(pt1,pt2);
        System.out.println("dis:"+dis);

    }
    @Test
    public void 创建圆(){
        //创建圆形区域
        Coordinate cenPT=new Coordinate(116.43368,39.91194);
        Polygon polygon=createCircle(cenPT,100);
        System.out.println(polygon.toText());

    }
    @Test
    public void 瓦片(){
        Tile tile=new Tile(1659,1256,11);
        Coordinate pt=TileSystem.TileXYToBounds(tile).centre();
        System.out.println(tile.toString());
        System.out.println(pt.toString());

    }
    private Polygon createCircle(Coordinate cenPT,int radius){
        Coordinate sw=TMap.getLngLatByOffset(cenPT, -radius, -radius);
        Coordinate ne=TMap.getLngLatByOffset(cenPT, radius, radius);
        GeometricShapeFactory shapeFactory=new GeometricShapeFactory();
        shapeFactory.setNumPoints(30);
        shapeFactory.setCentre(cenPT);
        shapeFactory.setWidth(ne.x-sw.x);
        shapeFactory.setHeight(ne.y-sw.y);
        return shapeFactory.createCircle();
    }
}
