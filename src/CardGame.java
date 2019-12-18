import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class creates the entire GUI of the game along with handling user actions.
 * It inherits data members and methods of the class "Cards".
 * 
 * @author Siddharth Agarwal
 * @version 1.0
 */

public class CardGame extends Cards {
	
	private JFrame frame = new JFrame();
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu control = new JMenu("Control");
	private JMenu help = new JMenu("Help");
	private JMenuItem exit = new JMenuItem("Exit");
	private JMenuItem instruction = new JMenuItem("Instruction");
	
	private JPanel MainPanel = new JPanel();
	private JPanel DealerPanel = new JPanel();
	private JPanel PlayerPanel = new JPanel();
	private JPanel RpCardBtnPanel = new JPanel();
	private JPanel ButtonPanel = new JPanel();
	private JPanel InfoPanel = new JPanel();
	
	private JLabel[] label_Images = new JLabel[6];
	private ImageIcon[] Images= new ImageIcon[6];

	private JButton btn_rpcard1 = new JButton("Replace Card 1");
	private JButton btn_rpcard2 = new JButton("Replace Card 2");;
	private JButton btn_rpcard3 = new JButton("Replace Card 3");;
	private JButton btn_start = new JButton("Start");
	private JButton btn_result = new JButton("Result");
	
	private JLabel label_bet = new JLabel("Bet: $");
	private JLabel label_info = new JLabel();
	private JLabel label_money = new JLabel();
	private JTextField txt_inputbet = new JTextField(10);
	
	/**
	 * The main function creates an object of the class 'CardGame' and calls the function 'go' to start the game.
	 * 
	 * @param args Unused
	 */
	public static void main(String[] args) {
		CardGame t = new CardGame();
		t.go();
	}
	
