#include <iostream>
#include<time.h>
#include <sys/time.h>
#include <unistd.h>
#define MILLION 1000000
using namespace std;
int cnt = 0;
long long BinaryExpo(int x,int y)
{
    long long ans = 1;
    int m = y;
    int q;
    do
    {
        int r = m % 2;
         q = m / 2;

        if(r == 1)
        {
            ans = ans * x;
            cnt++;
        }
        if(q == 0)
            return ans;
        m = q;
        x = x * x;
        cnt++;
    }while(q != 0);
}

int main()
{
    int x,y;
    cout<<"Enter x: ";
    cin>>x;
    cout<<"Enter y: ";
    cin>>y;
    cout<<y;
    struct timeval tpstart;
	struct timeval tpend;
	long timedif;
    gettimeofday(&tpstart, NULL);


    long long ans = BinaryExpo(x,y);

    gettimeofday(&tpend, NULL);
    timedif = MILLION* (tpend.tv_sec - tpstart.tv_sec) +
                    tpend.tv_usec - tpstart.tv_usec;

    cout<<"Answer :"<<ans<<endl;
    cout<<"Time: "<<timedif <<"microsec"<<endl;
    cout<<"No. of operations: "<<cnt;
    return 0;
}

