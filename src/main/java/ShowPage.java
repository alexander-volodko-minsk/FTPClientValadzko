import java.util.ArrayList;

import it.sauronsoftware.ftp4j.FTPFile;

public class ShowPage 
{
	
	//переменная для хранения информации о структуре директории
	String strCase="empty directory";
	//ArrayList ссылок на папки
	ArrayList<String> DirPath=new ArrayList<String>();
	//ArrayList ссылок на файлы
	ArrayList<String> FilePath=new ArrayList<String>();
	//ArrayList доступных комманд
	ArrayList<String> Commands=new ArrayList<String>();
	
	
	//Метод, определяющий структуру директории и накапливающий ссылки на вложенные объекты
	//____________________________________________________________________________________
	public void collectInfo(FTPFile[] pageObjects)
	{
		//очищаем коллекции и структуру
		DirPath.clear();
		FilePath.clear();
		strCase="empty directory";
		//переменная для подсчета количества вложенных директорий
		int dirNumber=0;
		//переменная для подсчета количества вложенных файлов
		int fileNumber=0;
				
		//проходим по вложенным объектам дирктории и подсчитываем количество папок и файлов, добавляем данные в коллекции
		for(FTPFile object: pageObjects)
		{
					
			//подсчет папок, добавление ссылок на вложенные папки в коллекцию
		    if(object.getType()==1)
		    {
			      dirNumber=dirNumber+1;
			      DirPath.add(object.getName());
		    }
		    //подсчет файлов, добавление ссылок на вложенные файлы в коллекцию
		    if(object.getType()==0)
		    {
			    fileNumber=fileNumber+1;
			    FilePath.add(object.getName());
		    }
		}
		//определяем случай структуры страницы, в последующем будут описаны возможные методы для каждого
		if((dirNumber!=0)&(fileNumber==0))
		{
			strCase="case1";
		}
				
		if((dirNumber==0)&(fileNumber!=0))
		{
			strCase="case2";
		}
				
		if((dirNumber!=0)&(fileNumber!=0))
		{
			strCase="case3";
		}
	}
	//____________________________________________________________________________________
	//Метод, определяющий структуру директории и накапливающий ссылки на вложенные объекты
	
	
	//Метод обображающий содержимое страницы
	//______________________________________
	public void showInfo()
	{	
		
		 if(strCase=="case1")//только папки
		 {
			System.out.println("___________________");
			System.out.println("Directories on page:");
			System.out.println("___________________");
			
			//Папки на текущей странице
			for(String dirPath : DirPath )
			{
				System.out.println(dirPath);
			}
			
			//Допустимые операции на текущей странице
			System.out.println("__________________________________________");
	     	System.out.println("input 'open' to open directory");
	     	Commands.add("open");
			System.out.println("input 'back' to go to the parent directory");
			Commands.add("back");
			System.out.println("input 'stop' to exit");
			Commands.add("stop");
		 }
		 
		 
		 if(strCase=="case2")//только файлы
		 {
			//Файлы на текущей странице
			System.out.println("______________");
			System.out.println("Files on page:");
			System.out.println("______________");
			
			//Файлы на текущей странице
		    for(String filePath : FilePath )
			{
				System.out.println(filePath);
			}
		    
		    //Допустимые операции на текущей странице
		    System.out.println("__________________________________________");
			System.out.println("input 'back' to go to the parent directory");
			Commands.add("back");
			System.out.println("input 'load' to download the file");
			Commands.add("load");
			System.out.println("input 'stop' to exit");
			Commands.add("stop");
		 }
		 
		 
		 if(strCase=="case3")//файлы и папки
		 {
			System.out.println("___________________");
			System.out.println("Directories on page:");
			System.out.println("___________________");
				
			//Папки на текущей странице
			for(String dirPath : DirPath )
			{
				System.out.println(dirPath);
			}
				
			//Файлы на текущей странице
				 
			System.out.println("______________");
			System.out.println("Files on page:");
			System.out.println("______________");
				
			//Файлы на текущей странице
			for(String filePath : FilePath )
			{
				System.out.println(filePath);
			}
			
			//Допустимые операции на текущей странице
			System.out.println("__________________________________________");
			System.out.println("input 'open' to open directory");
			Commands.add("open");
			System.out.println("input 'back' to go to the parent directory");
			Commands.add("back");
			System.out.println("input 'load' to download the file");
			Commands.add("load");
			System.out.println("input 'stop' to exit");
			Commands.add("stop");
		 }
		 if(strCase=="empty directory")
		 {
			System.out.println("Empty directory");
			//Допустимые операции на текущей странице
			System.out.println("__________________________________________");
			System.out.println("input 'back' to go to the parent directory");
			Commands.add("back");
			System.out.println("input 'stop' to exit");
			Commands.add("stop");
		 } 
	}
	//______________________________________
	//Метод обображающий содержимое страницы

}
				
	
