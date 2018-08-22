package com.lbi.map;

import com.lbi.util.CoordTransform;
import com.lbi.util.TMap;
import com.vividsolutions.jts.geom.Coordinate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testCoordTransform {
    @Test
    public void test(){
        Coordinate pt=new Coordinate(121.526159,31.221569);
        Coordinate pt2= CoordTransform.Bd09ToGcj02(pt);
        System.out.println(pt2.toString());
    }
    @Test
    public void DCS(){
        Coordinate pt=new Coordinate(114.493801428,30.449978687);
        Coordinate pt2=CoordTransform.Wgs84ToGcj02(pt);
        System.out.println(pt2.toString());
    }
    @Test
    public void disOffset(){
        Coordinate gcj02PT=new Coordinate(114.49910725928619, 30.447492625644294);
        Coordinate avgPT=new Coordinate(114.49910033865389,30.447498455565118);
        Coordinate pt1=new Coordinate(114.499109700521,30.447494032118);
        Coordinate pt2=new Coordinate(114.499109971788,30.4474943033854);
        Coordinate pt3=new Coordinate(114.499109971788,30.4474937608507);
        System.out.println("原始坐标(114.493801428,30.449978687)");
        System.out.println("网络偏移坐标(114.49910725928619,30.447492625644294)");
        System.out.println("平均DCS偏移坐标(114.49910033865389,30.447498455565118)");
        System.out.println("DCS偏移1(114.499109700521,30.447494032118)");
        System.out.println("DCS偏移2(114.499109971788,30.4474943033854)");
        System.out.println("DCS偏移3(114.499109971788,30.4474937608507)");
        System.out.println("---偏移坐标距离差---");
        System.out.println("网络偏移-DCS偏移1:"+TMap.getDistance(gcj02PT,pt1));
        System.out.println("网络偏移-DCS偏移2:"+TMap.getDistance(gcj02PT,pt2));
        System.out.println("网络偏移-DCS偏移3:"+TMap.getDistance(gcj02PT,pt3));

        System.out.println("平均DCS偏移-DCS偏移1:"+TMap.getDistance(avgPT,pt1));
        System.out.println("平均DCS偏移-DCS偏移2:"+TMap.getDistance(avgPT,pt2));
        System.out.println("平均DCS偏移-DCS偏移3:"+ TMap.getDistance(avgPT,pt3));

        System.out.println("DCS偏移1-DCS偏移2:"+TMap.getDistance(pt1,pt2));
        System.out.println("DCS偏移1-DCS偏移3:"+TMap.getDistance(pt1,pt3));

    }
    @Test
    public void avg(){
        List<Stat> xList=new ArrayList<Stat>();
        xList.add(new Stat(114.499109700521,355));
        xList.add(new Stat(114.499109971788,167));
        xList.add(new Stat(114.499109429253,12));
        xList.add(new Stat(114.499110243056,26));
        xList.add(new Stat(114.493801540799,1));
        List<Stat> yList=new ArrayList<Stat>();
        yList.add(new Stat(30.447494032118,365));
        yList.add(new Stat(30.4474943033854,85));
        yList.add(new Stat(30.4474945746528,6));
        yList.add(new Stat(30.4474937608507,100));
        yList.add(new Stat(30.4474934895833,4));
        yList.add(new Stat(30.4499785698785,1));
        double xVal=0;
        int xTotal=0;
        for(Stat x:xList){
            xVal+=x.val*x.count;
            xTotal+=x.count;
        }
        double avgXVal=xVal/xTotal;
        System.out.println("X:total:"+xTotal+",avgVal:"+avgXVal);
        double yVal=0;
        int yTotal=0;
        for(Stat y:yList){
            yVal+=y.val*y.count;
            yTotal+=y.count;
        }
        double avgYVal=yVal/yTotal;
        System.out.println("Y:total:"+yTotal+",avgVal:"+avgYVal);
    }

    public class Stat{
        public Double val;
        public int count;
        Stat(double val,int cnt){
            this.val=val;
            this.count=cnt;
        }
    }

}
