This is a cellular automaton to simulate forest fires. 

### Rules

Each cell can be empty (black), occupied by a tree (green) or burning (orange).

* A tree will catch fire if any neighboring cell is on fire
* A burning tree cell will become empty
* An empty cell will grow a tree with probability *t*
* A tree will catch fire with probability *f* (even if it's not near a burning tree; this simulates lightning strikes)
