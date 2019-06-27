package fmu;

public class Socket extends Connector 
{

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Socket:\n");
		sb.append(super.toString());

		return sb.toString();
	}


}
