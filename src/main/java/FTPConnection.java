import java.io.IOException;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

public class FTPConnection 
{
	private String connectAdress;
	private String logName;
	private String password;
	
	//����������� ������, � �������� ��������� �������� ������
	public FTPConnection(String connectAdress, String logName, String password)
	{
		this.connectAdress=connectAdress;
		this.logName=logName;
		this.password=password;	
	}
	
	//����������� � ���������� ������
	public FTPClient connect() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException 
	{
		//�������� FTP-client, connection � login
		FTPClient client = new FTPClient();
		client.connect(connectAdress);
		client.login(logName, password);
		return client;
	}

}
