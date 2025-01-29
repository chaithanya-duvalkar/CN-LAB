BEGIN{
    #include<stdio.h>
    drop_count=0;
}
{
    if($0=="ping")
    {
        drop_count++;
    }
}END{
    printf("The number of ping packets dropped are",drop_count);
}