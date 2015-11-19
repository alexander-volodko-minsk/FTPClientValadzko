import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

public abstract class FTPRunner 
{

	//������������� ��������
    static String command;
	
    //����������� ����� ����� ������, � �������� ��������� �������� ���������
	public static String readCommand(String message)
	{
		System.out.println(message);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		return command=scan.nextLine();
	}
	
	public static void main(String[] args) throws SocketException, IOException, IllegalStateException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException, FTPListParseException 
	{
        //������ ������ � ��������� ������� � ������������
		String adress=readCommand("Enter the adress");
		String logName=readCommand("Enter the log name");
		String password=readCommand("Enter the password");
		//����������� � �������
		FTPMediator startClient=new FTPMediator(adress,logName,password);
		//�������� �������
		FTPClient client=startClient.connect();
		
		//����������� �������
		for(;;)
		{
			
			//���������� ��������
			//___________________
			Page CurrentPage = new Page();
			//���� ���������� � ���������� ��������
			CurrentPage.collectInfo(client.list());
			//����� ���������� �� �����
			CurrentPage.showInfo();
			//___________________
			//���������� ��������
			
			
			//��������� �������
			//_________________
			String nextAct=readCommand("Please, choose action");

			//�������� ���������� ��������
			boolean valid=CurrentPage.validCommand(command);
			
			//���� �������� �� ���������
			if(valid!=true)
			{
				System.out.println("Incorrect command");
			}
			
			//���� �������� ���������
			else
			{
				
				if(nextAct.equals("open"))
				{
					//������ �������� ��������� �����
					String nextDir=readCommand("input directory name");			
					
					//�������� ��������� ��� ��������� �����
					startClient.open(client, nextDir, CurrentPage.DirNames);
				}
				
				else if(nextAct.equals("back"))
				{
					//������� � ���������� �����
					startClient.back(client);
				}
				
				else if(nextAct.equals("load"))
				{
					//������ ����� ���������� �����
					String fileToDownload=readCommand("input file name");
					
					//�������� ������� � ������������ ��������� ���� �����
					//CurrentPage.collectInfo(client.list());
					
					//���������� �����
					startClient.load(client, fileToDownload, CurrentPage.FileNames);
				}
				
				else if(nextAct.equals("stop"))
				{
					//����� �� ���������
					startClient.disconnect();
				}
				
				else
				{
					System.out.println("Incorrect command");
				}
			}
			//_________________
			//��������� �������
		}
	}
}
