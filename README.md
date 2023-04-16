# CronExpressionParser
This application parses a cron string and expands each field to show the times at which it will run. 

Input format corresponds to the single line standard cron format with five time fields (minute, hour, day of month, month, and day of week) plus a command. Special time strings such as "@yearly" are not handled. Following multiple operators per field are supported: "*/step", "startValue-endValue/step". Other combinations of operators are omitted.

Output is formatted as a table with the field name taking the first 14 columns and the times as a space-separated list following it.
