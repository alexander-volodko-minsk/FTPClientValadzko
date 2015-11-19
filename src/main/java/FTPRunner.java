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
	public static String readCommand(String message) {
		System.out.println(message);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		return command = scan.nextLine();
	}

	public static void main(String[] args) {
		// Запрос данных о требуемом сервере и пользователе
		String adress = readCommand("Enter the adress");
		String logName = readCommand("Enter the log name");
		String password = readCommand("Enter the password");
		// Подключение к серверу
		FTPMediator browser = new FTPMediator(adress, logName, password);
		// Создание клиента
		FTPClient client = null;
		
		// подключение
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
		// подключение
		
		

		// Отображение комманд
		for (;;) {

			// Информация страницы
			// ___________________
			Page CurrentPage = new Page();
			
			// сбор информации о содержимом страницы
			try 
			{
				CurrentPage.collectInfo(client.list());
			} 
			catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException
					| FTPDataTransferException | FTPAbortedException | FTPListParseException e)
			{
				e.printStackTrace();
			}
			
			// вывод информации на экран
			CurrentPage.showInfo();
			// ___________________
			// Информация страницы

			// Обработка комманд
			// _________________
			String nextAct = readCommand("Please, choose action");

			// проверка валидности комманды
			boolean valid = CurrentPage.validCommand(command);

			// если комманда не разрешена
			if (valid != true) {
				System.out.println("Incorrect command");
			}

			// если комманда разрешена
			else {
				if (nextAct.equals("open")) {
					// чтение названия следующей папки
					String nextDir = readCommand("input directory name");

					// передаем коллекцию все доступные папки
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
					
					// переход в предыдущую папку
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
					// чтение имени требуемого файла
					String fileToDownload = readCommand("input file name");

					// скачивание файла
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
					// выход из программы
					browser.disconnect();
				}

				else {
					System.out.println("Incorrect command");
				}
			}
			// _________________
			// Обработка комманд
		}
	}
}
