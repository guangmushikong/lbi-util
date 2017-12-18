package com.lbi.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
@Slf4j
public class FileUtils {
    public static long totalRecord(String fileName){
        long star=System.currentTimeMillis();
        log.info("file job start");
        long total=0;
        try{
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
            InputStreamReader isr=new InputStreamReader(bis);
            BufferedReader in = new BufferedReader(isr,100 * 1024 * 1024);//10M 缓存
            long n=0;
            while (in.ready()) {
                n++;
                in.readLine();
            }
            bis.close();
            isr.close();
            in.close();
            total= n;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        long end=System.currentTimeMillis();
        log.info("file job finish,cost time:"+(end-star)/1000+"s");
        return total;
    }
    public static void splitFile(
            String inputFileName,
            String outputFileName,
            long total,
            long splitRecordSize,
            String charsetName){
        long star=System.currentTimeMillis();
        log.info("split job start");
        try{
            long fileNum=total/splitRecordSize;

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(inputFileName)));
            InputStreamReader isr=new InputStreamReader(bis,charsetName);
            BufferedReader in = new BufferedReader(isr,100 * 1024 * 1024);//10M 缓存
            FileWriter fw = null;
            long n=0;
            String line=null;
            final long batchSize=100000;
            for(int i=0;i<=fileNum;i++){
                long startIndex=i*splitRecordSize;
                long endIndex=(i+1)*splitRecordSize;
                for(int j=0;j<splitRecordSize;j++){
                    if(!in.ready())break;
                    n++;
                    if(n%batchSize==0)System.out.println(n+"|"+line);
                    line = in.readLine();
                    if(j==0){
                        String outputFile=outputFileName.replace(".csv",System.currentTimeMillis()+".csv");
                        fw = new FileWriter(outputFile);
                        fw.append(line + "\n");
                        log.info(startIndex+"-"+endIndex+","+outputFile);
                    }else if(j==(splitRecordSize-1)){
                        fw.append(line + "\n");
                        fw.flush();
                        fw.close();
                    }else{
                        fw.append(line + "\n");
                    }
                }

            }

            bis.close();
            isr.close();
            in.close();
            fw.flush();
            fw.close();
            log.info("total:"+n);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        long end=System.currentTimeMillis();
        log.info("split job finish,cost time:"+(end-star)/1000+"s");
    }
}
