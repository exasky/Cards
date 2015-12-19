package hmi;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Main;
import sup.Supervisor;
import data.Player;

public class SetPlayersFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private static final DefaultListModel<String> defaultListModel = new DefaultListModel<String>();

	public SetPlayersFrame(){
		JPanel mainPanel = new JPanel();
		
		this.setSize(500,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JTextField playerNameText = new JTextField();
		
		JLabel players = new JLabel("Joueurs: ");
		
		final JList<String> playerNamedArea = new JList<String>();

		playerNamedArea.setModel(defaultListModel);
		playerNamedArea.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		        	((DefaultListModel<String>)playerNamedArea.getModel()).removeElement(playerNamedArea.getSelectedValue());
		        }
		    }
		});
		
		
		playerNamedArea.setPreferredSize(new Dimension(100,150));
		
		JButton validateName = new JButton();
		validateName.setText("Add new player");
		validateName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String playerName = playerNameText.getText();
				if (!playerName.equals("")){
					if (SetPlayersFrame.defaultListModel.indexOf(playerName) == -1){
						SetPlayersFrame.defaultListModel.addElement(playerName);
					} else {
						JOptionPane.showMessageDialog(SetPlayersFrame.this, "Name already taken !", "Name already taken", JOptionPane.NO_OPTION);
					}
					playerNameText.setText("");
				} else {
					JOptionPane.showMessageDialog(SetPlayersFrame.this, "Name cannot be empty !", "Empty name", JOptionPane.NO_OPTION);
				}
				playerNameText.requestFocus();
			}
		});
		
		JButton goToMenuButton = new JButton();
		goToMenuButton.setText("Revenir au menu");
		goToMenuButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Supervisor.players.clearPlayers();
				for (int i = 0 ; i < defaultListModel.size(); i++){
					Supervisor.players.addPlayer(new Player(defaultListModel.get(i)));
				}

				SetPlayersFrame.this.setVisible(false);
				Main.mainFrame.setVisible(true);
			}
		});
		
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx =0;
		c.gridy =0;
		mainPanel.add(playerNameText,c);
		
		c.gridx =0;
		c.gridy =1;
		mainPanel.add(validateName,c);
		
		c.gridx =0;
		c.gridy =2;
		mainPanel.add(goToMenuButton,c);
		
		c.gridx =1;
		c.gridy =0;
		mainPanel.add(players,c);

		c.gridx =1;
		c.gridy =1;
		c.gridheight = 2;
		c.weightx = 1;
		c.weighty = 1;
		mainPanel.add(playerNamedArea,c);
		
		Supervisor.setDefaultLocation(this);
		this.add(mainPanel);
		this.setVisible(true);
	}

}

