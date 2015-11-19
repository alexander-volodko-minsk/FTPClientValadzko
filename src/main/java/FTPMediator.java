import java.io.IOException;
import java.util.ArrayList;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

public class FTPMediator
{
	//�������� �������
	private String connectAdress;
	//user name
	private String logName;
	//password
	private String password;
	
	
	//����������� ������, � �������� ��������� �������� ������
	//________________________________________________________
	public FTPMediator(String connectAdress, String logName, String password)
	{
		this.connectAdress=connectAdress;
		this.logName=logName;
		this.password=password;	
	}
	//________________________________________________________
	//����������� ������, � �������� ��������� �������� ������
	
	
	
	//����������� � ���������� ������
	//_______________________________
	public FTPClient connect() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException 
	{
		//�������� FTP-client, connection � login
		FTPClient client = new FTPClient();
		client.connect(connectAdress);
		client.login(logName, password);
		return client;
	}
	//_______________________________
	//����������� � ���������� ������
	
	
	//�������� ����� �����
	//____________________
	public void open(FTPClient client,String path, ArrayList<String> Directories)
	{
		    //�����, ������������ ���� �� ��������� ����� � ������ ���������
			boolean flag=false;
			
			//���������� ��������� ����� �� ������� ������������
			for(String directory : Directories )
			{
				if(directory.equals(path))
				{
					flag=true;
				}
			}
			if(flag==true)
			{
				//��������� � ����� �����
				try 
				{
					client.changeDirectory(client.currentDirectory()+"/"+path);
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
	//�������� ����� �����
	
	
	
	//������� � ���������� �����
	//__________________________
	public void back(FTPClient client) 
	{
		try 
		{
			client.changeDirectoryUp();
		} 
		catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException e) 
		{
			e.printStackTrace();
		}
	}
	//__________________________
	//������� � ���������� �����
	
	
	
	//���������� �����
	//________________
	public void load(FTPClient client, String name,  ArrayList<String> Files ) 
	{
		//�����, ������������ ���� �� ��������� ���� � ������ ���������
		boolean flag=false;
		
		//���������� ��������� ���� �� ������� ������������
		for(String file : Files )
		{
			if(file.equals(name))
			{
				flag=true;
			}
		}
		if(flag==true)
		{
			//��������� � ����� �����
			try 
			{
				client.download(name, new java.io.File(name));
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
	//���������� �����
		
	//����� �� ���������
	//__________________
	public void disconnect() 
	{
		System.out.println("Good Luck");
		System.exit(0);
	}
	//__________________
	//����� �� ���������
}
