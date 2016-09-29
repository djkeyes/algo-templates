import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PrimMST {

	public static LinkedList<Edge> mst(LinkedList<Edge>[] adjList) {
		int n = adjList.length;
		boolean[] visited = new boolean[n];
		visited[0] = true;
		LinkedList<Edge> result = new LinkedList<>();
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>(n,
				new Comparator<Edge>() {
					@Override
					public int compare(Edge o1, Edge o2) {
						// TODO Auto-generated method stub
						return Long.signum(o1.dist - o2.dist);
					}
				});
		queue.addAll(adjList[0]);
		while (!queue.isEmpty()) {
			Edge cur = queue.remove();
			if (visited[cur.to]) {
				continue;
			}
			visited[cur.to] = true;
			result.add(cur);

			for (Edge out : adjList[cur.to]) {
				if (!visited[out.to]) {
					queue.add(out);
				}
			}
		}

		return result;
	}

	public static class Edge {
		public int from;
		public int to;
		public long dist;

		public Edge(int from, int to, long dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}

	public static void addEdge(LinkedList<Edge>[] adjList, int u, int v, long w) {
		adjList[u].add(new Edge(u, v, w));
		adjList[v].add(new Edge(v, u, w));
	}

	public static void main(String[] args) {
		int n = 10;
		@SuppressWarnings("unchecked")
		LinkedList<Edge>[] adjList = new LinkedList[n];
		for (int i = 0; i < n; i++) {
			adjList[i] = new LinkedList<Edge>();
		}

		addEdge(adjList, 0, 1, 1L);
		addEdge(adjList, 0, 2, 17L);
		addEdge(adjList, 1, 4, 13L);
		addEdge(adjList, 1, 5, 14L);
		addEdge(adjList, 2, 1, 16L);
		addEdge(adjList, 3, 8, 9L);
		addEdge(adjList, 4, 5, 10L);
		addEdge(adjList, 4, 3, 11L);
		addEdge(adjList, 5, 7, 5L);
		addEdge(adjList, 6, 2, 4L);
		addEdge(adjList, 6, 9, 3L);
		addEdge(adjList, 7, 1, 15L);
		addEdge(adjList, 8, 4, 2L);
		addEdge(adjList, 9, 2, 7L);
		addEdge(adjList, 9, 1, 6L);

		LinkedList<Edge> ans = mst(adjList);
		System.out.println("expected n-1=" + (n - 1) + " edge, got "
				+ ans.size());
		long sum = 0;
		for (Edge e : ans) {
			sum += e.dist;
		}
		System.out.println("total mst size: " + sum + ", expected " + 53);

		System.out.println("Edges: ");
		for (Edge e : ans) {
			System.out.println(String.format("[%d, %d]: %d", e.from, e.to,
					e.dist));
		}
	}
}
