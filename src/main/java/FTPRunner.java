import java.io.IOException;
import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

public class FTPRunner {

	public static void main(String[] args) 
	{

		
		// ������ ������ � ��������� ������� � ������������
		String address = Input.readCommand("Enter the address");
		String logName = Input.readCommand("Enter the log name");
		String password = Input.readCommand("Enter the password");
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
			String nextAct = Input.readCommand("Please, choose action");
			// �������� ���������� ��������
			boolean valid = CurrentPage.validCommand(nextAct);
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
					String nextDir = Input.readCommand("input directory name");
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
					String fileToDownload = Input.readCommand("input file name");
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
