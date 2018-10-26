#include<iostream>
#include<time.h>
#include <sys/time.h>
#include <unistd.h>
#define MILLION 1000000
using namespace std;
// ******** O(nlogn)******(merge sort)

int merge(int arr[],int temp[], int l, int m, int r)
{
    int inv = 0;
    int i = l;
    int j = m;
    int k = l;

    int n1 = m - 1;
    int n2 =  r ;

    while (i <= n1 && j <= n2)
    {
        if (arr[i] <= arr[j])
        {
            temp[k] = arr[i];
            i++;
            k++;
        }
        else
        {
            temp[k] = arr[j];
            j++;
            k++;
            inv = inv + (m - i);
        }
    }

    while (i <= n1)
    {
        temp[k] = arr[i];
        i++;
        k++;
    }

    while (j <= n2 )
    {
        temp[k] = arr[j];
        j++;
        k++;

    }
    for(int x = l;x <= r;x++)
    {
        arr[x] = temp[x];
    }
    return inv;
}
int mergeSort(int arr[],int temp[], int l, int r)
{
    int m;
    int cnt1=0;
    int cnt2=0;
    int cnt3=0;
    if (l < r)
    {
        m = (l + r)/2;
        cnt1 =  mergeSort(arr,temp, l, m);
        cnt2 = cnt1 + mergeSort(arr,temp, m+1, r);
        cnt3 = cnt2 + merge(arr,temp, l, m+1, r);
    }
    return cnt3;
}

int main()
{
	int n,mergeinvcnt =0;
	cout<<"To find inversion with O(nlogn) time complexity\n";
	cout<<"Enter size of array: ";
	cin>>n;
	int arr[n];
	cout<<"Enter array elements: ";
	for(int i=0;i<n;i++)
	{
		cin>>arr[i];
	}
    int temp[n];

	struct timeval tpstart;
	struct timeval tpend;
	long timedif;
    gettimeofday(&tpstart, NULL);

    mergeinvcnt = mergeSort(arr, temp,0, n - 1);

	gettimeofday(&tpend, NULL);
    timedif = MILLION* (tpend.tv_sec - tpstart.tv_sec) +
                    tpend.tv_usec - tpstart.tv_usec;
	cout<<"Count in Improved nlogn: "<<mergeinvcnt<<endl;
	cout<<"Time: "<<timedif<<" microsec";

	return 0;
}

/*cnt
------
5   9
*/
