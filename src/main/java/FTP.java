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
	
	//запрашиваемая комманда
    static String command;
	
    //статический метод ввода данных
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
		
		
		//Информация стартовой страницы
		ShowPage FirstPage = new ShowPage();
		FirstPage.collectInfo(client.list());
		FirstPage.showInfo();
		
		
		//Отображение комманд
		for(;;)
		{
			String nextAct=readCommand("Please, choose action");
/*
			//проверка валидности комманды
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
	
			//Обработка комманд
			//_________________
			else
			{
*/	

			if(nextAct.equals("open"))
			{
				//чтение названия следующей папки
				String nextDir=readCommand("input directory name");
				
				//Создание объекта и формирование коллекции всех папок
				ShowPage DirShow = new ShowPage();
				DirShow.collectInfo(client.list());
				
				//Создание объекта 
				Actions Dir = new Actions();
				
				//передаем коллекцию все доступные папки
				Dir.open(client, nextDir, DirShow.DirPath);
				
				//отображение новой папки
				DirShow.collectInfo(client.list());
				DirShow.showInfo();
			}
			
			else if(nextAct.equals("back"))
			{
				//переход в предыдущую папку
				Actions DirBack = new Actions();
				DirBack.back(client);
				//отображение предыдущей папки
				ShowPage DirShow = new ShowPage();
				DirShow.collectInfo(client.list());
				DirShow.showInfo();
			}
			
			else if(nextAct.equals("load"))
			{
				//чтение имени требуемого файла
				String fileToDownload=readCommand("input file name");
				
				//Создание объекта и формирование коллекции всех папок
				ShowPage DirShow = new ShowPage();
				DirShow.collectInfo(client.list());
				
				//скачивание файла
				Actions FileLoad = new Actions();
				FileLoad.load(client, fileToDownload, DirShow.FilePath);
				DirShow.collectInfo(client.list());
				DirShow.showInfo();
			}
			
			else if(nextAct.equals("stop"))
			{
				//выход из программы
				Actions Exit = new Actions();
				Exit.disconnect();
			}
			
			else
			{
				System.out.println("Incorrect command");
			}
		//}
		//_________________
		//Обработка комманд
		}
	}
}
