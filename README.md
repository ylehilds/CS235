# Data Structures & Logic in Java

A portfolio-quality collection of **from-scratch data structure implementations** and **algorithmic problem-solving exercises** in **Java**. This repository demonstrates mastery of core CS concepts (lists, trees, heaps, hashing, graphs), correctness via unit tests, and awareness of time/space complexity. It’s organized for easy exploration, discussion in interviews, and future extension.

## What This Project Includes
- **Linear Structures**: dynamic arrays, singly/doubly linked lists, stacks, queues, and deques.
- **Trees & Heaps**: binary search trees (with balanced variants such as AVL or similar, if present), heap/priority queue operations, traversals.
- **Hash-Based Structures**: hash maps/sets with collision handling (e.g., chaining or open addressing), load factor tuning, and custom hash strategies.
- **Graphs**: adjacency list/matrix representations, breadth-first search (BFS), depth-first search (DFS), connectivity checks, and basic shortest-path scaffolding.
- **Algorithms & Utilities**: sorting (e.g., Quick/Merge), searching, expression parsing (infix→postfix) and evaluation, small puzzle solvers, and driver programs for demos.
- **Testing & Verification**: JUnit-based unit tests covering core operations, edge cases, and invariants (where applicable).

## Why It’s Portfolio-Worthy
- **First-principles implementations**: avoids relying on high-level libraries so the design decisions and trade-offs are explicit.
- **Clean, modular Java**: idiomatic OOP structure with clear responsibilities and readable APIs.
- **Correctness focus**: unit tests validate expected behavior and guard against regressions.
- **Complexity literacy**: comments and method docs call out asymptotic costs and implementation trade-offs.
- **Interview-ready artifacts**: concise demos, clear naming, and easy run instructions make it ideal for walkthroughs.

## Tech Stack
- **Language**: Java (SE)
- **Testing**: JUnit (for unit tests)
- **Tooling**: Works with IntelliJ IDEA / Eclipse / VS Code (Java extensions) and standard JDK CLI builds
- **Dependencies**: Standard library only (unless a submodule specifies otherwise)

## Getting Started
1) **Clone**
   git clone https://github.com/ylehilds/CS235.git
   cd CS235

2) **Open in an IDE (recommended)**
    - Open the folder in IntelliJ IDEA (or your preferred IDE).
    - Let the IDE index sources under `src/`.
    - Locate a class with a `public static void main(String[] args)` method and run it.

3) **Or compile & run via command line**
   mkdir -p out
   javac -d out $(find src -name "*.java")
   # Run a specific demo (replace with actual package + class)
   java -cp out com.example.structures.BSTDemo

4) **Run tests (if present)**
    - Use your IDE’s JUnit runner, or the project’s build tool if configured.

## Repository Structure
- `src/` — packages for structures and algorithms (lists, trees, heaps, hash maps, graphs, parsing, sorting, drivers/tests)
- `README.md`, `LICENSE` — documentation and license files

> Tip: If you’re unsure which class to run, search for files containing a `main` method and start from there. Many demos print results or assertions that help verify behavior.

## Representative Highlights
- **BST/AVL**: insert/delete/rotate; inorder/preorder/postorder; height/balance checks.
- **Hash Tables**: collision resolution, load factor control, and stress tests with adversarial inputs.
- **Graphs**: BFS/DFS traversals and utility methods for components and reachability.
- **Parsing**: tokenization and shunting-yard style conversion for expression evaluation.
- **Sorting**: quicksort/mergesort variants with pivot strategies and brief performance notes.

## How to Extend
- Add **property-based tests** or randomized sequences to validate invariants (e.g., tree balance).
- Implement additional structures: **heaps/priority queues**, **tries**, **union–find (disjoint set)**, **LRU cache**.
- Enrich graph algorithms with **Dijkstra**, **topological sort**, or **MST**.
- Wrap demos in a small CLI menu to compare operations and timings across implementations.

## License
MIT — see `LICENSE`.

## Author
**Lehi Alcantara**  
Website: https://www.lehi.dev  
Email: lehi@lehi.dev