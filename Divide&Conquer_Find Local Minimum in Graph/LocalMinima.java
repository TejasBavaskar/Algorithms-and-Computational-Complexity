package localminima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class LocalMinima {
    static int x1,y1,flag,comp;
   public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       ArrayList<Integer> list = new ArrayList<>();
        
       System.out.print("Enter n: ");
       int n = scan.nextInt();
       
       int arr[][] = new int[n][n];
      /* Random rand = new Random();
       for(int i=0;i<(n*n*3);i++)
       {
           list.add(rand.nextInt(200)+1);
       }
       Collections.shuffle(list);   //to get the unique elements
       //Assign node values************
       int cnt = 0;
       for(int i=0;i<n;i++)
       {
           for(int j=0;j<n;j++)
           {
               arr[i][j] = list.get(cnt);
               cnt++;
           }
       }*/
       //manual input****************
      //-------------------------------
     /* int temp[] = new int[n*n];
      for(int i=n*n,j=0;i>0;i--,j++)
      {
          temp[j] = i;
      }
      */
      for(int i=0;i<n;i++)
       {
           for(int j=0;j<n;j++)
           {
               arr[i][j] = scan.nextInt();
              // flag++;
           }
       }
    
      
      
      //-------------------------------
      
      
      
       
       System.out.println("Matrix :");
       for(int i=0;i<n;i++)
       {
           for(int j=0;j<n;j++)
           {
               System.out.print(arr[i][j]+"\t");
           }
           System.out.println("\n");
       }
       
       
       int  start_time = (int) System.nanoTime();
       int lminimum = FindLocalminimum(arr, 0, n-1, 0,n-1);
       long end_time = System.nanoTime();
       if(lminimum == 0)
       {
           System.out.println("No local minimum found");
       }
       else
       {
        System.out.println("Local Minimum is: "+ lminimum+" at ("+x1+","+y1+")");   
       }
      // System.out.println("Time: "+ (end_time - start_time)*0.001+" microsec");
       System.out.println("No. of Comparisions: "+ comp);
       
       
   }// end of main
    
   public static int FindLocalminimum(int arr[][],int rstart,int rend,int cstart,int cend)
   {
       int a,b;
       if(rstart==rend && cstart==cend) //if only 1 element
        { 
           x1=rstart;
           y1=cstart;
        return (arr[rstart][cstart]);
        }
       
       if(rstart==rend-1 && cstart==cend-1) //2 elements
        {
            a=findmin(rstart,cstart,cend,arr);
            b=findmin(rend,cstart,cend,arr);

            if(arr[rstart][a]<arr[rend][b])
               {
                   x1=rstart;
                   y1=a;
                    return (arr[rstart][a]);
               }
            else
            {
                x1=rend;y1=b;
                return(arr[rend][b]);
            }

        }
       //more than 2 elements
       if(rstart>=0 && rend>=0 && cstart>=0 && cend>=0)
       {
            int mid = (rstart + rend)/2;
            int min = arr[mid][0];
            int rowminposition = findmin(mid,cstart,cend,arr);
           /* for(int i=cstart;i<cend;i++)
            {
                if(arr[mid][i] < min)
                {
                    min = arr[mid][i];
                    rowminposition = i;
                }
            }*/
            if(rowminposition >= ((cstart + cend)/2))
            {
                cstart = (cstart + cend)/2;
            }
            else
            {
                cend = (cstart + cend)/2;
            }
            if(mid == 0)            //only 2 elements vertically
            {
                if(arr[mid][rowminposition] < arr[mid+1][rowminposition])
                {
                    x1 = mid;
                    y1 = rowminposition;
                    return arr[mid][rowminposition];
                }
            }
            else if(arr[mid][rowminposition] < arr[mid+1][rowminposition] && arr[mid][rowminposition] < arr[mid-1][rowminposition])
            {
                //checking minimum element in the same column
                x1 = mid;
                y1 = rowminposition;
                return arr[mid][rowminposition];
            }
            else if(arr[mid][rowminposition] > arr[mid+1][rowminposition] ) //making lower quadrant
            {
                x1 = mid;
                y1 = rowminposition;
                return FindLocalminimum(arr, mid, rend, cstart, cend);
            }
            else         //making upper quadrant
            {
                x1 = mid;
                y1 = rowminposition;
                return FindLocalminimum(arr, rstart, mid, cstart, cend);
            }
       }
       return 0;
   }//End of FindLocalMinimum

    public static int findmin(int row,int cstart, int cend,int arr[][]){
        int i,min,pos = cstart;
        min = arr[row][cstart];
        for(i=cstart;i<=cend;i++)
        {
            comp++;
            if(min >arr[row][i])
            {
                min = arr[row][i];
                pos = i;
            }
        }
        return pos;
    }
   
}//end of class
