import itertools
import copy

def solution(map):
    # Get the size of the map
    # it can be non-squared
    h = len(map)
    w = len(map[0])
    # Give a name to columns and rows
    cols = [str(x) for x in range(w)]
    rows = [chr(x) for x in range(97, (97+h))]
    # Give a name to each node
    nodes = [c+r for (c,r) in itertools.product(rows, cols)]
    # Set the cells at 1, and walls at 1001
    weights = [(item*1000)+1 for sublist in map for item in sublist]
    # Initialize a list for results
    results = []
    for i, weight in enumerate(weights):
        # only run for the walls,
        # changing the value of the
        # specific wall to 1 to remove it
        if weight > 1000:
            new_weights = copy.deepcopy(weights)
            # Removing the wall
            new_weights[i] = weights[i] - 1000
            # Creating the graph
            init_graph = {}
            for node in nodes:
                init_graph[node] = {}
            for i, node in enumerate(nodes):
                # If node i is not the last one
                if i < len(nodes) -1:
                    # and it's not on the rightmost column
                    if (i+1) % w != 0:
                        # Connect with next node (right)
                        init_graph[node][nodes[i+1]] = max(new_weights[i], new_weights[i+1])
                # If node i is not on the last row
                if i < len(nodes) - w:
                    # connect with node below
                    init_graph[node][nodes[i+w]] = max(new_weights[i], new_weights[i+w])
            graph = Graph(nodes, init_graph)
            # Find the shortest path for the specific graph
            shortest_path = dijkstra_algorithm(graph=graph, start_node="a0")
            # Exit is always the last node
            result = shortest_path.get(nodes[-1]) +1 # add 1 because Dijkstra do not count the starting node as a step
            # Save the result in our list
            results.append(result)
    return min(results) # Return the shortest path