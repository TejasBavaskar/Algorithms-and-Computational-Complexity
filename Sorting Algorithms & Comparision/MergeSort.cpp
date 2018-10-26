#include <iostream>
#include <sys/time.h>
#include <time.h>
#include <unistd.h>
#include <cstdlib>

#define MILLION 1000000
long long int cnt = 0;
using namespace std;
void merge(int arr[], int l, int m, int r)
{
    int i, j, k;
    int n1 = m - l + 1;
    int n2 =  r - m;

    /* create temp arrays */
    int L[n1], R[n2];

    /* Copy data to temp arrays L[] and R[] */
    for (i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (j = 0; j < n2; j++)
        R[j] = arr[m + 1+ j];

    /* Merge the temp arrays back into arr[l..r]*/
    i = 0; // Initial index of first subarray
    j = 0; // Initial index of second subarray
    k = l; // Initial index of merged subarray
    while (i < n1 && j < n2)
    {
        if (L[i] <= R[j])
        {

            arr[k] = L[i];
            i++;
        }
        else
        {
            cnt++;
            arr[k] = R[j];
            j++;

        }
        k++;
    }


    while (i < n1)
    {
        arr[k] = L[i];
        i++;
        k++;
    }

    while (j < n2)
    {
        arr[k] = R[j];
        j++;
        k++;
    }
}

void mergeSort(int arr[], int l, int r)
{
    if (l < r)
    {
        // Same as (l+r)/2, but avoids overflow for
        // large l and h
        int m = (l+r)/2;

        // Sort first and second halves
        mergeSort(arr, l, m);
        mergeSort(arr, m+1, r);

        merge(arr, l, m, r);
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
   /* for(int i=0;i<n;i++)
    {
        arr[i] = i;
    }*/
   // Average
    for(int i=0;i<n;i++)
    {
        arr[i] = rand() % n;
    }
    //Worst
   /* for(int i=0, j=n-1;i<n;i++,j--)
    {
        arr[i] = j ;
    }*/

    gettimeofday(&tpstart, NULL);
    mergeSort(arr, 0, n - 1);
    gettimeofday(&tpend, NULL);
	timedif = MILLION* (tpend.tv_sec - tpstart.tv_sec) + tpend.tv_usec - tpstart.tv_usec;
    cout<<"It took "<<timedif<<" microsec."<<endl;
    cout<<"Merge Sorted array: "<<endl;
    cout<<"No. of swap :"<<cnt<<endl;
   /* for(int i=0;i<n;i++)
    {
        cout<<arr[i]<<"\t";
    }*/
    return 0;
}
