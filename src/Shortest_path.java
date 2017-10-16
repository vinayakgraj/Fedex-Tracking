
import java.util.ArrayList;
import java.util.Collections;

public class Shortest_path {
	
	private int[][] preD = new int[25][25];
	private int min = 99999, nextNode = 0; 
	
    @SuppressWarnings("rawtypes")
	ArrayList<ArrayList> shortestpath = new ArrayList<ArrayList>();
	public int[][] distance = new int[25][25]; 
	
						//  0		1		2		3		4		5		6		7		8		9		10		11		12		13		14		15		16		17		18		19		20		21		22		23		24
	
	int[][] matrix = 	{ { 0,		1,		0,		1,		0,		0,		0,		0,		0,		0,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0}, 
			/*1-*/	  	  { 1,		0,		1,		0,		0,		0,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0}, 
			/*2*/		  { 0,		1,		0,		1,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0},
			/*3-*/		  { 1,		0,		1,		0,		0,		0,		0,		0,		0,		1,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0},
			/*4*/		  { 0,		0,		1,		0,		0,		1,		0,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0},
			/*5*/		  { 0,		0,		0,		0,		1,		0,		1,		0,		0,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0},
			/*6*/		  { 0,		0,		0,		0,		0,		1,		0,		1,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0},
			/*7*/		  { 0,		1,		0,		0,		0,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		1,		0,		0,		0,		0,		0,		0,		0},
			/*8*/		  { 0,		0,		0,		0,		1,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0},
			/*9*/		  { 0,		0,		0,		1,		0,		0,		0,		0,		0, 		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0},
			/*10*/		  { 0,		0,		0,		0,		0,		1,		0,		0,		0,		1, 		0,		0,		0,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0},
			/*11*/		  { 1,		0,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0},
			/*12*/		  { 0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		1,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0},
			/*13*/		  { 0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		1,		0,		1,		0,		0,		0,		0,		1,		0,		0,		0,		0,		0},
			/*14*/		  { 0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		1,		0,		0,		1,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		0},
			/*15*/		  { 0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		1,		0,		0,		0,		1,		0,		0,		0,		0,		0,		0},
			/*16*/		  { 0,		0,		0,		0,		0,		0,		0,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		1,		1,		0,		0,		0,		0,		0,		0},
			/*17*/		  { 0,		0,		0,		0,		0,		0,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0,		1,		0,		0,		0,		0,		0,		0,		0,		0},
			/*18*/		  { 0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		1,		1,		0,		0,		1,		1,		0,		0,		0,		0},
			/*19*/		  { 0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		1,		0,		0,		0,		0,		1,		0,		0,		0,		0,		1,		1},
			/*20*/		  { 0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		1,		0,		0,		0,		1,		0,		0},
			/*21*/		  { 0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		1,		1,		0},
			/*22*/		  { 0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		1,		1,		0,		0,		0},
			/*23*/		  { 0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		1,		0,		1,		0,		0,		1},
			/*24*/		  { 0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		0,		1,		0,		0,		0,		1,		0}
			}; 
	
	int[][] vistd = new int[25][25];




	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	
	public Shortest_path() {

		for (int a = 0; a < 25; a++) {
			for (int b = 0; b < 25; b++) {
				if (matrix[a][b] == 0)
					matrix[a][b] = 99999;
			}
		}

		for (int src = 0; src < 25; src++) {
			distance[src] = matrix[src];
			vistd[src][0] = 1;
			distance[src][src] = 0;

			for (int counter = 0; counter < 25; counter++) {
				min = 99999;
				for (int i = 0; i < 25; i++) {
					if (min > distance[src][i] && vistd[src][i] != 1) {
						min = distance[src][i];
						nextNode = i;
					}
				}
				vistd[src][nextNode] = 1;
				for (int i = 0; i < 25; i++) {
					if (vistd[src][i] != 1) {
						if (min + matrix[nextNode][i] < distance[src][i]) {
							distance[src][i] = min + matrix[nextNode][i];
							preD[src][i] = nextNode;
						}
					}
				}
			}
		}

		for (int a = 0; a < 25; a++) {
			for (int b = 0; b < 25; b++) {
				distance[b][a] = distance[a][b];
			}
		}

		for (int src = 0; src < 25; src++) {
			int j;
			for (int i = 0; i < 25; i++) {
				j = i;
				ArrayList temp = new ArrayList();
				temp.clear();
				temp.add(i);
				while (j != 0) {
					j = preD[src][j];
					if (j != 0)
						temp.add(j);
				}
				temp.add(src);
				Collections.reverse(temp);
				shortestpath.add(temp);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public ArrayList getShortestPath(int src, int dst) {
		return shortestpath.get(src * 25 + dst);
	}

	public int getMat(int src, int dst) {
		return matrix[src][dst];
	}

	public String cityName(int cid) {
		switch (cid) {
		case 0:
			return "Northborough, MA";
		case 1:
			return "Edison, NJ";
		case 2:
			return "Pittsburgh, PA";
		case 3:
			return "Allentown, PA";
		case 4:
			return "Martinsburg, WV";
		case 5:
			return "Charlotte, NC";
		case 6:
			return "Atlanta, GA";
		case 7:
			return "Orlando, FL";
		case 8:
			return "Memphis, TN";
		case 9:
			return "Grove City, OH";
		case 10:
			return "Indianapolis, IN";
		case 11:
			return "Detroit, MI";
		case 12:
			return "New Berlin, WI";
		case 13:
			return "Minneapolis, MN";
		case 14:
			return "St. Louis, MO";
		case 15:
			return "Kansas, KS";
		case 16:
			return "Dallas, TX";
		case 17:
			return "Houston, TX";
		case 18:
			return "Denver, CO";
		case 19:
			return "Salt Lake City, UT";
		case 20:
			return "Phoenix, AZ";
		case 21:
			return "Los Angeles, CA";
		case 22:
			return "Chino, CA";
		case 23:
			return "Sacramento, CA";
		case 24:
			return "Seattle, WA";
		default:
			return "City Error";
		}
	}

}