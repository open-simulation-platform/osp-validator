package fmu;

public class Plug extends Connector 
{
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Plug:\n");
		sb.append(super.toString());

		return sb.toString();
	}
	
}
