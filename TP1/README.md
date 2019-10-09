# TP1-SIA-2019B

Gridlock search algorithm. Implemented with DFS, BFS, IDDFS, GREEDY and A*(ASTAR).

# Instructions

- Create your table(OPTIONAL): First line must contain de size and then must be the table.
  - Example: 
```
6 
00 01 01 00 00 00
00 00 00 02 00 00
00 00 00 02 00 00
-1 -1 00 00 03 00
00 00 00 00 03 00
04 04 04 04 00 00
```
 


- Run the jar:

      - Required parameters: -a (algorithm).
      - Optional: -p(path to initial table), -h(heuristic: basic, medium, advance, inadmissible).
      Example: java -jar gps-1.0-jar-with-dependencies.jar -a=ASTAR -p=L3_S5.txt -h=advance

      
