import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class mazeSolver {
	
	//5 - Starting position
	//0 - Wall
	//1 - Path
	//2 - destination

	
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<maze> mazes = readMazes(); 
		
		
		int i =0;
		while(i<mazes.size()) {
			if(solvingMaze(mazes.get(i))) {
			System.out.println("You Won!!");
			}else {
			System.out.println("No Path");
			}
			i++;
		}
		
	}
	
	private static ArrayList<maze> readMazes() throws FileNotFoundException {
		ArrayList<maze> mazes = new ArrayList<maze>();
		
		Scanner in = new Scanner(new File("maze.txt"));
		while(in.hasNext()) {
			maze m = new maze();
			
			int rows = Integer.parseInt(in.nextLine());
			m.maze = new int[rows][];

			for(int i = 0;i<rows;i++) {
				String line = in.nextLine();		
				m.maze[i] = Arrays.stream(line.split(", ")).mapToInt(Integer::parseInt).toArray();
			}
			
			m.start = new Position(Integer.parseInt(in.nextLine()),Integer.parseInt(in.nextLine()));
			m.path = new LinkedList<Position>();
			mazes.add(m);
			in.nextLine();
		}
		return mazes;
	}

	private static boolean solvingMaze(maze m) {
		Position p = m.start;
		m.path.push(p);
		
		while(true) {
			int y = m.path.peek().y;
			int x = m.path.peek().x;
			
			m.maze[y][x] = 0;
			
			//down
			if(isValid(y+1,x,m)) {
				if(m.maze[y+1][x]==2) {
					System.out.println("Moved down");
					return true;
					}else if(m.maze[y+1][x]==1) {
					m.path.push(new Position(y+1,x));
					System.out.println("Moved down");
					continue;
				}
			}
			
			//left
			if(isValid(y,x-1,m)) {
				if(m.maze[y][x-1]==2) {
				System.out.println("Moved left");
				return true;
				}else if(m.maze[y][x-1]==1) {
				m.path.push(new Position(y,x-1));
				System.out.println("Moved left");
				continue;
				}
			}
			
			//right
			if(isValid(y,x+1,m)) {
				if(m.maze[y][x+1]==2) {
				System.out.println("Moved right");
				return true;
				}else if(m.maze[y][x+1]==1) {
				m.path.push(new Position(y,x+1));
				System.out.println("Moved right");
				continue;
				}
			}
			
			//up
			if(isValid(y-1,x,m)) {
				if(m.maze[y-1][x]==2) {
				System.out.println("Moved up");
				return true;
				}else if(m.maze[y-1][x]==1) {
				m.path.push(new Position(y-1,x));
				System.out.println("Moved up");
				continue;
			}
			}
			
			m.path.pop();
			System.out.println("Moved back");
			if(m.path.size()<=0)
				return false;
			}
		}
	
	public static boolean isValid(int y, int x, maze m) {
		if(y < 0 ||
				y >= m.maze.length|| 
				x < 0 || 
				x>=m.maze[y].length) {
			return false;
		}
		return true;
	}

}
