package pk.sys.math;

public class Matrix3x3f 
{
	public static final Matrix3x3f IDENTITY = new Matrix3x3f(1, 0, 0, 0, 1, 0, 0, 0, 1);
	
	public float m00, m01, m02;
	public float m10, m11, m12;
	public float m20, m21, m22;
	
	public Matrix3x3f()
	{
		m00 = m01 = m02 = 0.0f;
		m10 = m11 = m12 = 0.0f;
		m20 = m21 = m22 = 0.0f;
	}
	
	public Matrix3x3f	(float m00, float m01, float m02,
						float m10, float m11, float m12,
						float m20, float m21, float m22)
	{
		this.m00 = m00; this.m01 = m01; this.m02 = m02;
		this.m10 = m10; this.m11 = m11; this.m12 = m12;
		this.m20 = m20; this.m21 = m21; this.m22 = m22;
	}
	
	public Matrix3x3f(Matrix3x3f m)
	{
		m00 = m.m00; m01 = m.m01; m02 = m.m02;
		m10 = m.m10; m11 = m.m11; m12 = m.m12;
		m20 = m.m20; m21 = m.m21; m22 = m.m22;
	}
	
	/////////////////////////////////////////////////////////////
	// MEMBER-FUNCS
	/////////////////////////////////////////////////////////////
	
	public Matrix3x3f add(Matrix3x3f m)
	{
		Matrix3x3f ret = new Matrix3x3f();
		
		ret.m00 = m00 + m.m00; ret.m01 = m01 + m.m01; ret.m02 = m02 + m.m02;
		ret.m10 = m10 + m.m10; ret.m11 = m11 + m.m11; ret.m12 = m12 + m.m12;
		ret.m20 = m20 + m.m20; ret.m21 = m21 + m.m21; ret.m22 = m22 + m.m22;
		
		return ret;
	}
	
	public Matrix3x3f sub(Matrix3x3f m)
	{
		Matrix3x3f ret = new Matrix3x3f();
		
		ret.m00 = m00 - m.m00; ret.m01 = m01 - m.m01; ret.m02 = m02 - m.m02;
		ret.m10 = m10 - m.m10; ret.m11 = m11 - m.m11; ret.m12 = m12 - m.m12;
		ret.m20 = m20 - m.m20; ret.m21 = m21 - m.m21; ret.m22 = m22 - m.m22;
		
		return ret;
	}
	
	public Matrix3x3f negate()
	{
		Matrix3x3f ret = new Matrix3x3f();
		
		ret.m00 = -m00; ret.m01 = -m01; ret.m02 = -m02;
		ret.m10 = -m10; ret.m11 = -m11; ret.m12 = -m12;
		ret.m20 = -m20; ret.m21 = -m21; ret.m22 = -m22;
		
		return ret;
	}
	
	public Matrix3x3f mul(float c)
	{
		Matrix3x3f ret = new Matrix3x3f();
		
		ret.m00 = m00 * c; ret.m01 = m01 * c; ret.m02 = m02 * c;
		ret.m10 = m10 * c; ret.m11 = m11 * c; ret.m12 = m12 * c;
		ret.m20 = m20 * c; ret.m21 = m21 * c; ret.m22 = m22 * c;
		
		return ret;
	}
	
	public Matrix3x3f mul(Matrix3x3f m)
	{
		Matrix3x3f ret = new Matrix3x3f();
		
		ret.m00 = (m00 * m.m00) + (m01 * m.m10) + (m02 * m.m20);
		ret.m01 = (m00 * m.m01) + (m01 * m.m11) + (m02 * m.m21);
		ret.m02 = (m00 * m.m02) + (m01 * m.m12) + (m02 * m.m22);
		
		ret.m10 = (m10 * m.m00) + (m11 * m.m10) + (m12 * m.m20);
		ret.m11 = (m10 * m.m01) + (m11 * m.m11) + (m12 * m.m21);
		ret.m12 = (m10 * m.m02) + (m11 * m.m12) + (m12 * m.m22);
		
		ret.m20 = (m20 * m.m00) + (m21 * m.m10) + (m22 * m.m20);
		ret.m21 = (m20 * m.m01) + (m21 * m.m11) + (m22 * m.m21);
		ret.m22 = (m20 * m.m02) + (m21 * m.m12) + (m22 * m.m22);
		
		return ret;
	}
	
