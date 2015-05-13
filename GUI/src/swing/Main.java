package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import mail.GmailClient;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import requestAgents.RequestAgent;
import requestAgents.RequestAgentManager;
import requestAgents.RequestAgents;
import requestObject.RequestObject;
import requestObject.RequestObjectForModel;
import thread.RequestThread;
import configXML.ConfigXML;
import configXML.ConfigXMLRunner;

import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;

public class Main {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JLabel label_1;
	private JComboBox comboBoxAgentSingelRequest; 
	private JLabel lblPrice ;
	
	private String loadedConfigFile = "";
	private JTextField textField_2;
	private JTextField textField_3;
	private JList<RequestObjectForModel> listOfConfigEntries;
	private JComboBox userAgentsForConfig;
	//private Document ConfigXML;
	private ConfigXML ConfigXML;
	private ConfigXMLRunner runner;
	private AbstractTableModel tableModel ;
	private DefaultListModel<RequestObjectForModel> listmodel = new DefaultListModel<RequestObjectForModel>() ;
	
private JCheckBox chckbxAllUserAgents;
private JTextField ConfigName;
private JTextField textField_versionNumber;
private RequestAgentManager agentManager;
private JTextField textField_Referer;
private JTextField textField_SIngleRequestMobile;
private JTextField textField_CSSMobilepath;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		agentManager = new RequestAgentManager();
		
