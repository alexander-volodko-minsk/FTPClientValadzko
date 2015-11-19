import java.io.IOException;
import java.util.Scanner;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

public abstract class FTPRunner {

	// запрашиваемая комманда
	static String command;

	// статический метод ввода данных, в качестве параметра передаем сообщение
	public static String readCommand(String message) 
	{
		System.out.println(message);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		return command = scan.nextLine();
	}

	public static void main(String[] args) 
	{
		// Запрос данных о требуемом сервере и пользователе
		String address = readCommand("Enter the address");
		String logName = readCommand("Enter the log name");
		String password = readCommand("Enter the password");
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
			String nextAct = readCommand("Please, choose action");
			// проверка валидности комманды
			boolean valid = CurrentPage.validCommand(command);
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
					String nextDir = readCommand("input directory name");
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
					String fileToDownload = readCommand("input file name");
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
