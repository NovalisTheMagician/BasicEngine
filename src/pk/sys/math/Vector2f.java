package pk.sys.math;

public class Vector2f
{
	public static final Vector2f UNIT_X = new Vector2f(1.0f, 0.0f);
	public static final Vector2f UNIT_Y = new Vector2f(0.0f, 1.0f);
	
	public float x, y;
	
	public Vector2f()
	{
		x = y = 0.0f;
	}
	
	public Vector2f(float f)
	{
		x = y = f;
	}
	
	public Vector2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2f(Vector2f v)
	{
		this.x = v.x;
		this.y = v.y;
	}
	
	/////////////////////////////////////////////////////////////
	// MEMBER-FUNCS
	/////////////////////////////////////////////////////////////
	
	public Vector2f add(Vector2f v)
	{
		return new Vector2f(x + v.x, y + v.y);
	}
	
	public Vector2f sub(Vector2f v)
	{
		return new Vector2f(x - v.x, y - v.y);
	}
	
	public Vector2f mul(float c)
	{
		return new Vector2f(x * c, y * c);
	}
	
	public float dot(Vector2f v)
	{
		return ((x * v.x) + (y * v.y));
	}
	
	public Vector2f negate()
	{
		return new Vector2f(-x, -y);
	}
	
	public float length()
	{
		return (float)(Math.sqrt((x * x) + (y * y)));
	}
	
	public Vector2f normalize()
	{
		Vector2f r = new Vector2f(0.0f);
		float l = this.length();
		
		r.x = x / l;
		r.y = y / l;
		
		return r;
	}
	
	/////////////////////////////////////////////////////////////
	// CLASS-FUNCS
	/////////////////////////////////////////////////////////////
	
	public static Vector2f add(Vector2f v1, Vector2f v2)
	{
		Vector2f ret = new Vector2f();
		ret.x = v1.x + v2.x;
		ret.y = v1.y + v2.y;
		return ret;
	}
	
	public static Vector2f sub(Vector2f v1, Vector2f v2)
	{
		Vector2f ret = new Vector2f();
		ret.x = v1.x - v2.x;
		ret.y = v1.y - v2.y;
		return ret;
	}
	
	public static Vector2f mul(Vector2f v, float c)
	{
		Vector2f ret = new Vector2f();
		ret.x = v.x * c;
		ret.y = v.y * c;
		return ret;
	}
	
	public static float dot(Vector2f v1, Vector2f v2)
	{
		return ((v1.x * v2.x) + (v1.y * v2.y));
	}
	
	public static float length(Vector2f v)
	{
		return (float)(Math.sqrt((v.x * v.x) + (v.y * v.y)));
	}
	
	public static Vector2f negate(Vector2f v)
	{
		return new Vector2f(-v.x, -v.y);
	}
	
	public static Vector2f normalize(Vector2f v)
	{
		Vector2f r = new Vector2f(0.0f);
		float l = v.length();
		
		r.x = v.x / l;
		r.y = v.y / l;
		
		return r;
	}
}
