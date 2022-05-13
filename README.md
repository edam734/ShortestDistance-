# ShortestDistance-

**(From geeksforgeeks)**

Shortest distance between two cells in a matrix or grid

Given a matrix of N*M order. Find the shortest distance from a source cell to a destination cell, traversing through limited cells only. Also you can move only up, down, left and right. If found output the distance else -1.<br />
s represents ‘source’<br />
d represents ‘destination’<br />
\* represents cell you can travel<br />
0 represents cell you can not travel<br />
This problem is meant for single source and destination.<br />
Examples:<br />
 
```
Input : {'0', '*', '0', 's'},
        {'*', '0', '*', '*'},
        {'0', '*', '*', '*'},
        {'d', '*', '*', '*'}
Output : 6

Input :  {'0', '*', '0', 's'},
         {'*', '0', '*', '*'},
         {'0', '*', '*', '*'},
         {'d', '0', '0', '0'}
Output :  -1
```
