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

		
		// Запрос данных о требуемом сервере и пользователе
		String address = Input.readCommand("Enter the address");
		String logName = Input.readCommand("Enter the log name");
		String password = Input.readCommand("Enter the password");
		// Подключение к серверу
		FTPMediator browser = new FTPMediator(address, logName, password);
		FTPClient ftp4client=browser.connect();
		

		// Отображение комманд
		for (;;)
		{

			// Информация текущей страницы
			// ___________________
			Page CurrentPage = new Page();
			// сбор информации о содержимом страницы
			try 
			{
				//в качестве параметра передаем массив объектов на странице
				CurrentPage.collectInfo(ftp4client.list());
			} 
			catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException
					| FTPDataTransferException | FTPAbortedException | FTPListParseException e)
			{
				e.printStackTrace();
			}
			// вывод информации на экран
			CurrentPage.showInfo();
			// ___________________
			// Информация текущей страницы
			

			// Обработка комманд
			// _________________
			
			// Чтение комманды
			String nextAct = Input.readCommand("Please, choose action");
			// проверка валидности комманды
			boolean valid = CurrentPage.validCommand(nextAct);
			// если комманда не разрешена
			if (valid != true)
			{
				System.out.println("Incorrect command");
			}
			// если комманда разрешена
			else
			{
				if (nextAct.equals("open"))
				{
					// чтение названия запрашиваемой папки
					String nextDir = Input.readCommand("input directory name");
					// запрос в FTPMediator
					browser.open(ftp4client, nextDir, CurrentPage.DirNames);
				}
				else if (nextAct.equals("back"))
				{
					// запрос в FTPMediator
					browser.back(ftp4client);
				}
				else if (nextAct.equals("load")) 
				{
					// чтение имени требуемого файла
					String fileToDownload = Input.readCommand("input file name");
					// запрос в FTPMediator
					browser.load(ftp4client, fileToDownload, CurrentPage.FileNames);
				}
				else if (nextAct.equals("stop"))
				{
					// запрос в FTPMediator
					browser.disconnect();
				}
				else
				{
					System.out.println("Incorrect command");
				}
			}
			// _________________
			// Обработка комманд
		}
		// Отображение комманд
	}
}
