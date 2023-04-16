# CronExpressionParser
This application parses a cron string and expands each field to show the times at which it will run. 

Input format corresponds to the single line standard cron format with five time fields (minute, hour, day of month, month, and day of week) plus a command. Special time strings such as "@yearly" are not handled. Following multiple operators per field are supported: "*/step", "startValue-endValue/step". Other combinations of operators are omitted.

Output is formatted as a table with the field name taking the first 14 columns and the times as a space-separated list following it.

# Instructions
Download JDK via https://www.oracle.com/cis/java/technologies/downloads/

Open project directory and run
```
javac Main.java
```
```
java Main
```
Input
```
*/15 0 1,15 * 1-5 /usr/bin/find
```
Output
```
minute        0 15 30 45 
hour          0
day of month  1 15 
month         1 2 3 4 5 6 7 8 9 10 11 12 
day of week   1 2 3 4 5 
command       /usr/bin/find
```

