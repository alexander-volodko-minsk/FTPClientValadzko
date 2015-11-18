import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

public abstract class FTP {
	
	//������������� ��������
    static String command;
	
    //����������� ����� ����� ������
	public static String readCommand(String message)
	{
		System.out.println(message);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		return command=scan.nextLine();
	}
	
	public static void main(String[] args) throws SocketException, IOException, IllegalStateException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException, FTPListParseException 
	{

		String adress=readCommand("Enter the adress");
		String logName=readCommand("Enter the log name");
		String password=readCommand("Enter the password");
		FTPConnection startClient=new FTPConnection(adress,logName,password);
		FTPClient client=startClient.connect();
		
		
		//���������� ��������� ��������
		ShowPage FirstPage = new ShowPage();
		FirstPage.collectInfo(client.list());
		FirstPage.showInfo();
		
		
		//����������� �������
		for(;;)
		{
			String nextAct=readCommand("Please, choose action");
/*
			//�������� ���������� ��������
			boolean flag=false;
			for( String validActions : FirstPage.Commands)
			{
				if(validActions.equals(nextAct))
				{
					flag=true;
				}
			}
			if(flag!=true)
			{
				System.out.println("Incorrect command");
			}
	
			//��������� �������
			//_________________
			else
			{
*/	

			if(nextAct.equals("open"))
			{
				//������ �������� ��������� �����
				String nextDir=readCommand("input directory name");
				
				//�������� ������� � ������������ ��������� ���� �����
				ShowPage DirShow = new ShowPage();
				DirShow.collectInfo(client.list());
				
				//�������� ������� 
				Actions Dir = new Actions();
				
				//�������� ��������� ��� ��������� �����
				Dir.open(client, nextDir, DirShow.DirPath);
				
				//����������� ����� �����
				DirShow.collectInfo(client.list());
				DirShow.showInfo();
			}
			
			else if(nextAct.equals("back"))
			{
				//������� � ���������� �����
				Actions DirBack = new Actions();
				DirBack.back(client);
				//����������� ���������� �����
				ShowPage DirShow = new ShowPage();
				DirShow.collectInfo(client.list());
				DirShow.showInfo();
			}
			
			else if(nextAct.equals("load"))
			{
				//������ ����� ���������� �����
				String fileToDownload=readCommand("input file name");
				
				//�������� ������� � ������������ ��������� ���� �����
				ShowPage DirShow = new ShowPage();
				DirShow.collectInfo(client.list());
				
				//���������� �����
				Actions FileLoad = new Actions();
				FileLoad.load(client, fileToDownload, DirShow.FilePath);
				DirShow.collectInfo(client.list());
				DirShow.showInfo();
			}
			
			else if(nextAct.equals("stop"))
			{
				//����� �� ���������
				Actions Exit = new Actions();
				Exit.disconnect();
			}
			
			else
			{
				System.out.println("Incorrect command");
			}
		//}
		//_________________
		//��������� �������
		}
	}
}
