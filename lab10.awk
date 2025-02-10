BEGIN{
    #include<stdio.h>
    count=0;
}
{
    if($1=="d")
    #d stands for the packets dropped
    count++;
}END{
    printf("The total number of packets dropped due to congestion:%d\n\n",count);
}