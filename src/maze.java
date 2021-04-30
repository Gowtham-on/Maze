import java.util.LinkedList;

public class maze {
	
	static int[][] maze = {
			{2,0,0,5},
			{1,1,1,1},
			{0,1,0,1},
			
	};
	//5 - Starting position
	//0 - Wall
	//1 - Path
	//2 - destination
	
	static LinkedList<Position> path = new LinkedList<Position>();
	
	public static void main(String[] args) {
		Position p = new Position(0,3);
		path.push(p);
	
		while(true) {
			int y = path.peek().y;
			int x = path.peek().x;
			
			maze[path.peek().y][x] = 0;
			
			//down
			if(isValid(y+1,x)) {
				if(maze[y+1][x]==2) {
					System.out.println("Moved down. You won!");
					return;
					}else if(maze[y+1][x]==1) {
					path.push(new Position(y+1,x));
					System.out.println("Moved down");
					continue;
				}
			}
			
			//left
			if(isValid(y,x-1)) {
				if(maze[y][x-1]==2) {
				System.out.println("Moved left. You won!");
				return;
				}else if(maze[y][x-1]==1) {
				path.push(new Position(y,x-1));
				System.out.println("Moved left");
				continue;
				}
			}
			
			
			
			//right
			if(isValid(y,x+1)) {
				if(maze[y][x+1]==2) {
				System.out.println("Moved right. You won!");
				return;
				}else if(maze[y][x+1]==1) {
				path.push(new Position(y,x+1));
				System.out.println("Moved right");
				continue;
				}
			}
			
			//up
			if(isValid(y-1,x)) {
				if(maze[y-1][x]==2) {
				System.out.println("Moved up. You won!");
				return;
				}else if(maze[y-1][x]==1) {
				path.push(new Position(y-1,x));
				System.out.println("Moved up");
				continue;
			}
			}
			
			path.pop();
			System.out.println("Moved back");
			if(path.size()<=0) {
				System.out.println("No path");
				return;
			}
		}
		
	}
	public static boolean isValid(int y, int x) {
		if(y < 0 ||
				y >= maze.length|| 
				x < 0 || 
				x>=maze[y].length) {
			return false;
		}
		return true;
	}

}
