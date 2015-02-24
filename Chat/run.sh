#opens a command in a new tab
#usage: new_tab "command.."
new_tab() {
        pwd=`pwd`
        osascript -e "tell application \"Terminal\"" \
        -e "tell application \"System Events\" to keystroke \"t\" using {command down}" \
        -e "do script \"cd $pwd; clear; $1;\" in front window" \
        -e "end tell"
        > /dev/null
}

#timeout, usage: timeout 1 sleep X
function timeout() { 
	perl -e 'alarm shift; exec @ARGV' "$@"; 
}

cd build/classes
export CLASSPATH=.
#killall rmiregistry
rmiregistry &
timeout 1 sleep 2
new_tab "java server.Server"
timeout 1 sleep 2
java client.Client localhost
java client.Client localhost
java client.Client localhost
cd ..
cd ..

#::java -cp . -Djava.rmi.server.hostname=localhost server.Server
