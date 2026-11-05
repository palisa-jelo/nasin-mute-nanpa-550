These are all the algorithms I've written for COMP 550 homeworks.  
# <ins>Overview</ins>
### Homework 1:
BasicSearchAlg with two sum and binary search; SortingAlgs with selection sort, merge sort, and topological sort. 
### Homework 2:
BasicSearchAlg with BFS and DFS. 
### Homework 3:
UndirectedGraphAlg with Prim's Algorithm; MiscelaneousHomework3Algs includes compatible intervals and fractional knapsack. 
### Homework 4:
DynamicProgramming with longest increasing subsequence, largest palindromic subsequence, and 0/1 knapsack. 
### Homework 5:
GraphSearchAlg with basic DAG SSSP, Bellman-Ford, and Dijkstra. 

# 
  
The structures folder holds a number of supporting classes, mostly in creating the graphs for the algorithms to run on.
The only notable exception is MinHasHeapSet.java, an efficient priority queue specialised for Prim's Algorithm and Dijkstra's algorithm, ordering objects by some secondary attribute. It has O(1) lookup and keeps O(logn) add while adding a duplicate object will replace it.
