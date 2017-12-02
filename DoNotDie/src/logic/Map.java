package logic;

public class Map
{
	public Room current;
	private Room [] layout;
	
	
	// Default constructor. So far only creates one single map, hard-coded
	// To have a procedurally generated map soon
	public Map ()
	{
		layout = new Room [14];
		
		layout [0] = new Room (0, 1, 5, -1, 8, -1, 1, -1, 2);
		layout [1] = new Room (0, 0, 5, -1, 0, -1, -1, -1, 4);
		layout [2] = new Room (2, 2, 5, -1, -1, -1, 3, 0, -1);
		layout [3] = new Room (1, 1, 5, -1, 2, -1, 4, 5, -1);
		layout [4] = new Room (0, 1, 5, -1, 3, -1, -1, 1, -1);
		layout [5] = new Room (0, 0, 5, -1, -1, -1, 6, 13, 3);
		layout [6] = new Room (0, 1, 5, 7, 5, -1, -1, -1, -1);
		layout [7] = new Room (0, 0, 5, -1, -1, 6, -1, -1, -1);
		layout [8] = new Room (1, 1, 5, -1, 9, -1, 0, -1, 10);
		layout [9] = new Room (0, 0, 5, -1, -1, -1, 8, -1, 11);
		layout [10] = new Room (1, 1, 5, -1, 11, 12, -1, 8, -1);
		layout [11] = new Room (1, 1, 5, -1, -1, -1, 10, 9, -1);
		layout [12] = new Room (2, 2, 5, 10, -1, -1, -1, -1, -1);
		layout [13] = new Room (2, 3, 5, -1, -1, -1, -1, -1, 5);
		
		current = layout [0];
	}
	
	// Movement methods. Change the current room to that of the specified direction, linked list style
	public void moveBack ()
	{
		if (current.connections [0] >= 0)
			current = layout [current.connections [0]];
	}
	public void moveLeft ()
	{
		if (current.connections [1] >= 0)
			current = layout [current.connections [1]];
	}
	public void moveCenter ()
	{
		if (current.connections [2] >= 0)
			current = layout [current.connections [2]];
	}
	public void moveRight ()
	{
		if (current.connections [3] >= 0)
			current = layout [current.connections [3]];
	}
	public void moveUp ()
	{
		if (current.connections [4] >= 0)
			current = layout [current.connections [4]];
	}
	public void moveDown ()
	{
		if (current.connections [5] >= 0)
			current = layout [current.connections [5]];
	}

	// Checks if all rooms have been cleared. Returns true if so, false if not
	public Boolean allCleared ()
	{
		for (int i = 0; i < layout.length; i++)
			if (!layout [i].roomCleared ())
				return false;
		return true;
	}
}
