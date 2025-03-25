#12 Implement an Ethernet LAN using n nodes and set multiple traffic nodes 
#and plot congestion window for different source / destination. 

#create simulator
set ns [new Simulator]

#set color
$ns color 1 Blue
$ns color 2 Red

#open nam and trace file
set ntrace [open lab12.tr w]
$ns trace-all $ntrace
set namfile [open lab12.nam w]
$ns namtrace-all $namfile

#open flat file
set winFile0 [open WinFile0 w]
set winFile1 [open WinFile1 w]

#finish procedure
proc Finish {} {
global ns ntrace namfile winFile0 winFile1

#dump trace data
$ns flush-trace
close $ntrace
close $namfile
close $winFile0
close $winFile1

#execute nam animation file
exec nam lab12.nam &

#plot the congestion window using xgraph
exec xgraph WinFile0 WinFile1 &
exit 0
}

proc PlotWindow {tcpSource file} {
global ns
set time 0.1
set now [$ns now]
set cwnd [$tcpSource set cwnd_]
puts $file "$now $cwnd"
$ns at [expr $now+$time] "PlotWindow $tcpSource $file"
}

#create 6 nodes
for {set i 0} {$i < 6} {incr i} {
set n($i) [$ns node]
}

#link nodes
$ns duplex-link $n(0) $n(2) 2Mb 10ms DropTail
$ns duplex-link $n(1) $n(2) 2Mb 10ms DropTail
$ns duplex-link $n(2) $n(3) 0.6Mb 100ms DropTail

#set lan 
set lan [$ns newLan "$n(3) $n(4) $n(5)" 0.5Mb 40ms LL Queue/DropTail MAC/802_3 Channel]

#set orientation
$ns duplex-link-op $n(0) $n(2) orient right-down
$ns duplex-link-op $n(1) $n(2) orient right-up
$ns duplex-link-op $n(2) $n(3) orient right

#queue limit btwn n2 and n3
$ns queue-limit $n(2) $n(3) 20
$ns duplex-link-op $n(2) $n(3) queuePos 0.5

#set error model at n2 and n3
set loss_module [new ErrorModel]
$loss_module ranvar [new RandomVariable/Uniform]
$loss_module drop-target [new Agent/Null]
$ns lossmodel $loss_module $n(2) $n(3)

#setup TCP connection n0 and n4
set tcp0 [new Agent/TCP/Newreno]
$tcp0 set fid_ 1
$tcp0 set window_ 8000
$tcp0 set packetSize_ 552
$ns attach-agent $n(0) $tcp0
set sink0 [new Agent/TCPSink/DelAck]
$ns attach-agent $n(4) $sink0
$ns connect $tcp0 $sink0

#setup ftp connection
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0
$ftp0 set type_ FTP

#setup another tcp for n5 and n1
set tcp1 [new Agent/TCP/Newreno]
$tcp1 set fid_ 2
$tcp1 set window_ 8000
$tcp1 set packetSize_ 552
$ns attach-agent $n(5) $tcp1
set sink1 [new Agent/TCPSink/DelAck]
$ns attach-agent $n(1) $sink1
$ns connect $tcp1 $sink1

#setup another ftp
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
$ftp1 set type_ FTP

#schedule events
$ns at 0.1 "$ftp0 start"
$ns at 0.1 "PlotWindow $tcp0 winFile0"
$ns at 0.5 "$ftp1 start"
$ns at 0.5 "PlotWindow $tcp1 winFile1"
$ns at 25.0 "$ftp0 stop"
$ns at 25.1 "$ftp1 stop"
$ns at 25.2 "Finish"

#run simulation
$ns run
