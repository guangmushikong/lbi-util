package com.lbi.util;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.util.GeometricShapeFactory;

/**
 * Created by lmk on 2017/8/10.
 */
public class GeoUtils {
    public static final GeometryFactory GEO_FACTORY=new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING),4326);

    public static MultiPolygon transMultiPolygon(MultiPolygon geom,int transType){
        Polygon[] polygons=new Polygon[geom.getNumGeometries()];
        for(int i=0;i<geom.getNumGeometries();i++){
            Polygon polygon=(Polygon)geom.getGeometryN(i);
            polygons[i]=transPolygon(polygon,transType);
        }
        return GEO_FACTORY.createMultiPolygon(polygons);
    }
    public static Polygon transPolygon(Polygon geom,int transType){
        LineString exteriorString=geom.getExteriorRing();
        exteriorString=transLineString(exteriorString,transType);
        LinearRing exteriorRing=GEO_FACTORY.createLinearRing(exteriorString.getCoordinateSequence());
        LinearRing[] interiorRings=new LinearRing[geom.getNumInteriorRing()];
        for(int i=0;i<geom.getNumInteriorRing();i++){
            LineString interiorString=geom.getInteriorRingN(i);
            interiorString=transLineString(interiorString,transType);
            interiorRings[i]=GEO_FACTORY.createLinearRing(interiorString.getCoordinateSequence());
        }
        return GEO_FACTORY.createPolygon(exteriorRing,interiorRings);
    }
    public static LineString transLineString(LineString from,int transType){
        Coordinate[] coords=from.getCoordinates();
        Coordinate[] coords2=new Coordinate[coords.length];
        for(int i=0;i<coords.length;i++){
            coords2[i]=transCoord(coords[i],transType);
        }
        return GEO_FACTORY.createLineString(coords2);
    }
    public static Coordinate transCoord(Coordinate coord,int transType){
        if(transType==1){
            coord=new Coordinate(coord.x/3600,coord.y/3600);
        }else if(transType==2){//Wgs84ToGcj02
            coord= CoordTransform.Wgs84ToGcj02(coord);
            System.out.println();
        }else if(transType==3){//Bd09ToGcj02
            coord= CoordTransform.Bd09ToGcj02(coord);
        }
        return coord;
    }
    /**
     * 创建圆形区域
     * @param cenPT 中心点
     * @param radius 半径
     * @return 圆形多边形
     */
    public static  Polygon createCircle(Coordinate cenPT,int radius){
        Coordinate sw= TMap.getLngLatByOffset(cenPT, -radius, -radius);
        Coordinate ne=TMap.getLngLatByOffset(cenPT, radius, radius);
        GeometricShapeFactory shapeFactory=new GeometricShapeFactory();
        shapeFactory.setNumPoints(30);
        shapeFactory.setCentre(cenPT);
        shapeFactory.setWidth(ne.x-sw.x);
        shapeFactory.setHeight(ne.y-sw.y);
        return shapeFactory.createCircle();
    }
}
