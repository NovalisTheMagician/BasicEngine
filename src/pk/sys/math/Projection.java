package pk.sys.math;

//import pk.sys.math.*;

public class Projection 
{
	private final float m_min;
	private final float m_max;

	public Projection(float min, float max)
	{
	  m_min = min;
	  m_max = max;
	}
	
	public boolean doesOverlap(final Projection other)
	{
	  if(m_max > other.m_min) 
	    return true;
	
	  if(m_min > other.m_max) 
	    return true;
	
	  return false;
	}
	
	public double getOverlap(final Projection other)
	{
	  if(!doesOverlap(other)) 
	  {
	    return 0;
	  }
	  
	  return 0;
	
	  /*
	  if(m_max > other.m_min) {
	    return Collider.getDistance(m_max, other.m_min);
	  }
	  else if(m_min > other.m_max)
	  {
	    return Collider.getDistance(m_min, other.m_max);
	  }
	  else 
	  {
	    Log.warning("Bad case in getOverlap!");
	    return 0;
	  }
	  */
	}
}
