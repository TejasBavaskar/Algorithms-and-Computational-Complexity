#include <iostream>
#include <sys/time.h>
#include <time.h>
#include <unistd.h>
#include <cstdlib>
#define MILLION 1000000
using namespace std;
long long int cnt=0;
void heapify(int arr[], int n, int i)
{
    int temp;
    int largest = i;
    int l = 2*i + 1;
    int r = 2*i + 2;

    if (l < n && arr[l] > arr[largest])
        largest = l;

    if (r < n && arr[r] > arr[largest])
        largest = r;

    if (largest != i)
    {
        cnt++;
        temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;

        heapify(arr, n, largest);
    }
}
void heapSort(int arr[], int n)
{
    int temp;
    for (int i = n / 2 - 1; i >= 0; i--)
        heapify(arr, n, i);

    for (int i=n-1; i>=0; i--)
    {
        cnt++;
        temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;
        heapify(arr, i, 0);
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
    //Average
    srand(time(0));
    for(int i=0;i<n;i++)
    {
        arr[i] = rand() % n;
    }
   /* //Worst
    for(int i=0, j=n-1;i<n;i++,j--)
    {
        arr[i] = j ;
    }*/

    gettimeofday(&tpstart, NULL);
    heapSort(arr, n);
    gettimeofday(&tpend, NULL);
	timedif = MILLION* (tpend.tv_sec - tpstart.tv_sec) + tpend.tv_usec - tpstart.tv_usec;

   /* cout<<"Heap Sorted array: "<<endl;
    for(int i=0;i<n;i++)
    {
        cout<<arr[i]<<"\t";
    }*/
    cout<<endl;
    cout<<"It took "<<timedif<<" microsec."<<endl;
    cout<<"No. of swaps :"<<cnt<<endl;
    return 0;

}
