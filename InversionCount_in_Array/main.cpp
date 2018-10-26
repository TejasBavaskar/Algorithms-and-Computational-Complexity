#include<iostream>
#include<time.h>
#include <sys/time.h>
#include <unistd.h>
#define MILLION 1000000
using namespace std;
// *************O(n^2)*************

int main()
{
	static int count;
	int size;
	cout<<"To find inversion with O(n^2) time complexity\n";
	cout<<"Enter size of array: ";
	cin>>size;
	int arr[size];
	cout<<"Enter array elements: ";
	for(int i=0;i<size;i++)
	{
		cin>>arr[i];
	}

	struct timeval tpstart;
	struct timeval tpend;
	long timedif;
    gettimeofday(&tpstart, NULL);

	for(int i=0;i<size;i++)
	{
		for(int j=i+1;j<size;j++)
		{
			if(arr[i] > arr[j])
			{
				count++;
			}
		}

	}

	gettimeofday(&tpend, NULL);
    timedif = MILLION* (tpend.tv_sec - tpstart.tv_sec) +
                    tpend.tv_usec - tpstart.tv_usec;
	cout<<"Count : "<<count;
	cout<<"Time: "<<timedif;

	return 0;
}
