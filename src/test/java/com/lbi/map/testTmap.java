/**************************************
 * Copyright (C), Navinfo
 * Package: com.lbi.map
 * Author: liumingkai05559
 * Date: Created in 2018/3/29 10:46
 **************************************/
package com.lbi.map;

import com.lbi.model.Tile;
import com.lbi.util.TMap;
import com.lbi.util.TileSystem;
import com.vividsolutions.jts.geom.Coordinate;
import org.junit.Test;

/*************************************
 * Class Name: testTmap
 * Description:〈TMap测试〉
 * @author liumingkai
 * @create 2018/3/29
 * @since 1.0.0
 ************************************/
public class testTmap {
    @Test
    public void test(){
        double x1=114;
        double y1=30;
        double x2=114.00001;
        double y2=30.000001;
        Coordinate pt1=new Coordinate(x1,y1);
        Coordinate pt2=new Coordinate(x2,y1);
        double distance= TMap.getDistance(pt1,pt2);
        System.out.println(distance);
    }
    @Test
    public void getTile(){
        Coordinate pt=new Coordinate(114.33155953,30.47080248);
        Tile tile= TileSystem.LatLongToTile(pt,15);
        System.out.println(tile.toString());
    }
}
