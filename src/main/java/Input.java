import java.util.Scanner;

public abstract class Input 
{
	// ������������� ��������
	static String command;

	// ����������� ����� ����� ������, � �������� ��������� �������� ���������
	public static String readCommand(String message) 
	{
		System.out.println(message);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		return command = scan.nextLine();
	}
}
