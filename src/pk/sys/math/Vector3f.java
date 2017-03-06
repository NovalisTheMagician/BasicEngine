package pk.sys.math;

public class Vector3f 
{
	public static final Vector3f UNIT_X = new Vector3f(1.0f, 0.0f, 0.0f);
	public static final Vector3f UNIT_Y = new Vector3f(0.0f, 1.0f, 0.0f);
	public static final Vector3f UNIT_Z = new Vector3f(0.0f, 0.0f, 1.0f);
	
	public float x, y, z;
	
	public Vector3f()
	{
		x = y = z = 0.0f;
	}
	
	public Vector3f(float f)
	{
		x = y = z = f;
	}
	
	public Vector3f(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f(Vector3f v)
	{
		x = v.x;
		y = v.y;
		z = v.z;
	}
	
	public Vector3f(Vector2f v)
	{
		x = v.x;
		y = v.y;
		z = 0.0f;
	}
	
	public Vector3f(Vector2f v, float z)
	{
		this.x = v.x;
		this.y = v.y;
		this.z = z;
	}
	
	/////////////////////////////////////////////////////////////
	// MEMBER-FUNCS
	/////////////////////////////////////////////////////////////
	
	public void add(Vector3f v)
	{
		x += v.x;
		y += v.y;
		z += v.z;
	}
	
	public void sub(Vector3f v)
	{
		x -= v.x;
		y -= v.y;
		z -= v.z;
	}
	
	public void mul(float c)
	{
		x *= c;
		y *= c;
		z *= c;
	}
	
	public float dot(Vector3f v)
	{
		return ((x * v.x) + (y * v.y) + (z * v.z));
	}
	
	public Vector3f cross(Vector3f v)
	{
		Vector3f ret = new Vector3f();
		ret.x = y * v.z - z * v.y;
		ret.y = z * v.x - x * v.z;
		ret.z = x * v.y - y * v.x;
		return ret;
	}
	
	public float length()
	{
		return (float)(Math.sqrt((x * x) + (y * y) + (z * z)));
	}
	
	/////////////////////////////////////////////////////////////
	// CLASS-FUNCS
	/////////////////////////////////////////////////////////////
	
	public static Vector3f add(Vector3f v1, Vector3f v2)
	{
		Vector3f ret = new Vector3f();
		ret.x = v1.x + v2.x;
		ret.y = v1.y + v2.y;
		ret.z = v1.z + v2.z;
		return ret;
	}
	
	public static Vector3f sub(Vector3f v1, Vector3f v2)
	{
		Vector3f ret = new Vector3f();
		ret.x = v1.x - v2.x;
		ret.y = v1.y - v2.y;
		ret.z = v1.z - v2.z;
		return ret;
	}
	
	public static Vector3f mul(Vector3f v, float c)
	{
		Vector3f ret = new Vector3f();
		ret.x = v.x * c;
		ret.y = v.y * c;
		ret.z = v.z * c;
		return ret;
	}
	
	public static float dot(Vector3f v1, Vector3f v2)
	{
		return ((v1.x * v2.x) + (v1.y * v2.y) + (v1.z * v2.z));
	}
	
	public static Vector3f cross(Vector3f v1, Vector3f v2)
	{
		Vector3f ret = new Vector3f();
		ret.x = v1.y * v2.z - v1.z * v2.y;
		ret.y = v1.z * v2.x - v1.x * v2.z;
		ret.z = v1.x * v2.y - v1.y * v2.x;
		return ret;
	}
	
	public static float length(Vector3f v)
	{
		return (float)(Math.sqrt((v.x * v.x) + (v.y * v.y) + (v.z * v.z)));
	}
	
	public static Vector3f negate(Vector3f v)
	{
		return new Vector3f(-v.x, -v.y, -v.z);
	}
}
