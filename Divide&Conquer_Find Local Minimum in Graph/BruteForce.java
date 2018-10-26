package localminima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class BruteForce {
    static int x1,y1,comp,flag;
   public static void main(String[] args) {
       
        int node,rows=0,rowe,cols=0,cole;
        
        Scanner scan = new Scanner(System.in);
       ArrayList<Integer> list = new ArrayList<Integer>();
        
       System.out.print("Enter n: ");
       int n = scan.nextInt();
       rowe=n-1;
       cole=n-1;
       
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
       
       //manual input worst case****************
      //-------------------------------
      int temp[] = new int[n*n];
      for(int i=n*n,j=0;i>0;i--,j++)
      {
          temp[j] = i;
      }
      
      for(int i=0;i<n;i++)
       {
           for(int j=0;j<n;j++)
           {
               arr[i][j] = temp[flag];
               flag++;
           }
       }
       
       
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
       node = localmin(rows,cols,rowe,cole,arr,n);
       long end_time = System.nanoTime();
       if(node == 0)
       {
           System.out.println("No local minimum found");
       }
       else
       {
        System.out.println("Local Minimum is: "+ node+" at ("+x1+","+y1+")");  
           System.out.println("No. of Comparisions: "+ comp);
       }
      // System.out.println("Time: "+ (end_time - start_time)*0.001+" microsec");
       
        
        
        
   }

    private static int localmin(int rows, int cols, int rowe, int cole, int[][] arr, int n) {
        int i,j;
        for(i=0;i<n;i++)
        {
            for(j=0;j<n;j++)
            {
                if(i==rows && j==cols)//row =0 col=0 1
                {   comp++;

                   if(arr[i][j]<arr[rows][cols+1] && arr[i][j]<arr[rows+1][cols])
                   {
                       x1=i;y1=j;
                       return arr[i][j];
                   }

                }
                 if(j>cols && j<cole)
                {   //comp++;
                    if(i==rows)//0 th row middle col
                    {   comp++;
                        if(arr[i][j]<arr[i][j+1] && arr[i][j]<arr[i][j-1] && arr[i][j]<arr[i+1][j])
                           {
                               x1=i;y1=j;
                               return arr[i][j];
                           }
                    }
                }
                     if(i==rows && j==cole) //2 element 0,colend
                    {
                        comp++;
                       if(arr[i][j]<arr[rows][cole-1] && arr[i][j]<arr[rows+1][cole])
                       {
                           x1=i;y1=j;
                           return arr[i][j];
                       }

                    }

                ///**************First Row Completet


                if(j==cols &&(i>rows && i<rowe))//element 1,0  2,0  3,0....
                {   comp++;
                    if(arr[i][j]<arr[i][j+1] && arr[i][j]<arr[i+1][j] && arr[i][j]<arr[i-1][j])
                       {
                           x1=i;y1=j;
                           return arr[i][j];
                       }
                }


                 if((i>rows && i<rowe) &&(j>cols && j<cole) ) //middle elements
                {   comp++;
                   if(arr[i][j]<arr[i][j+1] && arr[i][j]<arr[i][j-1] && arr[i][j]<arr[i+1][j] && arr[i][j]<arr[i-1][j])
                       {
                          x1=i;y1=j;;
                           return arr[i][j];
                       }
                }

                if(j==cole &&(i>rows && i<rowe) ) //0,n 1,n 2,n last colunm middle   ele
                {   comp++;
                    if(arr[i][j]<arr[i][j-1] && arr[i][j]<arr[i+1][j] && arr[i][j]<arr[i-1][j])
                       {
                           x1=i;y1=j;
                           return arr[i][j];
                       }

                }

                if(i==rowe && j==cols)//3
                {
                    comp++;
                   if(arr[i][j]<arr[rowe-1][cols] && arr[i][j]<arr[rowe][cols+1])
                   {
                       x1=i;y1=j;
                       return arr[i][j];
                   }

                }

                 if(i==rowe && (j>cols && j<cole))
                {   comp++;
                   if(arr[i][j]<arr[i][j+1] && arr[i][j]<arr[i][j-1] && arr[i][j]<arr[i-1][j])
                       {
                           x1=i;y1=j;
                           return arr[i][j];
                       }
                }


                if(i==rowe && j==cole) //4
               {
                   comp++;

                  if(arr[i][j]<arr[rowe-1][cole] && arr[i][j]<arr[rowe][cole-1])
                  {
                       x1=i;y1=j;
                      return arr[i][j];
                  }

               }
           }
    }
    System.out.println("Returning 0");
    return 0;
    
    }
}
