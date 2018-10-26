#include <iostream>
#include<time.h>
#include <sys/time.h>
#include <unistd.h>
#define MILLION 1000000

using namespace std;
int cnt = 0;
long long SimpleExpo(int x,int y)
{
    long long int ans = 1;
    for(int i=0;i<y;i++)
    {
        ans = ans * x;
        cnt++;

    }
    return ans;
}

int main()
{
    int x,y;
    cout<<"Enter x: ";
    cin>>x;
    cout<<"Enter y: ";
    cin>>y;

    struct timeval tpstart;
	struct timeval tpend;
	long timedif;
    gettimeofday(&tpstart, NULL);

    long long ans = SimpleExpo(x,y);

    gettimeofday(&tpend, NULL);
    timedif = MILLION* (tpend.tv_sec - tpstart.tv_sec) +
                    tpend.tv_usec - tpstart.tv_usec;

    cout<<"Answer :"<<ans<<endl;
    cout<<"Time :"<< timedif <<"microsec"<<endl;
    cout<<"No. of operations: "<<cnt;
    return 0;
}
