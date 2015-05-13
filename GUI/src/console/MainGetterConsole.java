package console;

import mail.GmailClient;

public class MainGetterConsole {
public static void main(String[] args){
	MainGetterConsole main = new MainGetterConsole();
	main.run();
}
public void run(){
	GmailClient mail = new GmailClient();
	mail.collectResultData();
}
}
