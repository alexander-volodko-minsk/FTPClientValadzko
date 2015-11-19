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

	//запрашиваемая комманда
    static String command;
	
    //статический метод ввода данных, в качестве параметра передаем сообщение
	public static String readCommand(String message)
	{
		System.out.println(message);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		return command=scan.nextLine();
	}
	
	public static void main(String[] args) throws SocketException, IOException, IllegalStateException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException, FTPListParseException 
	{
        //Запрос данных о требуемом сервере и пользователе
		String adress=readCommand("Enter the adress");
		String logName=readCommand("Enter the log name");
		String password=readCommand("Enter the password");
		//Подключение к серверу
		FTPMediator startClient=new FTPMediator(adress,logName,password);
		//Создание клиента
		FTPClient client=startClient.connect();
		
		//Отображение комманд
		for(;;)
		{
			
			//Информация страницы
			//___________________
			Page CurrentPage = new Page();
			//сбор информации о содержимом страницы
			CurrentPage.collectInfo(client.list());
			//вывод информации на экран
			CurrentPage.showInfo();
			//___________________
			//Информация страницы
			
			
			//Обработка комманд
			//_________________
			String nextAct=readCommand("Please, choose action");

			//проверка валидности комманды
			boolean valid=CurrentPage.validCommand(command);
			
			//если комманда не разрешена
			if(valid!=true)
			{
				System.out.println("Incorrect command");
			}
			
			//если комманда разрешена
			else
			{
				
				if(nextAct.equals("open"))
				{
					//чтение названия следующей папки
					String nextDir=readCommand("input directory name");			
					
					//передаем коллекцию все доступные папки
					startClient.open(client, nextDir, CurrentPage.DirNames);
				}
				
				else if(nextAct.equals("back"))
				{
					//переход в предыдущую папку
					startClient.back(client);
				}
				
				else if(nextAct.equals("load"))
				{
					//чтение имени требуемого файла
					String fileToDownload=readCommand("input file name");
					
					//Создание объекта и формирование коллекции всех папок
					//CurrentPage.collectInfo(client.list());
					
					//скачивание файла
					startClient.load(client, fileToDownload, CurrentPage.FileNames);
				}
				
				else if(nextAct.equals("stop"))
				{
					//выход из программы
					startClient.disconnect();
				}
				
				else
				{
					System.out.println("Incorrect command");
				}
			}
			//_________________
			//Обработка комманд
		}
	}
}
