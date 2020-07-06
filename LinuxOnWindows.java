


//Predefined Classes

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;


//SHELL commands
public class LinuxOnWindows {


    /*
        Function: get path of current directory
        Input: None
        Output: Print the path of current directory
     */
    public static void current_dir() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "CD").inheritIO().start().waitFor();

    }


    /*
     Function: clear the console
     Input: None
     Output: clears the console
  */
    public static void clearConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }


    /*
        Function: list the content of current directory
        Input: None
        Output: Print the files and folder in the current directory
     */
    public static void listContent()throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "dir").inheritIO().start().waitFor();
    }

  public static String getTZone() throws ParseException {
   	ProcessBuilder processBuilder = new ProcessBuilder();
        // Windows
        processBuilder.command("cmd", "/c", "tzutil /g");
	String send = "";

      try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
		
            while ((line = reader.readLine()) != null) {
		send = line;
		}
		
            int exitCode = process.waitFor();
		     

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	return send;
    }


    /*
        Function: get the current Date
        Input: None
        Output: Print the current date in specific format
     */
    public static void getDate() throws ParseException {
   	ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd", "/c", "echo %date%-%time%");
	String tzone = getTZone();
	
      try {

            Process process = processBuilder.start();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {     
		String[] arrStr = line.split("-");
       		String[] dayDate = arrStr[0].split(" ");
        //format to month and day
       		 DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
       		 DateFormat targetFormat = new SimpleDateFormat("MMM dd");
        	Date date = originalFormat.parse(dayDate[1]);
        	String formattedDate = targetFormat.format(date); 
		tzone = "CST"; 
        	String year1 = dayDate[1];
		String[] syear = year1.split("/");
		String formatyear = syear[2]; 
        //separate time
        	String[] times = arrStr[1].split("\\.");
        //required output format
        	System.out.println(dayDate[0] + " " + formattedDate + " " + times[0] + " " + tzone + " " + formatyear);
            }

            int exitCode = process.waitFor();      

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
       Function: the main function
       Input: None
       Output: execute shell commands
    */
    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        System.out.println("Welcome to MyShell \n");

        String name = "User";//Name of programmer
        String next;

        do{
            Scanner scan = new Scanner(System.in);
            System.out.print(">>>");
            next = scan.nextLine();// get input's from user
            next = next.toLowerCase();//convert to lowercase

            if(next.equals("pwd")){//path of current directory command
                current_dir();
            }

            else if(next.equals("date")){//date command
                getDate();
		
            }
            else if(next.equals("clear")){//clear command
                clearConsole();
            }

            else if(next.equals("ls")){//list contents command
                listContent();
            }

            else if (next.equals("help")) {//help command
                System.out.println("MyShell, version 1.0, runs on Windows10, developed by " + name);
                System.out.println("Release date Mar 12, 2020");
                System.out.println("These shell commands are defined internally. Type 'help' to see this list.");
                System.out.println("Command and its parameter, if any, should be separated by one space only.");
                System.out.println("Type 'help name' to find more about the command 'name'.\n");
                System.out.println("command                         function");
                System.out.println("=======                         ========");
                System.out.println("ls                              list contents of current directory");
                System.out.println("pwd                             display the current directory");
                System.out.println("clear                           clears the console");
                System.out.println("date                            displays current day, date, time, and time zone");
                System.out.println("help [cmd]                      get help for command cmd ('cmd' is optional)");
                System.out.println("                                without cmd displays this list");
                System.out.println("exit                            quit console");
                System.out.println("whoami                          displays name of programmer");
                continue;
            } else if (next.equals("whoami")) {//name of programmer
                System.out.println(name);
                continue;
            } else if (next.equals("help ls")) {//function of ls command
                System.out.println("List the contents of the current directory");
                continue;
            } else if (next.equals("help pwd")) {//function of pwd command
                System.out.println("Shows the absolute path for the current directory");
                continue;
            } else if (next.equals("help clear")) {//function of clear command
                System.out.println("Clears the console");
                continue;
            } else if (next.equals("help date")) {//Function of date command
                System.out.println("Displays day, date, time, and timezone in the format:");
                System.out.println("          Sun Mar 1 11:08:58 CST 2020");
                continue;
            } else if (next.equals("help exit")) {//Function of exit command
                System.out.println("Close the console");
                continue;
            } else if (next.equals("help whoami")) {//WhO AM I command
                System.out.println("Displays current user");
                continue;
            }
            else if(next.equals("exit")){//exit command
                break;
            }
            else{//unknown command
                System.out.println("Unknown command!");
            }


        }while (next != "exit"); // END of DO while loop

    }

}//END OF OS CLASS