	/////////////////////////////////////////////////////////////
	// CLASS-FUNCS
	/////////////////////////////////////////////////////////////
	
	public static Matrix3x3f add(Matrix3x3f m1, Matrix3x3f m2)
	{
		Matrix3x3f ret = new Matrix3x3f();
		
		ret.m00 = m1.m00 + m2.m00; ret.m01 = m1.m01 + m2.m01; ret.m02 = m1.m02 + m2.m02;
		ret.m10 = m1.m10 + m2.m10; ret.m11 = m1.m11 + m2.m11; ret.m12 = m1.m12 + m2.m12;
		ret.m20 = m1.m20 + m2.m20; ret.m21 = m1.m21 + m2.m21; ret.m22 = m1.m22 + m2.m22;
		
		return ret;
	}
	
	public static Matrix3x3f sub(Matrix3x3f m1, Matrix3x3f m2)
	{
		Matrix3x3f ret = new Matrix3x3f();
		
		ret.m00 = m1.m00 - m2.m00; ret.m01 = m1.m01 - m2.m01; ret.m02 = m1.m02 - m2.m02;
		ret.m10 = m1.m10 - m2.m10; ret.m11 = m1.m11 - m2.m11; ret.m12 = m1.m12 - m2.m12;
		ret.m20 = m1.m20 - m2.m20; ret.m21 = m1.m21 - m2.m21; ret.m22 = m1.m22 - m2.m22;
		
		return ret;
	}
	
	public static Matrix3x3f negate(Matrix3x3f m)
	{
		Matrix3x3f ret = new Matrix3x3f();
		
		ret.m00 = -m.m00; ret.m01 = -m.m01; ret.m02 = -m.m02;
		ret.m10 = -m.m10; ret.m11 = -m.m11; ret.m12 = -m.m12;
		ret.m20 = -m.m20; ret.m21 = -m.m21; ret.m22 = -m.m22;
		
		return ret;
	}
	
	public static Matrix3x3f mul(Matrix3x3f m, float c)
	{
		Matrix3x3f ret = new Matrix3x3f();
		
		ret.m00 = m.m00 * c; ret.m01 = m.m01 * c; ret.m02 = m.m02 * c;
		ret.m10 = m.m10 * c; ret.m11 = m.m11 * c; ret.m12 = m.m12 * c;
		ret.m20 = m.m20 * c; ret.m21 = m.m21 * c; ret.m22 = m.m22 * c;
		
		return ret;
	}
	
	public static Matrix3x3f mul(Matrix3x3f m1, Matrix3x3f m2)
	{
		Matrix3x3f ret = new Matrix3x3f();
		
		ret.m00 = (m1.m00 * m2.m00) + (m1.m01 * m2.m10) + (m1.m02 * m2.m20);
		ret.m01 = (m1.m00 * m2.m01) + (m1.m01 * m2.m11) + (m1.m02 * m2.m21);
		ret.m02 = (m1.m00 * m2.m02) + (m1.m01 * m2.m12) + (m1.m02 * m2.m22);
		
		ret.m10 = (m1.m10 * m2.m00) + (m1.m11 * m2.m10) + (m1.m12 * m2.m20);
		ret.m11 = (m1.m10 * m2.m01) + (m1.m11 * m2.m11) + (m1.m12 * m2.m21);
		ret.m12 = (m1.m10 * m2.m02) + (m1.m11 * m2.m12) + (m1.m12 * m2.m22);
		
		ret.m20 = (m1.m20 * m2.m00) + (m1.m21 * m2.m10) + (m1.m22 * m2.m20);
		ret.m21 = (m1.m20 * m2.m01) + (m1.m21 * m2.m11) + (m1.m22 * m2.m21);
		ret.m22 = (m1.m20 * m2.m02) + (m1.m21 * m2.m12) + (m1.m22 * m2.m22);
		
		return ret;
	}
	
	@Override
	public Object clone()
	{
		return new Matrix3x3f(this);
	}
}
