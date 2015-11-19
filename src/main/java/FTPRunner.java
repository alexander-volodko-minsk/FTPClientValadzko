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
	public static String readCommand(String message) {
		System.out.println(message);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		return command = scan.nextLine();
	}

	public static void main(String[] args) {
		// ������ ������ � ��������� ������� � ������������
		String adress = readCommand("Enter the adress");
		String logName = readCommand("Enter the log name");
		String password = readCommand("Enter the password");
		// ����������� � �������
		FTPMediator browser = new FTPMediator(adress, logName, password);
		// �������� �������
		FTPClient client = null;
		
		// �����������
		//____________
		try 
		{
			client = browser.connect();
		}

		catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException e) 
		{
			System.out.println("Sorry,connection wasn't established! Please, restart the programm with correct values");
			System.exit(0);
		}
		//____________
		// �����������
		
		

		// ����������� �������
		for (;;) {

			// ���������� ��������
			// ___________________
			Page CurrentPage = new Page();
			
			// ���� ���������� � ���������� ��������
			try 
			{
				CurrentPage.collectInfo(client.list());
			} 
			catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException
					| FTPDataTransferException | FTPAbortedException | FTPListParseException e)
			{
				e.printStackTrace();
			}
			
			// ����� ���������� �� �����
			CurrentPage.showInfo();
			// ___________________
			// ���������� ��������

			// ��������� �������
			// _________________
			String nextAct = readCommand("Please, choose action");

			// �������� ���������� ��������
			boolean valid = CurrentPage.validCommand(command);

			// ���� �������� �� ���������
			if (valid != true) {
				System.out.println("Incorrect command");
			}

			// ���� �������� ���������
			else {
				if (nextAct.equals("open")) {
					// ������ �������� ��������� �����
					String nextDir = readCommand("input directory name");

					// �������� ��������� ��� ��������� �����
					try 
					{
						browser.open(client, nextDir, CurrentPage.DirNames);
					} 
					catch (IllegalStateException e) 
					{
						e.printStackTrace();
					}
				}

				else if (nextAct.equals("back")) {
					
					// ������� � ���������� �����
					try 
					{
						browser.back(client);
					} 
					catch (IllegalStateException  e) 
					{
						e.printStackTrace();
					}
				}

				else if (nextAct.equals("load")) {
					// ������ ����� ���������� �����
					String fileToDownload = readCommand("input file name");

					// ���������� �����
					try
					{
						browser.load(client, fileToDownload, CurrentPage.FileNames);
					} 
					catch (IllegalStateException  e)
					{
						e.printStackTrace();
					}
				}

				else if (nextAct.equals("stop")) {
					// ����� �� ���������
					browser.disconnect();
				}

				else {
					System.out.println("Incorrect command");
				}
			}
			// _________________
			// ��������� �������
		}
	}
}
