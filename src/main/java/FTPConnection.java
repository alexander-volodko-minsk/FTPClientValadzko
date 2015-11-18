import java.io.IOException;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

public class FTPConnection 
{
	private String connectAdress;
	private String logName;
	private String password;
	
	//Конструктор класса, в качестве параметра передаем адресс
	public FTPConnection(String connectAdress, String logName, String password)
	{
		this.connectAdress=connectAdress;
		this.logName=logName;
		this.password=password;	
	}
	
	//подключение к указанному адресу
	public FTPClient connect() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException 
	{
		//Создание FTP-client, connection и login
		FTPClient client = new FTPClient();
		client.connect(connectAdress);
		client.login(logName, password);
		return client;
	}

}
