package logic;

public class Map
{
	public Map ()
	{
		layout = new Room [11];
		
		layout [0] = new Room (1, 0, 0, -1, 5, 1, -1, -1, -1);
		for (int i = 1; i < 10; i++)
		{
			layout [i] = new Room (0, 0, 0, i - 1, -1, i + 1, -1, -1, -1);
		}
		layout [10] = new Room (0, 0, 0, 9, -1, 0, -1, -1, -1);
		current = layout [0];
	}
	
	public void moveback ()
	{
		if (current.connections [0] >= 0)
			current = layout [current.connections [0]];
	}
	public void moveleft ()
	{
		if (current.connections [1] >= 0)
			current = layout [current.connections [1]];
	}
	public void movecenter ()
	{
		if (current.connections [2] >= 0)
			current = layout [current.connections [2]];
	}
	public void moveright ()
	{
		if (current.connections [3] >= 0)
			current = layout [current.connections [3]];
	}
	public void moveup ()
	{
		if (current.connections [4] >= 0)
			current = layout [current.connections [4]];
	}
	public void movedown ()
	{
		if (current.connections [5] >= 0)
			current = layout [current.connections [5]];
	}
	
	public Room current;
	private Room [] layout;
}
