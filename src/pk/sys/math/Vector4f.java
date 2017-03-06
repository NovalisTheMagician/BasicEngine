package pk.sys.math;

public class Vector4f 
{
	public float x, y, z, w;
	
	public Vector4f()
	{
		x = y = z = w = 0;
	}
	
	public Vector4f(float f)
	{
		x = y = z = w = f;
	}
	
	public Vector4f(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Vector4f(Vector4f v)
	{
		x = v.x;
		y = v.y;
		z = v.z;
		w = v.w;
	}
	
	public Vector4f(Vector3f v)
	{
		x = v.x;
		y = v.y;
		z = v.z;
		w = 0.0f;
	}
	
	public Vector4f(Vector2f v1, Vector2f v2)
	{
		x = v1.x;
		y = v1.y;
		z = v2.x;
		w = v2.y;
	}
	
	/////////////////////////////////////////////////////////////
	// MEMBER-FUNCS
	/////////////////////////////////////////////////////////////
	
	
}
