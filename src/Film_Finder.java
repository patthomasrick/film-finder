import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Film_Finder extends JFrame {

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

    private ArrayList<String> subscriptions = new ArrayList<String>();


    public Film_Finder() {

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

        GridLayout mainGrid = new GridLayout(1, 3);
        this.setLayout(mainGrid);
        this.add(subscriptionPanel);
        this.add(searchPanel);
        this.add(resultsPanel);

        Font font1 = new Font("Arial", Font.PLAIN, 48);

        int sWidth = width / 3;
        String[] services = {"Netflix", "HBO", "Hulu", "Amazon Prime"};
//		GridLayout serviceGrid = new GridLayout(0,1);
//		subscriptionPanel.setLayout(serviceGrid);

        serviceTitle = new JLabel("Input Subscription Services");
        serviceTitle.setFont(new Font("Arial", Font.BOLD, 52));
        serviceTitle.setLocation(sWidth, height / 10);

//		instructionPanel = new JPanel();
        instructions = new JLabel("Add the streaming services that you have subscriptions to");
        instructions.setFont(font1);
        instructions.setLocation(sWidth, height / 8);

//		instructionPanel.add(instructions);

        streamingServices = new JComboBox<String>(services);
        streamingServices.setSize((int) ((9 / (double) 10) * sWidth), height / 20);
        streamingServices.setFont(font1);
        streamingServices.setLocation(sWidth, height / 5);

//		serviceComboPanel = new JPanel();
//		serviceComboPanel.add(streamingServices);


        addService = new JButton("Add Subscription Service");
//		addPanel = new JPanel();
        addService.setFont(font1);
        addService.addActionListener(new addServiceListener());
        addService.setLocation(sWidth, height / 4);
//		addPanel.add(addService);

        removeService = new JButton("Remove Selected Service");
//		removePanel = new JPanel();
//		removePanel.setFont(font1);
        removeService.setFont(font1);
        removeService.setLocation(sWidth, height / 3);
        removeService.addActionListener(new removeServiceListener());
//		removePanel.add(removeService);

        removeAllService = new JButton("Remove All Services");
//		removeAllPanel = new JPanel();
//		removeAllPanel.setFont(font1);
        removeAllService.setFont(font1);
        removeAllService.setLocation(sWidth, height / 3);
        removeAllService.addActionListener(new removeAllServicesListener());
//		removeAllPanel.add(removeAllService);


        serviceList = new JList<String>();
        listModel = new DefaultListModel();
        serviceList.setModel(listModel);
        serviceList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        serviceList.setLayoutOrientation(JList.VERTICAL);
        serviceList.setVisibleRowCount(-1);
        serviceList.setFont(font1);
        servicePane = new JScrollPane(serviceList);
        servicePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        servicePane.setWheelScrollingEnabled(true);
        servicePane.setLocation(sWidth, (int) (height * 0.75));
        servicePane.setVisible(true);
//		servicePanePanel = new JPanel();
//		servicePanePanel.add(servicePane);

        subscriptionPanel.add(serviceTitle);
        subscriptionPanel.add(instructions);
        subscriptionPanel.add(streamingServices);
        subscriptionPanel.add(addService);
        subscriptionPanel.add(servicePane);
        subscriptionPanel.add(removeService);
        subscriptionPanel.add(removeAllService);
//		subscriptionPanel.add(instructionPanel);
//		subscriptionPanel.add(serviceComboPanel);
//		subscriptionPanel.add(addPanel);
//		subscriptionPanel.add(servicePanePanel);
//		subscriptionPanel.add(removePanel);
//		subscriptionPanel.add(removeAllPanel);
        subscriptionPanel.setVisible(true);


        GridLayout xx = new GridLayout(1, 1);
        searchPanel.setLayout(xx);
        JButton x = new JButton();
        x.setVisible(true);
        searchPanel.add(x);
        searchPanel.setVisible(true);


        resultsPanel.setVisible(true);


        this.setVisible(true);


    }

    public static void main(String[] args) {
        new Film_Finder();
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
            } catch (ArrayIndexOutOfBoundsException E) {

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


}
