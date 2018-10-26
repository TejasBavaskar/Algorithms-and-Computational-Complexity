#include <stdio.h>
#include <math.h>
#include <iostream>
#include <sys/time.h>
#include <time.h>
#include <unistd.h>
#include <cstdlib>

#define MILLION 1000000
long long int cnt =0;
using namespace std;
void insertionSort(int arr[], int n)
{
   int i, key, j;
   for (i = 1; i < n; i++)
   {
       key = arr[i];
       j = i-1;

       while (j >= 0 && arr[j] > key)
       {
            cnt++;
           arr[j+1] = arr[j];
           j--;
       }
       arr[j+1] = key;
   }
}

int main()
{
    struct timeval tpstart;
	struct timeval tpend;
	long timedif;
	int n;

    cout<<"Enter size of array: ";
    cin>>n;
    int arr[n];
      //Best
    for(int i=0;i<n;i++)
    {
        arr[i] = i;
    }
    //Average
    /*for(int i=0;i<n;i++)
    {
        arr[i] = rand() % n;
    }*/
   //Worst
   /*  for(int i=0, j=n-1;i<n;i++,j--)
    {
        arr[i] = j ;
    }*/

    gettimeofday(&tpstart, NULL);
    insertionSort(arr, n); //////////
    gettimeofday(&tpend, NULL);
	timedif = MILLION* (tpend.tv_sec - tpstart.tv_sec) + tpend.tv_usec - tpstart.tv_usec;

    /*cout<<"Insertion Sorted array: "<<endl;
    for(int i=0;i<n;i++)
    {
        cout<<arr[i]<<"\t";
    }*/
    cout<<endl;
    cout<<"It took "<<timedif<<" microsec."<<endl;
    cout<<"No. of swap :"<<cnt<<endl;
    return 0;
}
