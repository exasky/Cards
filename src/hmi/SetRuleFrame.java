package hmi;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.Main;
import sup.Supervisor;

public class SetRuleFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public SetRuleFrame(){
		JPanel mainPanel = new JPanel();
		this.setSize(500,150);
		Supervisor.setDefaultLocation(this);
		this.setLayout(new GridBagLayout());
		
		final JList<String> rules = new JList<String>();
		DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
		fillListModel(defaultListModel);
		rules.setModel(defaultListModel);
		
		JButton loadRuleButton = new JButton();
		loadRuleButton.setText("Choose this rule");
		loadRuleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedValue = rules.getSelectedValue();
				if (selectedValue == null){
					JOptionPane.showMessageDialog(SetRuleFrame.this, "Select at least one rule", "No selected rule", JOptionPane.ERROR_MESSAGE);
				} else {
					Supervisor.gameRule.loadRulesFromProperty(selectedValue);
					SetRuleFrame.this.setVisible(false);
					Main.mainFrame.setVisible(true);
				}
			}
		});
		
		JButton goToMenuButton = new JButton();
		goToMenuButton.setText("Menu");
		goToMenuButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Supervisor.players.clearPlayers();
				if (rules.getSelectedValue() != null)
					Supervisor.gameRule.loadRulesFromProperty(rules.getSelectedValue());

				SetRuleFrame.this.setVisible(false);
				Main.mainFrame.setVisible(true);
			}
		});
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx =0;
		c.gridy =0;
		c.gridheight = 2;
		c.weightx = 1;
		c.weighty = 1;
		mainPanel.add(rules,c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0;
		c.gridheight = 1;
		mainPanel.add(loadRuleButton,c);
		
		c.gridx = 1;
		c.gridy = 1;
		mainPanel.add(goToMenuButton,c);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(mainPanel);
		this.setVisible(true);
	}

	private void fillListModel(DefaultListModel<String> defaultListModel) {
		File file;
		URL resource = this.getClass().getClassLoader().getResource("Rules/");
		
		if (resource == null){
			try {
				resource= new URL(this.getClass().getProtectionDomain().getCodeSource().getLocation(),"Rules");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			file = new File(resource.toURI());
			File[] listFiles = file.listFiles();
			listFiles.clone();
			for (int i = 0; i< listFiles.length; i++){
				if(listFiles[i].getName().endsWith("rule")){
					defaultListModel.addElement(listFiles[i].getName());
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	/**/
}
