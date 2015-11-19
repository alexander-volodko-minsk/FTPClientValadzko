import java.util.ArrayList;

import it.sauronsoftware.ftp4j.FTPFile;

public class Page 
{
	
	//���������� ��� �������� ���������� � ��������� ���������� pageStructure �������������:
	//case1-�� �������� ������ �����
	//case2-�� �������� ������ �����
	//case3-�� �������� � ����� � �����
	//empty directory-�������� ������
	String pageStructure="empty directory";
	
	//ArrayList ���� ����� �� ��������
	ArrayList<String> DirNames=new ArrayList<String>();
	
	//ArrayList ���� ������ �� ��������
	ArrayList<String> FileNames=new ArrayList<String>();
	
	//ArrayList ��������� ������� �� ��������
	ArrayList<String> ValidCommands=new ArrayList<String>();
	
	
	//�����, ������������ ��������� �������� � ������������� ����� � ��� ��������� ��������
	//____________________________________________________________________________________
	public void collectInfo(FTPFile[] pageObjects)
	{
		//������� ��������� � ���������
		DirNames.clear();
		FileNames.clear();
		pageStructure="empty directory";
		//���������� ��� �������� ���������� ��������� �����
		int dirNumber=0;
		//���������� ��� �������� ���������� ��������� ������
		int fileNumber=0;
				
		//�������� �� ��������� �������� ��������� � ������������ ���������� ����� � ������, ��������� ������ � ���������
		for(FTPFile object: pageObjects)
		{
					
			//������� �����, ���������� ������ �� ��������� ����� � ���������
		    if(object.getType()==1)//'getType=1'-������������� �����
		    {
			      dirNumber=dirNumber+1;
			      DirNames.add(object.getName());
		    }
		    //������� ������, ���������� ������ �� ��������� ����� � ���������
		    if(object.getType()==0)//'getType=0'-������������� �����
		    {
			    fileNumber=fileNumber+1;
			    FileNames.add(object.getName());
		    }
		}
		//���������� ������ ��������� ��������
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
	//�����, ������������ ��������� �������� � ������������� ����� � ��� ��������� ��������
	
	
	//����� ������������ ���������� ��������
	//______________________________________
	public void showInfo()
	{	
		
		 if(pageStructure=="case1")//������ �����
		 {
			System.out.println("____________________________");
			System.out.println("DIRECTORIES OF CURRENT PAGE:");
			System.out.println("____________________________");
			
			//����� �� ������� ��������
			for(String someDir : DirNames )
			{
				System.out.println(someDir);
			}
			
			//���������� �������� �� ������� ��������
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
	     	System.out.println("input 'open' to open directory");
	     	ValidCommands.add("open");
			System.out.println("input 'back' to go to the parent directory");
			ValidCommands.add("back");
			System.out.println("input 'stop' to exit programm");
			ValidCommands.add("stop");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		 }
		 
		 
		 if(pageStructure=="case2")//������ �����
		 {
			//����� �� ������� ��������
			System.out.println("______________________");
			System.out.println("FILES ON CURRENT PAGE:");
			System.out.println("______________________");
			
			//����� �� ������� ��������
		    for(String someFile : FileNames )
			{
				System.out.println(someFile);
			}
		    
		    //���������� �������� �� ������� ��������
		    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("input 'back' to go to the parent directory");
			ValidCommands.add("back");
			System.out.println("input 'load' to download the file");
			ValidCommands.add("load");
			System.out.println("input 'stop' to exit programm");
			ValidCommands.add("stop");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		 }
		 
		 
		 if(pageStructure=="case3")//����� � �����
		 {
			System.out.println("____________________________");
			System.out.println("DIRECTORIES OF CURRENT PAGE:");
			System.out.println("____________________________");
				
			//����� �� ������� ��������
			for(String someDir : DirNames )
			{
				System.out.println(someDir);
			}
				
			//����� �� ������� ��������
				 
			System.out.println("______________________");
			System.out.println("FILES ON CURRENT PAGE:");
			System.out.println("______________________");
				
			//����� �� ������� ��������
			for(String someFile : FileNames )
			{
				System.out.println(someFile);
			}
			
			//���������� �������� �� ������� ��������
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("input 'open' to open directory");
			ValidCommands.add("open");
			System.out.println("input 'back' to go to the parent directory");
			ValidCommands.add("back");
			System.out.println("input 'load' to download the file");
			ValidCommands.add("load");
			System.out.println("input 'stop' to exit programm");
			ValidCommands.add("stop");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		 }
		 
		 if(pageStructure=="empty directory")
		 {
			System.out.println("!! EMPTY DIRECTORY !!");
			//���������� �������� �� ������� ��������
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("input 'back' to go to the parent directory");
			ValidCommands.add("back");
			System.out.println("input 'stop' to exit programm");
			ValidCommands.add("stop");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		 } 
	}
	//______________________________________
	//����� ������������ ���������� ��������
	
	
	//����� ����������� ���������� �������
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
	//����� ����������� ���������� �������

}