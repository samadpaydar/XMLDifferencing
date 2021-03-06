package deltaxmltest;

import com.deltaxml.api.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.transform.stream.*;
import java.util.Date;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.io.PrintStream;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Test {
    public Test() {
    }

    public static void main(String[] args) {
        try {
            final int from = Integer.parseInt(args[0]);
            final int to = Integer.parseInt(args[1]);
            final int step = Integer.parseInt(args[2]);
            final int numberOfTests = Integer.parseInt(args[3]);
            PrintStream totalLog = new PrintStream(new FileOutputStream("testcases\\totalLog.txt"));
            totalLog.println("Start: " + DateFormat.getDateTimeInstance().format (new Date()));
            for(int testcase=from; testcase<=to; testcase+=step) {
                System.out.print("testcase # " + testcase);
                String source = "testcases\\" + testcase + "\\File1.xml";
                String dest = "testcases\\" + testcase + "\\File2.xml";
                String resultFile = "testcases\\" + testcase + "\\result.xml";
                PrintStream log = new PrintStream(new FileOutputStream("testcases\\" + testcase+ "\\log.txt"));
                long results[] = new long[numberOfTests];
                for(int i=0; i < numberOfTests; i++) {
                    System.gc();
                    XMLComparatorFactory xcf = XMLComparatorFactory.newInstance();
                    XMLComparator xc = xcf.newXMLComparator();
                    xc.setFeature("http://deltaxml.com/api/feature/isFullDelta", false);
                    long start = System.currentTimeMillis();
                    xc.compare(new StreamSource(new File(source)),
                               new StreamSource(new File(dest)),
                               new StreamResult(new File(resultFile)));

                    long end = System.currentTimeMillis();
                    long spentTime = end - start;
                    results[i] = spentTime;
                    log.println(spentTime);
                    xc = null;
                    System.gc();
                }
                long sum = 0;
                for(int i =0; i< numberOfTests; i++) {
                    sum += results[i];
                }
                float avg = (float)sum / (float)numberOfTests;
                System.out.println("avg: "+avg);
                log.println("avg: "+avg);
                log.close();
                totalLog.println(testcase +" , "+avg);
            }
            totalLog.println("Finish: " + DateFormat.getDateTimeInstance().format (new Date()));
            totalLog.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