	/**
	 * This function sets up the GUI of the game by structuring all the panels.
	 * It also handles user actions, that is, handles user events.
	 * 
	 */
	public void go() {
		
		for(int i=0;i<label_Images.length;++i)
			label_Images[i]=new JLabel();

		round();
		
		for(int i=0;i<3;++i)
			DealerPanel.add(label_Images[i]);
		for(int i=3;i<6;++i)
			PlayerPanel.add(label_Images[i]);
				
		RpCardBtnPanel.add(btn_rpcard1);
		RpCardBtnPanel.add(btn_rpcard2);
		RpCardBtnPanel.add(btn_rpcard3);

		ButtonPanel.add(label_bet);
		ButtonPanel.add(txt_inputbet);
		ButtonPanel.add(btn_start);
		ButtonPanel.add(btn_result);

		InfoPanel.add(label_info);
		InfoPanel.add(label_money);

		MainPanel.setLayout(new GridLayout(5, 1));
		MainPanel.add(DealerPanel);
		MainPanel.add(PlayerPanel);
		MainPanel.add(RpCardBtnPanel);
		MainPanel.add(ButtonPanel);
		MainPanel.add(InfoPanel);

		DealerPanel.setBackground(Color.green);
		PlayerPanel.setBackground(Color.green);
		RpCardBtnPanel.setBackground(Color.green);
		
		control.add(exit);
		help.add(instruction);
		menuBar.add(control);
		menuBar.add(help);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(MainPanel);
		frame.setJMenuBar(menuBar);
		frame.setTitle("A Simple Card Game");
		frame.setSize(600, 700);
		frame.setVisible(true);

		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		instruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    String message = "Rules to determine who has better cards:\n" + "J, Q, K are regarded as special cards.\n"
						+ "Rule 1: The one with more special cards wins.\n"
						+ "Rule 2: If both have the same number of special cards, add the face values of the other card(s) and take the remainder after dividing the sum by 10. The one with a bigger remainder wins. (Note: Ace = 1).\n"
						+ "Rule 3: The dealer wins if both rule 1 and rule 2 cannot distinguish the winner.";
				JOptionPane.showMessageDialog(frame, message);
			}
		});

		btn_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bet = Double.valueOf(txt_inputbet.getText());
				if ((bet <= 0) || (bet != (int) bet)) {
					JOptionPane.showMessageDialog(frame, "WARNING! The bet you place must be a positive integer!");
				} else if (bet > amt) {
					JOptionPane.showMessageDialog(frame, "WARNING! You only have $" + amt + "!");
				} else {
					label_info.setText("Your current bet is $" + (int) bet);
					btn_start.setEnabled(false);
					txt_inputbet.setEditable(false);
					btn_result.setEnabled(true);
					btn_rpcard1.setEnabled(true);
					btn_rpcard2.setEnabled(true);
					btn_rpcard3.setEnabled(true);
					pack();
					shufflecards();
					for (int i = 0; i < 6; ++i) {

						if (i < 3)
							dealer.add(card.get(i));
						else
							player.add(card.get(i));
						switch (i) {
						case 0:
							Images[i] = new ImageIcon("Images/card_" + color(i) + ".gif");
							break;
						case 1:
							Images[i]= new ImageIcon("Images/card_" + color(i) + ".gif");
							break;
						case 2:
							Images[i] = new ImageIcon("Images/card_" + color(i) + ".gif");
							break;
						case 3:
							label_Images[i].setIcon(new ImageIcon("Images/card_" + color(i) + ".gif"));
							break;
						case 4:
							label_Images[i].setIcon(new ImageIcon("Images/card_" + color(i) + ".gif"));
							break;
						case 5:
							label_Images[i].setIcon(new ImageIcon("Images/card_" + color(i) + ".gif"));
							break;
						}
					}

					for (int i = 0; i < 6; ++i)
						card.remove(0);

				}

			}
		});

		btn_rpcard1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c++ < 2) {
					label_Images[3].setIcon(new ImageIcon("Images/card_" + color(0) + ".gif"));
					player.set(0, card.get(0));
					card.remove(0);
				}
				btn_rpcard1.setEnabled(false);
				if (c == 2) {
					btn_rpcard2.setEnabled(false);
					btn_rpcard3.setEnabled(false);

				}
			}
		});

		btn_rpcard2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c++ < 2) {
					label_Images[4].setIcon(new ImageIcon("Images/card_" + color(0) + ".gif"));
					player.set(1, card.get(0));
					card.remove(0);
				}
				btn_rpcard2.setEnabled(false);
				if (c == 2) {
					btn_rpcard1.setEnabled(false);
					btn_rpcard3.setEnabled(false);

				}
			}
		});

		btn_rpcard3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c++ < 2) {
					label_Images[5].setIcon(new ImageIcon("Images/card_" + color(0) + ".gif"));
					player.set(2, card.get(0));
					card.remove(0);
				}
				btn_rpcard3.setEnabled(false);
				if (c == 2) {
					btn_rpcard1.setEnabled(false);
					btn_rpcard2.setEnabled(false);

				}
			}
		});

		btn_result.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
				
				for(int i=0;i<3;++i)
					label_Images[i].setIcon(Images[i]);
				
				if (winner()) {
					amt += bet;
					JOptionPane.showMessageDialog(frame, "Congratulations! You win this round!");
				} else {
					amt -= bet;
					JOptionPane.showMessageDialog(frame, "Sorry! The Dealer wins this round!");
				}
				if (amt > 0) {
					round();
				} else {
					label_info.setText("You have no more money! Please start a new game");
					btn_result.setEnabled(false);
					txt_inputbet.setText(null);
					label_money.setText(null);
					JOptionPane.showMessageDialog(frame,
							"Game over!\n You have no more money!\n Please start a new game");
					System.exit(0);
				}
			}
		});
	} 
	
	/**
	 * This function sets up the initial and new rounds of the game by interacting with the data members.
	 * 
	 */
	public void round() {
				
		label_info.setText("Please place your bet!");
		label_money.setText("Amount of money you have: $" + amt);
		bet = 0;
		c = 0;	
		
		for(int i=0;i<label_Images.length;++i) {
			Images[i]=new ImageIcon("Images/card_back.gif");
			label_Images[i].setIcon(Images[i]);
		}

		btn_start.setEnabled(true);
		txt_inputbet.setEditable(true);
		txt_inputbet.setText(null);
		btn_result.setEnabled(false);
		btn_rpcard1.setEnabled(false);
		btn_rpcard2.setEnabled(false);
		btn_rpcard3.setEnabled(false);
	}

}
