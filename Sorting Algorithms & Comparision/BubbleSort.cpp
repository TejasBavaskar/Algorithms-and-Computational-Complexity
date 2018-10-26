#include <iostream>
#include <sys/time.h>
#include <time.h>
#include <unistd.h>
#include <cstdlib>

#define MILLION 1000000
using namespace std;
long long int cnt =0;
void bubbleSort(int arr[], int n)
{
   int temp;
   for (int i = 0; i < n-1; i++)
   {
       for (int j = 0; j < n-i-1; j++)
       {

           if (arr[j] > arr[j+1])
           {
                cnt++;
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
           }
       }

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
   /* for(int i=0, j=n-1;i<n;i++,j--)
    {
        arr[i] = j ;
    }*/


    gettimeofday(&tpstart, NULL);
    bubbleSort(arr, n); //////////
    gettimeofday(&tpend, NULL);
	timedif = MILLION* (tpend.tv_sec - tpstart.tv_sec) + tpend.tv_usec - tpstart.tv_usec;

  /*  cout<<"Bubble Sorted array: "<<endl;
    for(int i=0;i<n;i++)
    {
        cout<<arr[i]<<"\t";
    }*/
    cout<<endl;
    cout<<"It took "<<timedif<<" microsec."<<endl;
    cout<<"No. of swap :"<<cnt<<endl;
    return 0;
}
