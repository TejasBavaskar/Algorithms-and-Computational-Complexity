package dynamicprogramming;

import java.util.Scanner;



public class DynamicProgramming {
    static StringBuilder sb = new StringBuilder();
   public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       System.out.println("Enter String: ");
       String str = scan.nextLine();
       str = str.replaceAll(" ", "");
       int length = str.length();
       int qualityMatrix[][] = new int[length][length];
       int tracematrix[][] = new int[length][length];
       int  start_time = (int) System.nanoTime();
       computeMatrix(qualityMatrix,tracematrix,str);

       traceStringSegmentation(tracematrix,str,0,str.length()-1);
       long end_time = System.nanoTime();
       System.out.println("Time: "+ (end_time - start_time)*0.001+" microsec");
       
       for(int i=0;i<str.length();i++)
       {
           for(int j=0;j<str.length();j++)
       {
           System.out.print(qualityMatrix[i][j]+"\t");
           
       }
           System.out.println("\n");
       }
       System.out.println("traceMatrix:");
       for(int i=0;i<str.length();i++)
       {
           for(int j=0;j<str.length();j++)
       {
           System.out.print(tracematrix[i][j]+"\t");
           
       }
           System.out.println("\n");
       }
       
       System.out.println("Final string: "+sb);
       

   }
   
   public static int  dictionary(String str){
       String []dict = {"I","a","am","ace","i","meet","me","at","eight","the",
           "youth","event","he","you","out","my","how","are","u","go","to"};
       int result = -1;
       for(int i=0;i<dict.length;i++)
       {
           if(dict[i].equals(str))
           {
               result = 1;
               return result;
           }
       }
       return result;
   }
   
   public static void computeMatrix(int qualityMatrix[][],int tracematrix[][],String str){
       
       String substring = null;
       int maxindex = 0;
       int resultMatch;
       int count =0;
       for(int i=0;i<str.length();i++)  // diagonal elements in matrix
       {
           substring = str.substring(i, i+1);
          // System.out.println("Substring in compute: "+ substring);
          // System.out.println("Substring:"+substring);
           resultMatch = dictionary(substring);
           qualityMatrix[i][i] = resultMatch;
           tracematrix[i][i] = i;   
                      
       }
       //*********remaining elements calculations
      /* for(int i=0;i<str.length();i++)
       {
           for(int j=0;j<str.length();j++)
       {
           System.out.print(qualityMatrix[i][j]);
           
       }
           System.out.println("\n");
       }*/
       int calc=1;
       for(int x=0;x<str.length();x++)
       {
           count = x+1;
           if(count<str.length())
           {
            for(int i=0;i<(str.length()-calc);i++)
            {
                
               // System.out.println("i:"+i+"count:"+count+"calc:"+calc);
                
                //SSystem.out.println("Substring i count:"+ str.substring(i, count+1));
                resultMatch = dictionary(str.substring(i, count+1));
                if(resultMatch == 1)
                {
                    qualityMatrix[i][count] = resultMatch;
                    tracematrix[i][count] = count;
                }
                else
                {
                     int max = -1;
                     maxindex = count;
                     int maxArr[] = new int[str.length()];
                    for(int k=i;k<count;k++)
                    {
                        
                        
                        maxArr[k] = qualityMatrix[i][k] + qualityMatrix[k+1][count]; 
                        if(maxArr[k] > max)
                        {
                            max = maxArr[k];
                            maxindex = k;
                        }

                    }
                    qualityMatrix[i][count] = max;
                    tracematrix[i][count] = maxindex;
                }
                
                ++count;
            }
            calc++;
           }
           
       }
       
       
       
  
   }

    public static void traceStringSegmentation(int[][] tracematrix, String str,int rowstart, int rowend) {
      
       /* if(rowend < str.length() &&rowstart <str.length()-1 )
        {
            String str1 = null;
            int  corner_element =  tracematrix[rowstart][rowend];
            int rowvalue = tracematrix[rowstart][corner_element];
            if(corner_element == rowvalue)
            {
                str1 = getString(rowvalue,str,rowstart,rowend);
                System.out.println("*****Str:"+str1);
            }
            rowstart = corner_element + 1;
            System.out.println("rowstart: "+rowstart);
             if(rowstart == rowend)
            {   
                System.out.println("In fun");
                sb.append(str.charAt(str.length()-1));
                return;
            } 
            traceStringSegmentation(tracematrix, str, rowstart, rowend);
        }*/
        
        int split = tracematrix[rowstart][rowend];
        if(split == rowend)
        {
            getCompleteString(str,rowstart,rowend);
            return;
        }
        traceStringSegmentation(tracematrix, str, rowstart,split);
        traceStringSegmentation(tracematrix, str, split+1,rowend);
        
        

    }

    /*private static String getString(int rowvalue,String str, int rowstart, int rowend) {
       /* if(rowstart == rowend)
        {   
            System.out.println("In fun");
            sb.append(str.charAt(str.length()-1));
            return sb.toString();
        }    
        String tempstr = str.substring(rowstart, rowvalue+1);
        tempstr = tempstr.concat(" ");
        sb.append(tempstr);
        
        return tempstr;
        
        
    }**/

    private static void getCompleteString(String str, int rowstart, int rowend) {
        String tempstr = str.substring(rowstart, rowend+1);
        tempstr = tempstr.concat(" ");
        sb.append(tempstr);
    
    }
    
   
  
    
}
