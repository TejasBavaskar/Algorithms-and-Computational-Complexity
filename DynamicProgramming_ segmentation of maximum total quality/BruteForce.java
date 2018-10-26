package dynamicprogramming;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.MatchResult;
import javafx.print.Collation;

public class BruteForce {
    static int maxQ=-9999999;
    static String maxS;
    public static StringBuilder sb = new StringBuilder();
   
    public static HashMap<Integer, String> hmap = new HashMap<Integer, String>();
    public static void main(String []args){
        
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter String: ");
        String str = scan.next();
        int  start_time = (int) System.nanoTime();
       printPattern(str);
       long end_time = System.nanoTime();
       System.out.println("Time: "+ (end_time - start_time)*0.001+" microsec");

      
       Set set = hmap.entrySet();
      Iterator iterator = set.iterator();
      while(iterator.hasNext()) {
         Map.Entry mentry = (Map.Entry)iterator.next();
         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
         System.out.println(mentry.getValue().toString());
         
          System.out.println("Quality "+ maxQ + " String "+ maxS);
      }
    }



    private static int getQuality(String tempstr) {
        int mresult = 0 ;
        
       String[] occurence = tempstr.split(" ");
        
        System.out.println("************************************************************");
        System.out.println("New String  "+ tempstr);
       for(int i=0;i<occurence.length;i++)
       {
         //  System.out.println(occurence[i]);
           mresult = mresult + dictionary(occurence[i].trim());
           
           
       }
       hmap.put(mresult, tempstr);
       // System.out.println("***********************"+occurence[occurence.length-1]+"hi");
        //mresult = dictionary(occurence[occurence.length-1].trim());
       
            
        
        System.out.println("Result for "+ tempstr+": "+mresult);
        
        
        return mresult;
        
    }

    private static int dictionary(String tempstr) {
        System.out.println("In dictionary: "+ tempstr);
        String []dict = {"I","a","am","ace","i","meet","me","at","eight","the",
           "youth","event","he","you","out","my","how","are","u","to","hi","good","morning","put","go"};
        //String []dict = {"how","are","u"};
       int result = -1;
       for(int i=0;i<dict.length;i++)
       {
           if(dict[i].equals(tempstr))
           {
               result = 1;
               System.out.println("Returning for: "+tempstr+":: "+ result);
               return result;
           }
       }
       System.out.println("Returning for: "+ result);
       return result;
    }
    static int count = 0;
    private static void printPatternUtil(String str, char buf[], int i, int j, int n) {
        count++;
        int result=0;
       if(i == n)
        {
            buf[j] = '\0';
            //System.out.println(buf);
          //  System.out.println("STOP******");

            String bufstring = new String(buf);
            
            result = getQuality(bufstring);
               
            if(result > maxQ)
            {
                maxQ = result;
                maxS = bufstring;
            }
            return;
        }
 
        // Either put the character
       
            buf[j] = str.charAt(i);
       
            printPatternUtil(str, buf, i+1, j+1, n);
 
        // Or put a space followed by next character
        buf[j] = ' ';
        
            buf[j+1] = str.charAt(i);
        
        
 
        printPatternUtil(str, buf, i+1, j+2, n);
    }
    static void printPattern(String str)
    {
        int len = str.length();
 
        // Buffer to hold the string containing spaces
        // 2n-1 characters and 1 string terminator
        char[] buf = new char[2*len];
 
        // Copy the first character as it is, since it will be always
        // at first position
        buf[0] = str.charAt(0);
        printPatternUtil(str, buf, 1, 1, len);
    }
    
}
