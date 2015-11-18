import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

public class Actions {
	
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
