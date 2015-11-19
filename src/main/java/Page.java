import java.util.ArrayList;

import it.sauronsoftware.ftp4j.FTPFile;

public class Page 
{
	
	//переменной для хранения информации о структуре директории pageStructure соответствуют:
	//case1-на странице только папки
	//case2-на странице только файлы
	//case3-на странице и папки и файлы
	//empty directory-страница пустая
	String pageStructure="empty directory";
	
	//ArrayList имен папок на странице
	ArrayList<String> DirNames=new ArrayList<String>();
	
	//ArrayList имен файлов на странице
	ArrayList<String> FileNames=new ArrayList<String>();
	
	//ArrayList доступных комманд на странице
	ArrayList<String> ValidCommands=new ArrayList<String>();
	
	
	//Метод, определяющий структуру страницы и накапливающий имена и тип вложенных объектов
	//____________________________________________________________________________________
	public void collectInfo(FTPFile[] pageObjects)
	{
		//очищаем коллекции и структуру
		DirNames.clear();
		FileNames.clear();
		pageStructure="empty directory";
		//переменная для подсчета количества вложенных папок
		int dirNumber=0;
		//переменная для подсчета количества вложенных файлов
		int fileNumber=0;
				
		//проходим по вложенным объектам дирктории и подсчитываем количество папок и файлов, добавляем данные в коллекции
		for(FTPFile object: pageObjects)
		{
					
			//подсчет папок, добавление ссылок на вложенные папки в коллекцию
		    if(object.getType()==1)//'getType=1'-соответствует папке
		    {
			      dirNumber=dirNumber+1;
			      DirNames.add(object.getName());
		    }
		    //подсчет файлов, добавление ссылок на вложенные файлы в коллекцию
		    if(object.getType()==0)//'getType=0'-соответствует файлу
		    {
			    fileNumber=fileNumber+1;
			    FileNames.add(object.getName());
		    }
		}
		//определяем случай структуры страницы
		if((dirNumber!=0)&(fileNumber==0))
		{
			pageStructure="case1";
		}
				
		if((dirNumber==0)&(fileNumber!=0))
		{
			pageStructure="case2";
		}
				
		if((dirNumber!=0)&(fileNumber!=0))
		{
			pageStructure="case3";
		}
	}
	//____________________________________________________________________________________
	//Метод, определяющий структуру страницы и накапливающий имена и тип вложенных объектов
	
	
	//Метод обображающий содержимое страницы
	//______________________________________
	public void showInfo()
	{	
		
		 if(pageStructure=="case1")//только папки
		 {
			System.out.println("___________________");
			System.out.println("Directories on page:");
			System.out.println("___________________");
			
			//Папки на текущей странице
			for(String someDir : DirNames )
			{
				System.out.println(someDir);
			}
			
			//Допустимые операции на текущей странице
			System.out.println("__________________________________________");
	     	System.out.println("input 'open' to open directory");
	     	ValidCommands.add("open");
			System.out.println("input 'back' to go to the parent directory");
			ValidCommands.add("back");
			System.out.println("input 'stop' to exit programm");
			ValidCommands.add("stop");
		 }
		 
		 
		 if(pageStructure=="case2")//только файлы
		 {
			//Файлы на текущей странице
			System.out.println("______________");
			System.out.println("Files on page:");
			System.out.println("______________");
			
			//Файлы на текущей странице
		    for(String someFile : FileNames )
			{
				System.out.println(someFile);
			}
		    
		    //Допустимые операции на текущей странице
		    System.out.println("__________________________________________");
			System.out.println("input 'back' to go to the parent directory");
			ValidCommands.add("back");
			System.out.println("input 'load' to download the file");
			ValidCommands.add("load");
			System.out.println("input 'stop' to exit programm");
			ValidCommands.add("stop");
		 }
		 
		 
		 if(pageStructure=="case3")//файлы и папки
		 {
			System.out.println("___________________");
			System.out.println("Directories on page:");
			System.out.println("___________________");
				
			//Папки на текущей странице
			for(String someDir : DirNames )
			{
				System.out.println(someDir);
			}
				
			//Файлы на текущей странице
				 
			System.out.println("______________");
			System.out.println("Files on page:");
			System.out.println("______________");
				
			//Файлы на текущей странице
			for(String someFile : FileNames )
			{
				System.out.println(someFile);
			}
			
			//Допустимые операции на текущей странице
			System.out.println("__________________________________________");
			System.out.println("input 'open' to open directory");
			ValidCommands.add("open");
			System.out.println("input 'back' to go to the parent directory");
			ValidCommands.add("back");
			System.out.println("input 'load' to download the file");
			ValidCommands.add("load");
			System.out.println("input 'stop' to exit programm");
			ValidCommands.add("stop");
		 }
		 
		 if(pageStructure=="empty directory")
		 {
			System.out.println("Empty directory");
			//Допустимые операции на текущей странице
			System.out.println("__________________________________________");
			System.out.println("input 'back' to go to the parent directory");
			ValidCommands.add("back");
			System.out.println("input 'stop' to exit programm");
			ValidCommands.add("stop");
		 } 
	}
	//______________________________________
	//Метод обображающий содержимое страницы
	
	
	//Метод проверяющий валидность команды
	//____________________________________
	public boolean validCommand(String command)
	{
		boolean valid=false;
		for( String someCommand : ValidCommands)
		{
			if(someCommand.equals(command))
			{
				valid=true;
			}
		}
		return valid;
	}
	//____________________________________
	//Метод проверяющий валидность команды

}