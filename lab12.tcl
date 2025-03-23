#set simulator
set ns [new Simulator]

#open nam and trace file
set ntrace [open lab10.tr w]
$ns trace-all $ntrace
set namfile [open lab10.nam w] 
$ns namtrace-all $namfile

#finish procedure
proc Finish {} {
global ns ntrace namfile

#dump all the trace data
$ns flush-trace
close $ntrace
close $namfile

#execute the nam animation file
exec nam lab10.nam &

#show the no. of packets dropped
exec echo "the no. of packets dropped is" &
exec grep -c "^d" lab10.tr &
exit 0

}

#create node
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

#label nodes
$n0 label "TCP Source"
$n1 label "Sink"

#set color
$ns color blue 1

#create link
$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n0 $n1 1Mb 10ms DropTail

#make orientation
$ns duplex-link-op $n0 $n1 1Mb 10ms orient right
$ns duplex-link-op $n1 $n2 1Mb 10ms orient right

#queue limit
$ns queue-limit $n0 $n1 10
$ns queue-limit $n1 $n2 10

#set up tranport layer connection
set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0
set sink0 [new Agent/TCPSink]
$ns attach-agent $n2 $sink0
$ns connect $tcp0 $sink0

#set up application layer traffic
set cbr0 [new Application/Traffic/CBR]
$cbr0 set type_CBR
$cbr0 set packetSize_100
$cbr0 set rate_1Mb
$cbr0 set random_false
$cbr0 attach-agent $tcp0
$tcp0 set class_1

#schedule events
$ns at 0.0 "$cbr0 start"
$ns at 5.0 "Finish"

#run simulation
$ns run