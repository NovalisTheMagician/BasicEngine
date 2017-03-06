package main.test;

// JAVA 6
/*
import java.io.File;
import java.io.FileInputStream;
*/
import java.io.IOException;

// JAVA 7
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOTest
{
	public IOTest() {}
	
	public byte[] readFileBinary(String fileName) throws IOException
	{
		// JAVA 7
		return Files.readAllBytes(Paths.get(fileName));
		
		// JAVA 6
		/*
		FileInputStream in = new FileInputStream(new File(fileName));
		byte[] b = new byte[in.available()];
		in.read(b);
		in.close();
		return b;
		*/
	}
	
	public int findFirstOf(byte c, byte[] d)
	{
		for(int i = 0; i < d.length; ++i)
		{
			if(d[i] == c)
				return i;
		}
		return -1;
	}
	
	public int findFirstOf(byte c, byte[] d, int from)
	{
		if(from >= d.length)
			return -1;
		
		for(int i = from; i < d.length; ++i)
		{
			if(d[i] == c)
				return i;
		}
		return -1;
	}
	
	public int findFirstNotOf(byte c, byte[] d)
	{
		for(int i = 0; i < d.length; ++i)
		{
			if(d[i] != c)
				return i;
		}
		return -1;
	}
	
	public int findFirstNotOf(byte c, byte[] d, int from)
	{
		if(from >= d.length)
			return -1;
		
		for(int i = from; i < d.length; ++i)
		{
			if(d[i] != c)
				return i;
		}
		return -1;
	}
	
	public int findLastOf(byte c, byte[] d)
	{
		for(int i = d.length - 1; i >= 0; --i)
		{
			if(d[i] == c)
				return i;
		}
		return -1;
	}
	
	public int findLastOf(byte c, byte[] d, int from)
	{
		for(int i = from; i >= 0; --i)
		{
			if(d[i] == c)
				return i;
		}
		return -1;
	}
	
	public boolean equals(byte[] a, String b)
	{
		if(a.length != b.length())
			return false;
		
		for(int i = 0; i < a.length; ++i)
			if(a[i] != (byte)b.charAt(i))
				return false;
		
		return true;
	}
	
	public boolean equals(byte[] a, byte[] b)
	{
		if(a.length != b.length)
			return false;
		
		for(int i = 0; i < a.length; ++i)
			if(a[i] != b[i])
				return false;
		
		return true;
	}
	
	public byte[] getSub(int from, int to, byte[] d)
	{
		byte[] b = new byte[to - from];
		
		for(int i = 0; i < to - from; ++i)
			b[i] = d[i + from];
		
		return b;
	}
	
	public void print(byte[] d)
	{
		for(byte b : d)
		{
			if((char)b == ' ')
				System.out.print('#');
			else if((char)b == '\n' || (char)b == '\r')
			{
				System.out.print('$');
				System.out.print((char)b);
			}
			else if((char)b == '\t')
			{
				System.out.print('§');
				System.out.print((char)b);
			}
			else
				System.out.print((char)b);
		}
		System.out.print('\n');
	}
	
	public byte[] cutOut(byte[] d, byte c)
	{
		byte[] bs;
		int occurs = 0;
		for(int i = 0; i < d.length; ++i)
		{
			if(d[i] == c)
			{
				occurs++;
				d[i] = -1;
			}
		}
		
		bs = new byte[d.length - occurs];
		
		int dIndex = 0, bIndex = 0;
		while(bIndex < bs.length)
		{
			if(d[dIndex] >= 0)
			{
				bs[bIndex] = d[dIndex];
				bIndex++;
			}
			dIndex++;
		}
		
		return bs;
	}
	
	public void replaceAll(byte w, byte t, byte[] d)
	{
		for(int i = 0; i < d.length; ++i)
			if(d[i] == w)
				d[i] = t;
	}
	
	public short convertToNumber(byte[] d)
	{
		short r = 0;
		int n = 1, i;
		boolean sign;
		
		if(d[0] == (byte)'-')
		{
			sign = true;
			i = 1;
		}
		else
		{
			sign = false;
			i = 0;
		}
		
		for(; i < d.length; ++i)
			n *= 10;
		
		if(sign)
		{
			n = -n;
			i = 1;
		}
		else
			i = 0;
		
		for(; i < d.length; ++i)
		{
			n /= 10;
			r += (d[i] - 48) * n;
		}
		
		return r;
	}
	
	public static void main(String[] args) throws IOException 
	{
		IOTest iotest = new IOTest();
		byte[] bs = iotest.readFileBinary("res/levelE0M0.pkl");
		
		System.out.println(bs.length + " bytes read\n");
		
		System.out.println("Raw filedata: \n");
		for(byte b : bs)
			System.out.print((char)b);
		
		System.out.println("\n\ndata with special characters: \n");
		iotest.print(bs);
		
		System.out.println("\n\nParsing file... \n\n");
		
		Header h = new Header();
		Data d = new Data();
		
		int piv = -1;
		int pivO = piv;
		byte[] sub, sub2 = null;
		
		bs = iotest.cutOut(bs, (byte)'\n');
		bs = iotest.cutOut(bs, (byte)'\r');
		bs = iotest.cutOut(bs, (byte)'\t');
		
		piv = iotest.findFirstOf((byte)'{', bs);
		sub = iotest.getSub(0, piv, bs);
		
		if(!iotest.equals(sub, "W3D_LEVEL"))
		{
			System.out.println("Invalid level-file! Exiting...");
			return;
		}
		
		pivO = piv;
		piv = iotest.findFirstOf((byte)'{', bs, piv + 1);
		
		sub = iotest.getSub(pivO + 1, piv, bs);
		
		if(!iotest.equals(sub, "settings"))
		{
			System.out.println("Missing settings-block! Exiting...");
			return;
		}
		pivO = piv;
		piv = iotest.findFirstOf((byte)'}', bs, piv + 1);
		sub = iotest.getSub(pivO + 1, piv, bs);
		
		
		// HEADER BLOCK
		int a = 0, b = 0;
		byte[] sub3 = null;
		while(true)
		{
			a = iotest.findFirstOf((byte)';', sub, a);
			if(a == -1)
				break;
			
			sub2 = iotest.getSub(b, a, sub);
			sub2 = iotest.cutOut(sub2, (byte)' ');
			
			int c = -1;
			c = iotest.findFirstOf((byte)'=', sub2);
			sub3 = iotest.getSub(0, c, sub2);
			
			if(iotest.equals(sub3, "name"))
			{
				sub3 = iotest.getSub(c + 1, sub2.length, sub2);
				iotest.replaceAll((byte)'#', (byte)' ', sub3);
				h.name = new String(sub3);
			}
			else if(iotest.equals(sub3, "author"))
			{
				sub3 = iotest.getSub(c + 1, sub2.length, sub2);
				iotest.replaceAll((byte)'#', (byte)' ', sub3);
				h.author = new String(sub3);
			}
			else if(iotest.equals(sub3, "date"))
			{
				sub3 = iotest.getSub(c + 1, sub2.length, sub2);
				iotest.replaceAll((byte)'#', (byte)' ', sub3);
				h.date = new String(sub3);
			}
			else if(iotest.equals(sub3, "description"))	
			{
				sub3 = iotest.getSub(c + 1, sub2.length, sub2);
				iotest.replaceAll((byte)'#', (byte)' ', sub3);
				h.description = new String(sub3);
			}
			else if(iotest.equals(sub3, "fogFunc"))
			{
				sub3 = iotest.getSub(c + 1, sub2.length, sub2);
				short f = iotest.convertToNumber(sub3);
				h.fogFunc = f;
			}
			else if(iotest.equals(sub3, "fogStart"))
			{
				sub3 = iotest.getSub(c + 1, sub2.length, sub2);
				short f = iotest.convertToNumber(sub3);
				h.fogStart = f;
			}
			else if(iotest.equals(sub3, "fogEnd"))
			{
				sub3 = iotest.getSub(c + 1, sub2.length, sub2);
				short f = iotest.convertToNumber(sub3);
				h.fogEnd = f;
			}
			else if(iotest.equals(sub3, "fogColor"))
			{
				sub3 = iotest.getSub(c + 1, sub2.length, sub2);
				c = iotest.findFirstOf((byte)',', sub3);
				h.fogColor[0] = iotest.convertToNumber(iotest.getSub(0, c, sub3));
				b = iotest.findFirstOf((byte)',', sub3, c + 1);
				h.fogColor[1] = iotest.convertToNumber(iotest.getSub(c + 1, b, sub3));
				h.fogColor[2] = iotest.convertToNumber(iotest.getSub(b + 1, sub3.length, sub3));
			}
			else if(iotest.equals(sub3, "player"))
			{
				sub3 = iotest.getSub(c + 1, sub2.length, sub2);
				c = iotest.findFirstOf((byte)',', sub3);
				h.playerStart[0] = iotest.convertToNumber(iotest.getSub(0, c, sub3));
				h.playerStart[1] = iotest.convertToNumber(iotest.getSub(c + 1, sub3.length, sub3));
			}
			
			a++;
			b = a;
		}
		
		h.print();
		
		// DATA BLOCK
		pivO = piv;
		piv = iotest.findFirstOf((byte)'{', bs, piv + 1);
		sub = iotest.getSub(pivO + 1, piv, bs);
		
		if(!iotest.equals(sub, "data"))
		{
			System.out.println("Missing data-block! Exiting...");
			return;
		}
		
		pivO = piv;
		piv = iotest.findLastOf((byte)'}', bs);
		sub = iotest.getSub(pivO + 1, piv - 1, bs);
		
		Data.GeomType gt = Data.GeomType.GT_BLOCK;
		a = 0; b = 0; sub3 = null;
		while(true)
		{
			if(a != 0)
				b = iotest.findFirstOf((byte)'}', sub, b) + 1;
			a = iotest.findFirstOf((byte)'{', sub, a);
			if(a == -1)
				break;
			
			sub2 = iotest.getSub(b, a, sub);
			
			if(iotest.equals(sub2, "BLOCK"))
				gt = Data.GeomType.GT_BLOCK;
			else if(iotest.equals(sub2, "FLOOR"))
				gt = Data.GeomType.GT_FLOOR;
			else if(iotest.equals(sub2, "CEILING"))
				gt = Data.GeomType.GT_CEILING;
			
			sub2 = iotest.getSub(a + 1, iotest.findFirstOf((byte)'}', sub, a + 1), sub);
			sub2 = iotest.cutOut(sub2, (byte)' ');
			
			int k = 0, j = 0, i = 0; byte[] sub4 = null;
			while(true)
			{
				k = iotest.findFirstOf((byte)';', sub2, j);
				if(k == -1)
					break;
				
				sub3 = iotest.getSub(j, k, sub2);
				
				int p = iotest.findFirstOf((byte)',', sub3);
				j = p;
				p = iotest.findFirstOf((byte)',', sub3, j + 1);
				
				sub4 = iotest.getSub(0, j, sub3);
				int posX = iotest.convertToNumber(sub4);
				sub4 = iotest.getSub(j + 1, p, sub3);
				int posZ = iotest.convertToNumber(sub4);
				sub4 = iotest.getSub(p + 1, sub3.length, sub3);
				String tex = new String(sub4);
				
				System.out.println(gt.name() + " at " + i + ": X=" + posX + " Z=" + posZ + " Tex=" + tex);
				
				d.insert(gt, posX, posZ, tex, i);
				
				i++;
				k++;
				j = k;
			}
			
			a++;
			b++;
		}
	}
}

