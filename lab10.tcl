set ns [new simulator]

set ntrace [open lab10.tr w]
$ns trace-all $ntrace
set namfile [open lab10.nam w]
$ns namtrace-all $namfile

proc Finish{} {
    global ns ntrace namfile

    $ns flush-trace
    close $ntrace
    close $namfile

    exec nam lab10.nam &
    exec echo "The number of packets dropped are " &
    exec grep -c "^d" lab10.tr &
    exit 0
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

$n0 label "TCP source"
$n1 label "Sink"

$ns color 1 blue

$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 10ms DropTail

$ns duplex-link-op $n0 $n1 orient right
$ns duplex-link-op $n1 $n2 orient right

$ns queue-limit $n0 $n1 10
$ns queue-limit $n1 $n2 10

set tcp0 [new Agent/TCP]
$ns attach-agent $n0 tcp0
set sink0 [new Agent/TCPSink]
$ns attach-agent $n2 sink0
$ns connect $tcp0 $sink0

