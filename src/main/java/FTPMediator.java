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
	public void open(FTPClient client,String path, ArrayList<String> Directories) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException
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
				client.changeDirectory(client.currentDirectory()+"/"+path);
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
	public void back(FTPClient client) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException
	{
		client.changeDirectoryUp();
	}
	//__________________________
	//������� � ���������� �����
	
	
	
	//���������� �����
	//________________
	public void load(FTPClient client, String name,  ArrayList<String> Files ) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException 
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
			client.download(name, new java.io.File(name));
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