///////////////////////////////////////////////
//Container classes

class Header
{
	String name;
	String author;
	String date;
	String description;
	
	short fogFunc;
	short fogStart;
	short fogEnd;
	short[] fogColor;
	
	int[] playerStart;
	
	public Header()
	{
		name = "undefined";
		author = "unknown";
		date = "not present";
		description = "no idea";
		
		fogFunc = 0;
		fogStart = 0;
		fogEnd = 0;
		
		fogColor = new short[3];
		
		playerStart = new int[2];
	}
	
	public void print()
	{
		System.out.print("Name: " + name + "\nAuthor: " + author + "\nDate: " + date + "\nDescription: " + description + "\nFogFunc: " + fogFunc + "\nFogStart: " + fogStart + "\nFogEnd: " + fogEnd);
		for(int i = 0; i < fogColor.length; ++i)
			System.out.print("\nfogColor " + (i == 0 ? "R: " : (i == 1 ? "G: " : "B: ")) + fogColor[i]);
		for(int i = 0; i < playerStart.length; ++i)
			System.out.print("\nplayerStart " + (i == 0 ? "X: " : "Y: ") + playerStart[i]);
		System.out.print("\n");
	}
}

class GeomData
{
	float[] position;
	String texName;
	
	public GeomData()
	{
		position = new float[2];
		texName = "";
	}
}

class Data
{
	public enum GeomType
	{
		GT_BLOCK,
		GT_FLOOR,
		GT_CEILING
	}
	
	public Data()
	{
		blocks = new GeomData[255];
		floors = new GeomData[255];
		ceilings = new GeomData[255];
	}
	
	public void insert(GeomType type, float x, float y, String tex, int index)
	{
		switch(type)
		{
		case GT_BLOCK:
			blocks[index] = new GeomData();
			blocks[index].position[0] = x;
			blocks[index].position[1] = y;
			blocks[index].texName = tex;
			break;
		case GT_FLOOR:
			floors[index] = new GeomData();
			floors[index].position[0] = x;
			floors[index].position[1] = y;
			floors[index].texName = tex;
			break;
		case GT_CEILING:
			ceilings[index] = new GeomData();
			ceilings[index].position[0] = x;
			ceilings[index].position[1] = y;
			ceilings[index].texName = tex;
			break;
		}
	}
	
	GeomData[] blocks;
	GeomData[] floors;
	GeomData[] ceilings;
}