		listmodel = new DefaultListModel<RequestObjectForModel>();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 604);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Run", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel lblSingleRequest = new JLabel("Single Request");
		lblSingleRequest.setBounds(10, 11, 100, 14);
		panel_2.add(lblSingleRequest);
		
		JLabel lblWebsite = new JLabel("Website");
		lblWebsite.setBounds(10, 36, 46, 14);
		panel_2.add(lblWebsite);
		
		textField = new JTextField();
		textField.setBounds(66, 36, 272, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CSS Path");
		lblNewLabel.setBounds(10, 73, 46, 14);
		panel_2.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(66, 70, 272, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSingleRequest();
			}
		});
		btnRun.setBounds(249, 196, 89, 23);
		panel_2.add(btnRun);
		
		lblPrice = new JLabel("Price");
		lblPrice.setBounds(149, 200, 46, 14);
		panel_2.add(lblPrice);
		
		comboBoxAgentSingelRequest = new JComboBox();
		comboBoxAgentSingelRequest.setModel(new DefaultComboBoxModel(agentManager.getAgents().toArray()));
		comboBoxAgentSingelRequest.setBounds(67, 143, 82, 20);
		panel_2.add(comboBoxAgentSingelRequest);
		
		JLabel lblMobileCss = new JLabel("Mobile CSS");
		lblMobileCss.setBounds(10, 98, 46, 14);
		panel_2.add(lblMobileCss);
		
		textField_SIngleRequestMobile = new JTextField();
		textField_SIngleRequestMobile.setBounds(66, 95, 272, 20);
		panel_2.add(textField_SIngleRequestMobile);
		textField_SIngleRequestMobile.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel label = new JLabel("Run Config");
		label.setBounds(10, 11, 71, 14);
		panel_3.add(label);
		
		JButton button = new JButton("LoadFile");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadXMLConfig();
			}
		});
		button.addMouseListener(new MouseAdapter() {
			//TODO: entfernen
			@Override
			public void mouseClicked(MouseEvent e) {
				FileFilter filter = new FileNameExtensionFilter("Config Files","cfg");
				 JFileChooser fc = new JFileChooser();
				 fc.setFileFilter(filter);
				 int returnVal = fc.showOpenDialog(null);

			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            label_1.setText(file.getName());
			            loadedConfigFile = file.getAbsolutePath();
			            //This is where a real application would open the file.
			           // log.append("Opening: " + file.getName() + "." + newline);
			        } else {
			          //  log.append("Open command cancelled by user." + newline);
			        }
			}
		});
		button.setBounds(10, 37, 89, 23);
		panel_3.add(button);
		
		 label_1 = new JLabel("Loaded File");
		label_1.setBounds(109, 41, 100, 14);
		panel_3.add(label_1);
		
		JButton btnRun_1 = new JButton("Run");
		btnRun_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runConfigData();
			}
		});
		btnRun_1.setBounds(254, 37, 89, 23);
		panel_3.add(btnRun_1);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 512, 459, 14);
		panel_3.add(progressBar);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 83, 459, 423);
		panel_3.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_4.add(scrollPane_1);
		
		tableModel = new AbstractTableModel() {
			ArrayList<RequestObject> req = new ArrayList<RequestObject>();
			private int rowIndex =0;
			String[] names = {"Name","Website","CSS path","Referer","UserAgent","Price"};
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				if(aValue instanceof RequestObject){
					req.add((RequestObject) aValue);
				}
				super.setValueAt(aValue, rowIndex, columnIndex);
			}

			@Override
			public String getColumnName(int column) {
				
				return names[column];
			}

			public Object getValueAt(int rowIndex, int columnIndex) {
				RequestObject regOb = req.get(rowIndex);
				if(columnIndex == 0){
					return regOb.getName();
				}
				if(columnIndex == 1){
					return regOb.getURL();
				}
				if(columnIndex == 2){
					return regOb.getCssPath();
				}
				if(columnIndex == 3){
					return regOb.getReferer();
				}
				if(columnIndex == 4){
					return regOb.getRequestAgent().getName();
				}
				if(columnIndex == 5){
					return regOb.getPrice();
				}
				if(columnIndex == -1){
					return regOb;
				}
				return null;
			}
			
			public int getRowCount() {
				return req.size();
			}
			
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return names.length;
			}
		};
		
		table = new JTable(tableModel);
		scrollPane_1.setViewportView(table);
		
		JButton btnSendToClients = new JButton("send To Clients");
		btnSendToClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendToClient();
				
			}
		});
		btnSendToClients.setBounds(353, 37, 116, 23);
		panel_3.add(btnSendToClients);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Create Config", null, panel_1, null);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(198, 263, 196, -144);
		panel_5.add(scrollPane);
		
		listOfConfigEntries = new JList<RequestObjectForModel>();
		scrollPane.setViewportView(listOfConfigEntries);
		listOfConfigEntries.setModel(listmodel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(67, 52, 327, 20);
		panel_5.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblWebsite_1 = new JLabel("Website");
		lblWebsite_1.setBounds(10, 55, 46, 14);
		panel_5.add(lblWebsite_1);
		
		JLabel label_2 = new JLabel("CSS Path");
		label_2.setBounds(10, 86, 46, 14);
		panel_5.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(67, 83, 327, 20);
		panel_5.add(textField_3);
		
		userAgentsForConfig = new JComboBox();
		userAgentsForConfig.setModel(new DefaultComboBoxModel(agentManager.getAgents().toArray()));
		userAgentsForConfig.setBounds(201, 230, 129, 20);
		panel_5.add(userAgentsForConfig);
		
		chckbxAllUserAgents = new JCheckBox("All User Agents");
		chckbxAllUserAgents.setBounds(62, 229, 133, 23);
		panel_5.add(chckbxAllUserAgents);
		chckbxAllUserAgents.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(chckbxAllUserAgents.isSelected()){
					userAgentsForConfig.setEnabled(false);
				}else{
					userAgentsForConfig.setEnabled(true);
				}
				
			}
		});
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewEntryToConfigXML();
			}
		});
		btnAdd.setBounds(340, 229, 89, 23);
		panel_5.add(btnAdd);
		
		JButton btnSaveAs = new JButton("save as");
		btnSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAsDialog();
			}
		});
		btnSaveAs.setBounds(340, 263, 89, 23);
		panel_5.add(btnSaveAs);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 30, 46, 14);
		panel_5.add(lblName);
		
		ConfigName = new JTextField();
		ConfigName.setBounds(67, 27, 327, 20);
		panel_5.add(ConfigName);
		ConfigName.setColumns(10);
		
		textField_versionNumber = new JTextField();
		textField_versionNumber.setBounds(216, 263, 114, 19);
		panel_5.add(textField_versionNumber);
		textField_versionNumber.setColumns(10);
		
		textField_Referer = new JTextField();
		textField_Referer.setBounds(67, 114, 327, 20);
		panel_5.add(textField_Referer);
		textField_Referer.setColumns(10);
		
		JLabel lblReferer = new JLabel("Referer");
		lblReferer.setBounds(10, 117, 46, 14);
		panel_5.add(lblReferer);
		
		JLabel lblCssMobile = new JLabel("Css Mobile");
		lblCssMobile.setBounds(10, 147, 46, 14);
		panel_5.add(lblCssMobile);
		
		textField_CSSMobilepath = new JTextField();
		textField_CSSMobilepath.setBounds(67, 144, 327, 20);
		panel_5.add(textField_CSSMobilepath);
		textField_CSSMobilepath.setColumns(10);
		//listmodel.addElement(new RequestObjectForModel("laaaa", "badum", null));
		
	}
	protected void loadXMLConfig() {
		//FileDialog fdi = new FileDialog(this.frame,"save",FileDialog.LOAD);
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("choose a config file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (chooser.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
		      System.out.println("getCurrentDirectory(): "
		         +  chooser.getCurrentDirectory());
		      System.out.println("getSelectedFile() : "
		         +  chooser.getSelectedFile());
		      }
		 else {
		      System.out.println("No Selection ");
		 }
		//fdi.setVisible(true);
		String path =  chooser.getSelectedFile().getPath();
		runner = new ConfigXMLRunner(path);
		int index = 0;
		for( RequestObject regob : runner.getRequestObjects()){
			tableModel.setValueAt(regob, index, 0);
			//tableModel.setValueAt(regob, index, 1);
			//tableModel.setValueAt(regob, index, 2);
			System.out.println(regob);
			index++;
		}
		
		
	}

	private void saveAsDialog(){
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Save as");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (chooser.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
		      System.out.println("getCurrentDirectory(): "
		         +  chooser.getCurrentDirectory());
		      System.out.println("getSelectedFile() : "
		         +  chooser.getSelectedFile());
		      }
		 else {
		      System.out.println("No Selection ");
		 }
		//fdi.setVisible(true);
		String path =  chooser.getSelectedFile().getPath();
		ConfigXML.persits(path);
		
		
	}
	private void addNewEntryToConfigXML(){
		if(ConfigXML == null){
			int versionNumber = Integer.parseInt(textField_versionNumber.getText());
			ConfigXML = new ConfigXML(versionNumber);
		}
		String website = textField_2.getText();
		String cssPath = textField_3.getText();
		String cssPathMobile = textField_CSSMobilepath.getText();
		String id = ConfigName.getText();
		String referer = textField_Referer.getText();
		if(chckbxAllUserAgents.isSelected()){
			ConfigXML.addNewRequest(id,website, cssPath,cssPathMobile,referer, null);
			listmodel.addElement(new RequestObjectForModel(website, cssPath,cssPathMobile,referer, null));
		}
		
	}
	private void runConfigData(){
		int requests= tableModel.getRowCount();
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(int i =0 ; i < requests; ++i){
			RequestObject requ = (RequestObject)tableModel.getValueAt(i, -1);
			//Thread thread1 = new Thread(new RequestThread( "Thread-1",requ), "T1");
			//threads.add(thread1);
			//thread1.start();
			requ.perform();
		}
		/*for(Thread r : threads){
			try {
				r.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		runner.sendResults();
	}
	public void doSingleRequest(){
		 String URL = this.textField.getText();
		 ArrayList<String> temp = new ArrayList<String>(); 
		 String CssPath  = this.textField_1.getText();
		 temp.add(CssPath);
		 RequestAgent agent = (RequestAgent)this.comboBoxAgentSingelRequest.getSelectedItem();
		 RequestObject requ = RequestObject.factory.createRequestObjecti("single Reqeust",URL, temp,"",agent );
		requ.perform();
		this.lblPrice.setText(requ.getPrice()+"");
				
	}
	private void sendToClient(){
		if(runner == null){
			JOptionPane.showMessageDialog(null, "no Config selected, please select first a Config over load");
		}else{
			GmailClient.sendNewConfig(runner.getConfigXML());
		}
	}
}
