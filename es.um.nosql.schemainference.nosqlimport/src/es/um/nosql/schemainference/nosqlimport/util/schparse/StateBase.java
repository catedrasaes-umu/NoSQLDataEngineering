package es.um.nosql.schemainference.nosqlimport.util.schparse;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class StateBase {

	private InputStream is;
	
	public StateBase(InputStream is)
	{
		setIs(is);
	}
	
	public void setIs(InputStream is) {
		if (is.markSupported())
			this.is = is;
		else
			this.is = new BufferedInputStream(is);
	}
	
	public InputStream getInputStream()
	{
		return is;
	}
	
	public int peek()
	{
		is.mark(1);
		int c = -1;
		try {
			c = is.read();
		} catch (IOException e) {
		} finally
		{
			try {
				is.reset();
			} catch (IOException e) {
			}
		}

		return c;
	}
	
	public void skipInput(int n)
	{
		try {
			is.skip(n);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
