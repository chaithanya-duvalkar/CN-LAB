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



/d/{
    if($0~/Ping/){
        drop_count++
    }
}END{
    print "The number of ping packets dropped are",drop_count
}