
public class Contributor 
{
	private boolean win;
	private boolean endGame;
	private boolean shotHit;
	private int[] shot;
	
	public int[] getShot()
	{
		return shot;
	}
	
	public void setShot(int[] shotCoordinate)
	{
		shot = shotCoordinate;
	}
	
	public boolean HitOrMiss()
	{
		return true;
	}
	
	public boolean winCheck(Computer c1, Player p1)
	{
		return false;
	}
	
	public boolean getWin()
	{
		return win;
	}
	
	public void setWin(boolean b)
	{
		win = b;
	}
	
	public boolean getEndGame()
	{
		return endGame;
	}
	
	public void setEndGame(boolean end)
	{
		endGame = end;
	}	
}
