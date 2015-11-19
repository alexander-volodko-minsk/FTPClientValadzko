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
	private String connectAddress;
	//user name
	private String logName;
	//password
	private String password;
	
	
	//Конструктор класса, в качестве параметра передаем адресс, логин, пароль
	//_______________________________________________________________________
	public FTPMediator(String connectAddress, String logName, String password)
	{
		this.connectAddress=connectAddress;
		this.logName=logName;
		this.password=password;	
	}
	//______________________________________________________________________
	//Конструктор класса, в качестве параметра передаем адресс, логин, пароль
	
	
	
	//Подключение к указанному адресу
	//_______________________________
	public FTPClient connect() 
	{
		//Создание FTP-client, connection и login
		FTPClient ftp4client = new FTPClient();
		try 
		{
			ftp4client.connect(connectAddress);
		} 
		catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException e) 
		{
			System.out.println("Sorry,connection wasn't established! Please, restart the programm with adress value");
			System.exit(0);
		}
		try 
		{
			ftp4client.login(logName, password);
		}
		catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException e) 
		{
			System.out.println("Sorry,connection wasn't established!"
					+ " Please, restart the programm with correct login and password values");
			System.exit(0);
		}
		return ftp4client;
	}
	//_______________________________
	//Подключение к указанному адресу
	
	
	//Открытие новой папки
	//____________________
	public void open(FTPClient ftp4client,String newDirectoryName, ArrayList<String> Directories)
	{
		    //метка, показывающая есть ли указанная папка в списке доступных
			boolean flag=false;
			
			//сравниваем указанную папку со списком существующих
			for(String someDirectory : Directories )
			{
				if(someDirectory.equals(newDirectoryName))
				{
					flag=true;
				}
			}
			if(flag==true)
			{
				//переходим в новую папку
				try 
				{
					ftp4client.changeDirectory(ftp4client.currentDirectory()+"/"+newDirectoryName);
				} 
				catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException e)
				{
					e.printStackTrace();
				}
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
	public void back(FTPClient ftp4client) 
	{
		try 
		{
			ftp4client.changeDirectoryUp();
		} 
		catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException e) 
		{
			e.printStackTrace();
		}
	}
	//__________________________
	//Возврат в предыдущую папку
	
	
	
	//Скачивание файла
	//________________
	public void load(FTPClient ftp4client, String newFileName,  ArrayList<String> Files ) 
	{
		//метка, показывающая есть ли указанный файл в списке доступных
		boolean flag=false;
		
		//сравниваем указанный файл со списком существующих
		for(String someFile : Files )
		{
			if(someFile.equals(newFileName))
			{
				flag=true;
			}
		}
		if(flag==true)
		{
			//переходим в новую папку
			try 
			{
				ftp4client.download(newFileName, new java.io.File(newFileName));
			}
			catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException
					| FTPDataTransferException | FTPAbortedException e) 
			{
				e.printStackTrace();
			}
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
