#11 Implement transmission of ping messages/trace route over a network 
#topology consisting of 6 nodes and find the number of packets dropped due 
#to congestion 


#create simulator
set ns [new Simulator]

#set color
$ns color 1 Blue
$ns color 2 Red

#open nam and trace file
set ntrace [open lab11.tr w]
$ns trace-all $ntrace
set namfile [open lab11.nam w]
$ns namtrace-all $namfile

#finish procedure
proc Finish {} {
global ns ntrace namfile

#dump trace data
$ns flush-trace
close $ntrace
close $namfile

#execute nam animation file
exec nam lab11.nam &

#find packets dropped
puts "packets dropped are: " 
exec grep "^d" lab11.tr | cut -d " " -f 5 | grep -c "ping" &
exit 0
}

#create 6 nodes
for {set i 0} {$i < 6} {incr i} {
set n($i) [$ns node]
}

#link nodes
for {set j 0} {$j < 5} {incr j} {
$ns duplex-link $n($j) $n([expr ($j+1)]) 0.1Mb 10ms DropTail
}

#define the recv function
Agent/Ping instproc recv {from rtt} {
$self instvar node_
puts "node [$node_ id] received ping answer from $from with round trip time $rtt ms"
}

#create two ping agents
set p0 [new Agent/Ping]
$p0 set class_ 1
$ns attach-agent $n(0) $p0 
set p1 [new Agent/Ping]
$p1 set class_ 1
$ns attach-agent $n(5) $p1
$ns connect $p0 $p1 

#set queuesize
$ns queue-limit $n(2) $n(3) 2
$ns duplex-link-op $n(2) $n(3) queuePos 0.5

#create congestion between n2 & n4
set tcp0 [new Agent/TCP]
$tcp0 set class_ 2
$ns attach-agent $n(2) $tcp0
set sink0 [new Agent/TCPSink]
$sink0 set class_ 2
$ns attach-agent $n(4) $sink0
$ns connect $tcp0 $sink0

#apply cbr traffic over tcp
set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 500
$cbr0 set rate_ 1Mb
$cbr0 attach-agent $tcp0

#schedule events
$ns at 0.2 "$p0 send"
$ns at 0.4 "$p1 send"
$ns at 0.4 "$cbr0 start"
$ns at 0.8 "$p0 send"
$ns at 1.0 "$p1 send"
$ns at 1.2 "$cbr0 stop"
$ns at 1.4 "$p0 send"
$ns at 1.6 "$p1 send"
$ns at 1.8 "Finish"

#run simulation
$ns run


 
