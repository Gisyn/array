//有一头母牛，它每年年初生一头小母牛。每头小母牛从第四个年头开始，每年年初也生一头小母牛。请编程实现在第n年的时候，共有多少头母牛？
#include<stdio.h>
void chunqiu(int num, int baby, int small, int adult, int mom, int year, int years){
	year++;
	if (year == 0){ num = 0; }
	else if (year == 1){ num++;  }
	else if (year == 2){ num++; baby++; }
	else if (year == 3){ num++; small++; }
	else{ mom += adult; adult = small; small = baby; baby = mom; num += baby; }
	if (year == years){ printf("%d\n", num); return; }

	chunqiu(num, baby, small, adult, mom, year, years);
}
int main()
{
	while (1){
		int num = 0;
		int baby = 0;
		int small = 0;
		int adult = 0;
		int mom = 1;
		int year = -1;
		int years = 0;
		scanf_s("%d", &years,1);
		chunqiu(num, baby, small, adult, mom, year, years);
	}
	return 0;
}
