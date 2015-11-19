import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

public class FTPMediator
{
	//название портала
	private String connectAdress;
	//user name
	private String logName;
	//password
	private String password;
	
	
	//Конструктор класса, в качестве параметра передаем адресс
	//________________________________________________________
	public FTPMediator(String connectAdress, String logName, String password)
	{
		this.connectAdress=connectAdress;
		this.logName=logName;
		this.password=password;	
	}
	//________________________________________________________
	//Конструктор класса, в качестве параметра передаем адресс
	
	
	
	//Подключение к указанному адресу
	//_______________________________
	public FTPClient connect() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException 
	{
		//Создание FTP-client, connection и login
		FTPClient client = new FTPClient();
		client.connect(connectAdress);
		client.login(logName, password);
		return client;
	}
	//_______________________________
	//Подключение к указанному адресу
	
	
	//Открытие новой папки
	//____________________
	public void open(FTPClient client,String path, ArrayList<String> Directories) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException
	{
		    //метка, показывающая есть ли указанная папка в списке доступных
			boolean flag=false;
			
			//сравниваем указанную папку со списком существующих
			for(String directory : Directories )
			{
				if(directory.equals(path))
				{
					flag=true;
				}
			}
			if(flag==true)
			{
				//переходим в новую папку
				client.changeDirectory(client.currentDirectory()+"/"+path);
				return;
			}
			else
			{
				System.out.println("Incorrect directory");
			}
	}
	//____________________
	//Открытие новой папки
	
	
	
	//Возврат в предыдущую папку
	//__________________________
	public void back(FTPClient client) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException
	{
		client.changeDirectoryUp();
	}
	//__________________________
	//Возврат в предыдущую папку
	
	
	
	//Скачивание файла
	//________________
	public void load(FTPClient client, String name,  ArrayList<String> Files ) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException 
	{
		//метка, показывающая есть ли указанный файл в списке доступных
		boolean flag=false;
		
		//сравниваем указанный файл со списком существующих
		for(String file : Files )
		{
			if(file.equals(name))
			{
				flag=true;
			}
		}
		if(flag==true)
		{
			//переходим в новую папку
			client.download(name, new java.io.File(name));
		    System.out.println("downloaded");
		}
		else
	    {
			System.out.println("Incorrect file name");
		}
	}
	//________________
	//Скачивание файла
		
	//Выход из программы
	//__________________
	public void disconnect() 
	{
		System.out.println("Good Luck");
		System.exit(0);
	}
	//__________________
	//Выход из программы
}
