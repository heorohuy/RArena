package rarena.util;

public class Point4D {

	public final int X;
	public final int Y;
	public final int Z;
	public final int Dimension;
	
	public Point4D(int x, int y, int z, int dimension)
	{
		X = x;
		Y = y;
		Z = z;
		Dimension = dimension;
	}

	@Override
	public int hashCode()
	{
		// Auto-generated hash function - good enough for now
		final int prime = 31;
		int result = 1;
		result = prime * result + Dimension;
		result = prime * result + X;
		result = prime * result + Y;
		result = prime * result + Z;
		return result;
	}

	@Override
	public boolean equals(Object other)
	{
		return equals((Point4D) other);
	}
	
	public boolean equals(Point4D other)
	{
		if (Dimension != other.Dimension)
			return false;
		if (X != other.X)
			return false;
		if (Y != other.Y)
			return false;
		if (Z != other.Z)
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return "(" + X + ", " + Y + ", " + Z + ", " + Dimension + ")"; 
	}
}
