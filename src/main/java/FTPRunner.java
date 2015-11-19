import java.io.IOException;
import java.util.Scanner;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

public abstract class FTPRunner {

	// ������������� ��������
	static String command;

	// ����������� ����� ����� ������, � �������� ��������� �������� ���������
	public static String readCommand(String message) 
	{
		System.out.println(message);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		return command = scan.nextLine();
	}

	public static void main(String[] args) 
	{
		// ������ ������ � ��������� ������� � ������������
		String address = readCommand("Enter the address");
		String logName = readCommand("Enter the log name");
		String password = readCommand("Enter the password");
		// ����������� � �������
		FTPMediator browser = new FTPMediator(address, logName, password);
		FTPClient ftp4client=browser.connect();
		

		// ����������� �������
		for (;;)
		{

			// ���������� ������� ��������
			// ___________________
			Page CurrentPage = new Page();
			// ���� ���������� � ���������� ��������
			try 
			{
				//� �������� ��������� �������� ������ �������� �� ��������
				CurrentPage.collectInfo(ftp4client.list());
			} 
			catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException
					| FTPDataTransferException | FTPAbortedException | FTPListParseException e)
			{
				e.printStackTrace();
			}
			// ����� ���������� �� �����
			CurrentPage.showInfo();
			// ___________________
			// ���������� ������� ��������
			

			// ��������� �������
			// _________________
			
			// ������ ��������
			String nextAct = readCommand("Please, choose action");
			// �������� ���������� ��������
			boolean valid = CurrentPage.validCommand(command);
			// ���� �������� �� ���������
			if (valid != true)
			{
				System.out.println("Incorrect command");
			}
			// ���� �������� ���������
			else
			{
				if (nextAct.equals("open"))
				{
					// ������ �������� ������������� �����
					String nextDir = readCommand("input directory name");
					// ������ � FTPMediator
					browser.open(ftp4client, nextDir, CurrentPage.DirNames);
				}
				else if (nextAct.equals("back"))
				{
					// ������ � FTPMediator
					browser.back(ftp4client);
				}
				else if (nextAct.equals("load")) 
				{
					// ������ ����� ���������� �����
					String fileToDownload = readCommand("input file name");
					// ������ � FTPMediator
					browser.load(ftp4client, fileToDownload, CurrentPage.FileNames);
				}
				else if (nextAct.equals("stop"))
				{
					// ������ � FTPMediator
					browser.disconnect();
				}
				else
				{
					System.out.println("Incorrect command");
				}
			}
			// _________________
			// ��������� �������
		}
		// ����������� �������
	}
}
