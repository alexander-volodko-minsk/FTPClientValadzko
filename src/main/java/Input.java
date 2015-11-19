import java.util.Scanner;

public abstract class Input 
{
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
}
