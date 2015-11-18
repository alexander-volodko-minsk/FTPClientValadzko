import java.util.ArrayList;

import it.sauronsoftware.ftp4j.FTPFile;

public class ShowPage 
{
	
	//���������� ��� �������� ���������� � ��������� ����������
	String strCase="empty directory";
	//ArrayList ������ �� �����
	ArrayList<String> DirPath=new ArrayList<String>();
	//ArrayList ������ �� �����
	ArrayList<String> FilePath=new ArrayList<String>();
	//ArrayList ��������� �������
	ArrayList<String> Commands=new ArrayList<String>();
	
	
	//�����, ������������ ��������� ���������� � ������������� ������ �� ��������� �������
	//____________________________________________________________________________________
	public void collectInfo(FTPFile[] pageObjects)
	{
		//������� ��������� � ���������
		DirPath.clear();
		FilePath.clear();
		strCase="empty directory";
		//���������� ��� �������� ���������� ��������� ����������
		int dirNumber=0;
		//���������� ��� �������� ���������� ��������� ������
		int fileNumber=0;
				
		//�������� �� ��������� �������� ��������� � ������������ ���������� ����� � ������, ��������� ������ � ���������
		for(FTPFile object: pageObjects)
		{
					
			//������� �����, ���������� ������ �� ��������� ����� � ���������
		    if(object.getType()==1)
		    {
			      dirNumber=dirNumber+1;
			      DirPath.add(object.getName());
		    }
		    //������� ������, ���������� ������ �� ��������� ����� � ���������
		    if(object.getType()==0)
		    {
			    fileNumber=fileNumber+1;
			    FilePath.add(object.getName());
		    }
		}
		//���������� ������ ��������� ��������, � ����������� ����� ������� ��������� ������ ��� �������
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
	//�����, ������������ ��������� ���������� � ������������� ������ �� ��������� �������
	
	
	//����� ������������ ���������� ��������
	//______________________________________
	public void showInfo()
	{	
		
		 if(strCase=="case1")//������ �����
		 {
			System.out.println("___________________");
			System.out.println("Directories on page:");
			System.out.println("___________________");
			
			//����� �� ������� ��������
			for(String dirPath : DirPath )
			{
				System.out.println(dirPath);
			}
			
			//���������� �������� �� ������� ��������
			System.out.println("__________________________________________");
	     	System.out.println("input 'open' to open directory");
	     	Commands.add("open");
			System.out.println("input 'back' to go to the parent directory");
			Commands.add("back");
			System.out.println("input 'stop' to exit");
			Commands.add("stop");
		 }
		 
		 
		 if(strCase=="case2")//������ �����
		 {
			//����� �� ������� ��������
			System.out.println("______________");
			System.out.println("Files on page:");
			System.out.println("______________");
			
			//����� �� ������� ��������
		    for(String filePath : FilePath )
			{
				System.out.println(filePath);
			}
		    
		    //���������� �������� �� ������� ��������
		    System.out.println("__________________________________________");
			System.out.println("input 'back' to go to the parent directory");
			Commands.add("back");
			System.out.println("input 'load' to download the file");
			Commands.add("load");
			System.out.println("input 'stop' to exit");
			Commands.add("stop");
		 }
		 
		 
		 if(strCase=="case3")//����� � �����
		 {
			System.out.println("___________________");
			System.out.println("Directories on page:");
			System.out.println("___________________");
				
			//����� �� ������� ��������
			for(String dirPath : DirPath )
			{
				System.out.println(dirPath);
			}
				
			//����� �� ������� ��������
				 
			System.out.println("______________");
			System.out.println("Files on page:");
			System.out.println("______________");
				
			//����� �� ������� ��������
			for(String filePath : FilePath )
			{
				System.out.println(filePath);
			}
			
			//���������� �������� �� ������� ��������
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
			//���������� �������� �� ������� ��������
			System.out.println("__________________________________________");
			System.out.println("input 'back' to go to the parent directory");
			Commands.add("back");
			System.out.println("input 'stop' to exit");
			Commands.add("stop");
		 } 
	}
	//______________________________________
	//����� ������������ ���������� ��������

}
				
	
