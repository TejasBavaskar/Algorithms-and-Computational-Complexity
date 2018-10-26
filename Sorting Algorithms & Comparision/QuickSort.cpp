#include <iostream>
#include <sys/time.h>
#include <time.h>
#include <unistd.h>
#include <cstdlib>

#define MILLION 1000000
using namespace std;
long long int cnt = 0;

int partition (int arr[], int low, int high)
{
    int temp;
    int mid = (low + high)/2;
    int pivot = arr[high];    // pivot
    int i = (low - 1);  // Index of smaller element

    for (int j = low; j <= high- 1; j++)
    {
        if (arr[j] <= pivot)
        {
            cnt++;
            i++;
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    temp = arr[i+1];
    arr[i+1] = arr[high];
    arr[high] = temp;
    cnt++;
    return (i + 1);
}

void quickSort(int arr[], int low, int high)
{
    if (low < high)
    {
        /* pi is partitioning index, arr[p] is now
           at right place */
        int pi = partition(arr, low, high);
        // Separately sort elements before
        // partition and after partition
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
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
     //worst
    /*for(int i=0;i<n;i++)
    {
        arr[i] = i;
    }*/
    //Best
    srand(time(0));
    for(int i=0;i<n;i++)
    {
        arr[i] = rand()% n;
    }
    //Worst
   /* for(int i=0, j=n-1;i<n;i++,j--)
    {
        arr[i] = j ;
    }*/


    gettimeofday(&tpstart, NULL);
    quickSort(arr, 0, n-1);
    gettimeofday(&tpend, NULL);
	timedif = MILLION* (tpend.tv_sec - tpstart.tv_sec) + tpend.tv_usec - tpstart.tv_usec;
	cout<<endl;
    cout<<"It took "<<timedif<<" microsec."<<endl;
    cout<<"No. of swaps: "<<cnt<<endl;
   /* cout<<"Quick Sorted array: "<<endl;
    for(int i=0;i<n;i++)
    {
        cout<<arr[i]<<"\t";
    }*/
    return 0;
}
