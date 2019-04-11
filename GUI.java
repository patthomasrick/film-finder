import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class GUI extends JFrame {

	JPanel subscriptionPanel;
	JPanel searchPanel;
	JPanel resultsPanel;
	
//	JPanel instructionPanel;
	JLabel instructions;
	JComboBox streamingServices;
//	JPanel serviceComboPanel;
	JList serviceList;
	DefaultListModel listModel;
	JScrollPane servicePane;
//	JPanel servicePanePanel;
	JLabel serviceTitle;
	JButton addService;
//	JPanel addPanel;
	JButton removeService;
//	JPanel removePanel;
	JButton removeAllService;
//	JPanel removeAllPanel;
	
	JLabel title;
	JLabel searchInstructions;
	JTextField movie;
	JButton search;
	JList results;
	JScrollPane resultsPane;
	JLabel message;
	
	JLabel customTitle;
	JLabel customInst;
	JPanel space1;
	JLabel lengthInst;
	JComboBox length;
	JPanel space2;
	JLabel ratingInst;
	JComboBox rating;
	JPanel space3;
	JLabel genreInst;
	JComboBox genre;
	JPanel space4;
	JLabel methodInst;
	JComboBox method;
	JButton custom;
	
	ArrayList<String> subscriptions = new ArrayList<String>();
	
	
	public static void main(String[] args) {
		
		
		new GUI();
		
	}
	
	
	public GUI() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("GPA Calculator");
	    // Get height and size of screen
	    int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		this.setLocation(0, 0);
		this.setSize(width, height);
		
		subscriptionPanel = new JPanel();
		searchPanel = new JPanel();
		resultsPanel = new JPanel();
		
		GridLayout mainGrid = new GridLayout(1,3);
		this.setLayout(mainGrid);
		this.add(subscriptionPanel);
		this.add(resultsPanel);
		this.add(searchPanel);
				
		Font font1 = new Font("Arial", Font.PLAIN, 48);
		Font fontB = new Font("Arial", Font.BOLD, 52);
		
		
		int sWidth = width / 3;
		String[] services = {"Netflix", "HBO", "Hulu", "Amazon Prime"};
		GridLayout Grid = new GridLayout(0,1);
		GridLayout Grid3 = new GridLayout(3,1);
		subscriptionPanel.setLayout(Grid);
		
		serviceTitle = new JLabel("Input Subscription Services");
		serviceTitle.setFont(fontB);
		serviceTitle.setLocation(sWidth, height/10);
		
//		instructionPanel = new JPanel();
		instructions = new JLabel("Add the streaming services that you have subscriptions to");
		instructions.setFont(font1);
		instructions.setLocation(sWidth, height/8);
		
//		instructionPanel.add(instructions);
		
		streamingServices = new JComboBox<String>(services);
		streamingServices.setSize((int) ((9/ (double) 10)*sWidth), height/20);
		streamingServices.setFont(font1);
		streamingServices.setLocation(sWidth, height/5);
		
//		serviceComboPanel = new JPanel();
//		serviceComboPanel.add(streamingServices);		
		

		addService = new JButton("Add Subscription Service");
//		addPanel = new JPanel();
		addService.setFont(font1);
		addService.addActionListener(new addServiceListener());
		addService.setLocation(sWidth, height/4);
//		addPanel.add(addService);
		
		removeService = new JButton("Remove Selected Service");
//		removePanel = new JPanel();
//		removePanel.setFont(font1);
		removeService.setFont(font1);
		removeService.setLocation(sWidth, height/3);
		removeService.addActionListener(new removeServiceListener());
//		removePanel.add(removeService);
		
		removeAllService = new JButton("Remove All Services");
//		removeAllPanel = new JPanel();
//		removeAllPanel.setFont(font1);
		removeAllService.setFont(font1);
		removeAllService.setLocation(sWidth, height/3);
		removeAllService.addActionListener(new removeAllServicesListener());
//		removeAllPanel.add(removeAllService);
		
		
		listModel = new DefaultListModel();
		serviceList = new JList<String>(listModel);
		// serviceList.setModel(listModel);
		serviceList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		serviceList.setLayoutOrientation(JList.VERTICAL);
		serviceList.setVisibleRowCount(-1);
		serviceList.setFont(font1);
		servicePane = new JScrollPane(serviceList);
		servicePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		servicePane.setWheelScrollingEnabled(true);
		servicePane.setSize(width/4, height/4);
		servicePane.setLocation(sWidth, (int) (height * 0.75));
		servicePane.setVisible(true);
//		servicePanePanel = new JPanel();
//		servicePanePanel.add(servicePane);
		
		subscriptionPanel.add(serviceTitle);
		subscriptionPanel.add(instructions);
		JPanel P1 = new JPanel();
		P1.setLayout(Grid3);
		P1.add(new JPanel());
		P1.add(streamingServices);
		subscriptionPanel.add(P1);
		subscriptionPanel.add(addService);
		subscriptionPanel.add(servicePane);
		subscriptionPanel.add(removeService);
		subscriptionPanel.add(removeAllService);
		subscriptionPanel.add(new JPanel());
//		subscriptionPanel.add(instructionPanel);
//		subscriptionPanel.add(serviceComboPanel);
//		subscriptionPanel.add(addPanel);
//		subscriptionPanel.add(servicePanePanel);
//		subscriptionPanel.add(removePanel);
//		subscriptionPanel.add(removeAllPanel);
		subscriptionPanel.setVisible(true);
		
		
		resultsPanel.setLayout(Grid);
		title = new JLabel("Film Finder");
		title.setFont(fontB);
		resultsPanel.add(title);
		searchInstructions = new JLabel("Enter name of a movie you want to watch");
		searchInstructions.setFont(font1);
		resultsPanel.add(searchInstructions);
		JPanel spacer = new JPanel();
		resultsPanel.add(spacer);
		spacer.setLayout(Grid3);
		JPanel spacer2 = new JPanel();
		spacer.add(spacer2);
		movie = new JTextField();
		movie.setFont(font1);
		spacer.add(movie);
		resultsPanel.setVisible(true);
		search = new JButton("Search for Movie");
		search.setFont(font1);
		search.addActionListener(new searchListener());
		resultsPanel.add(search);
		JLabel resText = new JLabel("The movie can be viewed on the following services:");
		resText.setFont(font1);
		resultsPanel.add(resText);
		results = new JList(listModel);
		results.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		results.setLayoutOrientation(JList.VERTICAL);
		results.setVisibleRowCount(-1);
		results.setFont(font1);
		resultsPanel.add(results);
		resultsPanel.add(new JPanel());
		message = new JLabel("");
		message.setFont(font1);
		resultsPanel.add(message);
		
//		JLabel customTitle;
//		JLabel customInst;
//		JPanel space1;
//		JComboBox length;
//		JPanel space2;
//		JComboBox rating;
//		JPanel space3;
//		JComboBox genre;
//		JButton custom;
		searchPanel.setLayout(Grid);
		customTitle = new JLabel("Custom Search");
		customTitle.setFont(fontB);
		customInst = new JLabel("If you do not have a specific movie in mind, enter desired specifications");
		customInst.setFont(font1);
		searchPanel.add(customTitle);
		searchPanel.add(customInst);
		
		space1 = new JPanel();
		space1.setLayout(Grid3);
		lengthInst = new JLabel("Choose desired length of movie");
		lengthInst.setFont(font1);
		space1.add(lengthInst);
		String[] lengths = {"Less than 90 minutes", "Between 90 minutes and two hours", "Between two and two and a half hours", "Between two and a half and three hours", "Over three hours"};
		length = new JComboBox(lengths);
		length.setFont(font1);
		space1.add(length);
		searchPanel.add(space1);
		
		space2 = new JPanel();
		space2.setLayout(Grid3);
		ratingInst = new JLabel("Choose desired rating of movie");
		ratingInst.setFont(font1);
		space2.add(ratingInst);
		String[] ratings = {"G", "PG", "PG-13", "R"};
		rating = new JComboBox(ratings);
		rating.setFont(font1);
		space2.add(rating);
		searchPanel.add(space2);
		
		space3 = new JPanel();
		space3.setLayout(Grid3);
		genreInst = new JLabel("Choose desired genre of movie");
		genreInst.setFont(font1);
		space3.add(genreInst);
		String[] genres = {"Action", "Drama", "Comedy"};
		genre = new JComboBox(genres);
		genre.setFont(font1);
		space3.add(genre);
		searchPanel.add(space3);
		
		space4 = new JPanel();
		space4.setLayout(Grid3);
		methodInst = new JLabel("Choose desired method for finding results");
		methodInst.setFont(font1);
		space4.add(methodInst);
		String[] methods = {"Return by Highest Rated", "Return by Popularity"};
		method = new JComboBox(methods);
		method.setFont(font1);
		space4.add(method);
		searchPanel.add(space4);
		
		custom = new JButton("Search for Movies");
		custom.setFont(font1);
		custom.addActionListener(new customListener());
		searchPanel.add(custom);
		
		searchPanel.add(new JPanel());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		this.setVisible(true);
		
		
		
	}
	
	private class addServiceListener implements ActionListener {
		

		@Override
		public void actionPerformed(ActionEvent e) {
			String service = streamingServices.getSelectedItem().toString();
			if (service != null) {
				listModel.addElement(service);
				subscriptions.add(service);
			}
				
		}
		
	}
	
	private class removeServiceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String service = streamingServices.getSelectedItem().toString();
				listModel.removeElement(service);
				subscriptions.remove(service);
			}
			catch (ArrayIndexOutOfBoundsException E) {
				
			}
			
		}
		
	}
	
	private class removeAllServicesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			listModel.removeAllElements();
			subscriptions.clear();
			
		}
		
	}
	
	private class searchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String film = movie.getText().trim();
			movie.setText("");
			// Take string and run it through search program
			// If found, put into JList
				// listModel.addElement(service);
			// If not, display error message
				// message.setText("Movie not found in any service that you are subscribed to");
			
		}
		
		
	}
	
	private class customListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String len = (String) length.getSelectedItem();
			String rated = (String) rating.getSelectedItem();
			String filmGenre = (String) genre.getSelectedItem();
			String preference = (String) method.getSelectedItem();
			int pref;
			if (preference == "Return by Highest Rated") {
				pref = 1;
			} 
			if (preference == "Return by Popularity") {
				pref = 2;
			}
		}
		
	}
	
	
}
