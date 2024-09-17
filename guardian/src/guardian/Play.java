package guardian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Play extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public static final String[] Icons = {"src/archerA.jpg", "src/horseA.jpeg", "src/knightA.jpeg", "src/mageA.jpg", "src/archerB.jpg", "src/horseB.jpeg", "src/knightB.jpeg", "src/mageB.jpg"};
	
	public static int[] cardStateA = {0,0,0,0,0,0};
	public static int[] cardTypeA = {0,0,0,0,0,0};
	public static int[] cardStateB = {0,0,0,0,0,0};
	public static int[] cardTypeB = {0,0,0,0,0,0};
	
	public static int[] boardCardState = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	public static int[] boardCardType = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	public static int clickBoardCardType = 0;
	public static JButton clickBoardButton;
	public static int clickBoardCardLocation;
	
	// 0 is haven't click anywhere, 1 is clicked board, 2 is clicked card slot 
	public static int clickBoardCard = 0;
	public static int clickBoardActionPerformed = 0;
	
	public static JButton clickStackButton;
	public static int clickCardType = 0;
	public static int clickCardBoxLocation;
	public static int clickCardLocation;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	public static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	private static void createAndShowGUI()
	{
		Play gyplay = new Play();
		gyplay.setLocation(300,100);
		gyplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gyplay.addComponentsToBoard(gyplay.getContentPane());
		gyplay.addComponentsToPlayerControl(gyplay.getContentPane());
		
		gyplay.pack();
		gyplay.setVisible(true);
	}
	
	public void addComponentsToBoard(final Container pane) {
		final JPanel compsToBoard = new JPanel();
		compsToBoard.setPreferredSize(new Dimension(600,400));
		GridLayout boardLayout = new GridLayout(7,7);
		compsToBoard.setLayout(boardLayout);
		Icon baseIconA = new ImageIcon("src/baseA.jpg");
		Icon baseIconB = new ImageIcon("src/baseB.jpg");
		for (int i = 0; i < 49; i++) {
			JButton JB = new JButton("");
			JB.setName("JB"+i);
			if (i == 21) {
				JB.setIcon(baseIconA);
			}
			if (i == 27) {
				JB.setIcon(baseIconB);
			}
			compsToBoard.add(JB);
			JB.addActionListener(new ActionListener() {
				@Override
			    public void actionPerformed(ActionEvent e) {
					JButton JB = (JButton)e.getSource();
					String jbname = JB.getName();
					//System.out.println(jbname);

					if (	clickBoardCard == 1 && clickBoardActionPerformed == 0) {
						String iconName = Icons[clickBoardCardType-1];
						Icon icon = new ImageIcon(iconName);
						Icon spacerIcon = new ImageIcon("src/spacer.jpeg");
						
						if (JB != clickBoardButton) {
							if (clickBoardCardType != 1 && clickBoardCardType != 5) {
								//System.out.println("other than archer action");
								JB.setIcon(icon);
								clickBoardButton.setIcon(spacerIcon);
								boardCardState[clickBoardCardLocation] = 0;
								boardCardType[clickBoardCardLocation] = 0;
								
								for (int i = 0; i < 49; i++) {
									String jbnametemp = "JB"+i;
									if(jbname.equalsIgnoreCase(jbnametemp))
									{
										//System.out.println("enter set value");
										//System.out.println(jbnametemp);
										//System.out.println(jbname);
										boardCardState[i] = 1;
										boardCardType[i] = clickBoardCardType;
										//System.out.println(Arrays.toString(boardCardType));
										//System.out.println(Arrays.toString(boardCardState));
									}
								}
								//System.out.println("boardCard is moved");
							}
							
							if (clickBoardCardType == 1 || clickBoardCardType == 5) {
								//System.out.println("archer action");
								for (int i = 0; i < 49; i++) {
									String jbnametemp = "JB"+i;
									if(jbname.equalsIgnoreCase(jbnametemp))
									{
										if (boardCardState[i] == 1) {
											JB.setIcon(spacerIcon);
											boardCardState[i] = 0;
											boardCardType[i] = 0;
											System.out.println(Arrays.toString(boardCardType));
											System.out.println(Arrays.toString(boardCardState));
										}
										else {
											JB.setIcon(icon);
											boardCardState[i] = 1;
											boardCardType[i] = clickBoardCardType;
											clickBoardButton.setIcon(spacerIcon);
											boardCardState[clickBoardCardLocation] = 0;
											boardCardType[clickBoardCardLocation] = 0;
											System.out.println(Arrays.toString(boardCardType));
											System.out.println(Arrays.toString(boardCardState));
										}
									}
								}
							}
						}
						if (JB == clickBoardButton) {
							clickBoardCardType = 0;
							//clickBoardCard = 0;
							//clickBoardActionPerformed = 0;
							clickCardType = 0;
						}
						clickBoardCard = 0;
						clickBoardActionPerformed = 1;
						JB.setFocusPainted(false);
					}

					if (clickBoardCard == 0 && clickBoardActionPerformed == 0) {
						JB.setFocusPainted(true);
						for (int i = 0; i < 49; i++) {
							String jbnametemp = "JB"+i;
							if(jbname.equalsIgnoreCase(jbnametemp) && boardCardState[i] == 1) {
								System.out.println("boardCard is clicked");
								clickBoardCard = 1;
								clickBoardCardType = boardCardType[i];
								clickBoardButton = JB;
								clickBoardCardLocation = i;
								System.out.println(clickBoardCardLocation);
							}
						}
						clickBoardActionPerformed = 1;
					}
					
					if (clickCardType != 0 && clickBoardCard == 2 && clickBoardActionPerformed ==0) {

						for (int i = 0; i < 49; i++) {
							String jbnametemp = "JB"+i;
							if(jbname.equalsIgnoreCase(jbnametemp)){
								
								if(boardCardState[i] == 0 && i != 21 && i != 27) {
									String iconName = Icons[clickCardType-1];
									Icon icon = new ImageIcon(iconName);
									JB.setIcon(icon);
									
									int clickCardTypeTemp = clickCardType;
									clickCardType = 0;
									clickBoardCard = 0;
									JB.setFocusPainted(false);
									
									Icon spacerIcon = new ImageIcon("src/spacer.jpeg");
									clickStackButton.setIcon(spacerIcon);
									if (clickCardBoxLocation == 1) 
									{
										cardStateA[clickCardLocation] = 0;
										cardTypeA[clickCardLocation] = 0;
									}
									if (clickCardBoxLocation == 2) 
									{
										cardStateB[clickCardLocation] = 0;
										cardTypeB[clickCardLocation] = 0;
									}
									//System.out.println("enter set value");
									//System.out.println(jbnametemp);
									//System.out.println(jbname);
									boardCardState[i] = 1;
									boardCardType[i] = clickCardTypeTemp;
									//System.out.println(Arrays.toString(boardCardType));
									//System.out.println(Arrays.toString(boardCardState));
									clickBoardActionPerformed = 1;
								}
								
								if(boardCardState[i] == 1 || i == 21 || i == 27) {
									JB.setFocusPainted(false);
									clickBoardCardType = 0;
									clickBoardCard = 0;
									clickBoardActionPerformed = 0;
									clickCardType = 0;
								}
							}
							
						}

					}
					clickBoardActionPerformed = 0;
					System.out.println("clickBoardCard is " + clickBoardCard);
				}
			});
			
		}
		pane.add(compsToBoard, BorderLayout.NORTH);
	}
	
	public void addComponentsToPlayerControl(final Container pane) {
		
		final JPanel playerControl = new JPanel();
		GridBagLayout playerControlLayout = new GridBagLayout();
		playerControl.setLayout(playerControlLayout);
		JButton drawButton;
		JButton stackButton;
		Icon icon = new ImageIcon("src/images.jpeg");
		Icon spacerIcon = new ImageIcon("src/spacer.jpeg");
		
		GridBagConstraints c = new GridBagConstraints();
		for (int i = 0; i < 8; i++) {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = i;
			c.gridy = 0;
			
			JButton spacer = new JButton();
			spacer.addActionListener(new ActionListener() {
				@Override
			    public void actionPerformed(ActionEvent e) {
					clickBoardCardType = 0;
					clickBoardCard = 0;
					clickBoardActionPerformed = 0;
					clickCardType = 0;
				}
			});
			spacer.setMargin(new Insets(0,0,0,0)); 
			spacer.setBorderPainted(false);
			//spacer.setBorder(null);
			spacer.setFocusPainted(false);
			spacer.setContentAreaFilled(false);
			playerControl.add(spacer,c);
		}
		
		GridBagConstraints c0 = new GridBagConstraints();
		drawButton = new JButton();
		drawButton.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				int randomInt = getRandomNumber(1,5);
				System.out.println(randomInt);
				String iconName = Icons[randomInt-1];
				System.out.println(iconName);
				Icon icon = new ImageIcon(iconName);
				//System.out.println(playerControl.toString());
				Component[] allComponents = playerControl.getComponents();
				
				/*for (Component comp : allComponents) {
					GridBagConstraints gbc = playerControlLayout.getConstraints(comp);
					if (gbc.gridx == 0 && gbc.gridy == 3) {
						JButton buttonNow = (JButton) comp;
						buttonNow.setIcon(icon);
						break;
					}
				}*/
				
				int gotoFlag = 0;
				
				for (Component comp : allComponents) {
					
					if(gotoFlag == 1)
					{
						break;
					}
					
					GridBagConstraints gbc = playerControlLayout.getConstraints(comp);
					for( int cardState=0; cardState < 6; cardState++)
					{
						
						if(gotoFlag == 1)
						{
							break;
						}
						if(cardStateA[cardState] == 0)
						{
							if(cardState == 0) 
							{
								if (gbc.gridx == 0 && gbc.gridy == 3) 
								{
									JButton buttonNow = (JButton) comp;
									buttonNow.setIcon(icon);
									cardStateA[cardState] = 1;
									cardTypeA[cardState] = randomInt;
									System.out.println("card"+cardTypeA[cardState]+"is given at 03");
									gotoFlag = 1;
								}
								
							}
							if(cardState == 1) 
							{
								if (gbc.gridx == 1 && gbc.gridy == 3) 
								{
									JButton buttonNow = (JButton) comp;
									buttonNow.setIcon(icon);
									cardStateA[cardState] = 1;
									cardTypeA[cardState] = randomInt;
									System.out.println("card"+cardTypeA[cardState]+"is given at 13");
									gotoFlag = 1;
								}
								
							}
							if(cardState == 2) 
							{
								if (gbc.gridx == 2 && gbc.gridy == 3) 
								{
									JButton buttonNow = (JButton) comp;
									buttonNow.setIcon(icon);
									cardStateA[cardState] = 1;
									cardTypeA[cardState] = randomInt;
									System.out.println("card"+cardTypeA[cardState]+"is given at 23");
									gotoFlag = 1;
								}
								
							}
							if(cardState == 3) 
							{
								if (gbc.gridx == 0 && gbc.gridy == 4) 
								{
									JButton buttonNow = (JButton) comp;
									buttonNow.setIcon(icon);
									cardStateA[cardState] = 1;
									cardTypeA[cardState] = randomInt;
									System.out.println("card"+cardTypeA[cardState]+"is given at 04");
									gotoFlag = 1;
								}
								
							}
							if(cardState == 4) 
							{
								if (gbc.gridx == 1 && gbc.gridy == 4) 
								{
									JButton buttonNow = (JButton) comp;
									buttonNow.setIcon(icon);
									cardStateA[cardState] = 1;
									cardTypeA[cardState] = randomInt;
									System.out.println("card"+cardTypeA[cardState]+"is given at 14");
									gotoFlag = 1;
								}
								
							}
							if(cardState == 5) 
							{
								if (gbc.gridx == 2 && gbc.gridy == 4) 
								{
									JButton buttonNow = (JButton) comp;
									buttonNow.setIcon(icon);
									cardStateA[cardState] = 1;
									cardTypeA[cardState] = randomInt;
									System.out.println("card"+cardTypeA[cardState]+"is given at 24");
									gotoFlag = 1;
								}
								
								
							}
							
						}
					}
					
					
				}
				
		    }
		});
		c0.fill = GridBagConstraints.HORIZONTAL;
		c0.insets = new Insets(10,0,10,0);
		c0.ipady = 0;
		c0.gridwidth = 2;
		c0.gridx = 1;
		c0.gridy = 1;
		drawButton.setIcon(icon);
		playerControl.add(drawButton,c0);
		
		drawButton = new JButton();
		drawButton.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				int randomInt = getRandomNumber(5,9);
				System.out.println(randomInt);
				String iconName = Icons[randomInt-1];
				System.out.println(iconName);
				Icon icon = new ImageIcon(iconName);
				//System.out.println(playerControl.toString());
				
				Component[] allComponents = playerControl.getComponents();
				
				int gotoFlag = 0;
				
				for (Component comp : allComponents) {
					
					if(gotoFlag == 1)
					{
						break;
					}
					
					GridBagConstraints gbc = playerControlLayout.getConstraints(comp);
					for( int cardState=0; cardState < 6; cardState++)
					{
						
						if(gotoFlag == 1)
						{
							break;
						}
						if(cardStateB[cardState] == 0)
						{
							if(cardState == 0) 
							{
								if (gbc.gridx == 5 && gbc.gridy == 3) 
								{
									JButton buttonNow = (JButton) comp;
									buttonNow.setIcon(icon);
									cardStateB[cardState] = 1;
									cardTypeB[cardState] = randomInt;
									System.out.println("card"+cardTypeB[cardState]+"is given at 53");
									gotoFlag = 1;
								}
								
							}
							if(cardState == 1) 
							{
								if (gbc.gridx == 6 && gbc.gridy == 3) 
								{
									JButton buttonNow = (JButton) comp;
									buttonNow.setIcon(icon);
									cardStateB[cardState] = 1;
									cardTypeB[cardState] = randomInt;
									System.out.println("card"+cardTypeB[cardState]+"is given at 63");
									gotoFlag = 1;
								}
								
							}
							if(cardState == 2) 
							{
								if (gbc.gridx == 7 && gbc.gridy == 3) 
								{
									JButton buttonNow = (JButton) comp;
									buttonNow.setIcon(icon);
									cardStateB[cardState] = 1;
									cardTypeB[cardState] = randomInt;
									System.out.println("card"+cardTypeB[cardState]+"is given at 73");
									gotoFlag = 1;
								}
								
							}
							if(cardState == 3) 
							{
								if (gbc.gridx == 5 && gbc.gridy == 4) 
								{
									JButton buttonNow = (JButton) comp;
									buttonNow.setIcon(icon);
									cardStateB[cardState] = 1;
									cardTypeB[cardState] = randomInt;
									System.out.println("card"+cardTypeB[cardState]+"is given at 54");
									gotoFlag = 1;
								}
								
							}
							if(cardState == 4) 
							{
								if (gbc.gridx == 6 && gbc.gridy == 4) 
								{
									JButton buttonNow = (JButton) comp;
									buttonNow.setIcon(icon);
									cardStateB[cardState] = 1;
									cardTypeB[cardState] = randomInt;
									System.out.println("card"+cardTypeB[cardState]+"is given at 64");
									gotoFlag = 1;
								}
								
							}
							if(cardState == 5) 
							{
								if (gbc.gridx == 7 && gbc.gridy == 4) 
								{
									JButton buttonNow = (JButton) comp;
									buttonNow.setIcon(icon);
									cardStateB[cardState] = 1;
									cardTypeB[cardState] = randomInt;
									System.out.println("card"+cardTypeB[cardState]+"is given at 74");
									gotoFlag = 1;
								}
								
							}
							
						}
					}
					
					
				}
		    }
		});
		c0.fill = GridBagConstraints.HORIZONTAL;
		c0.insets = new Insets(10,0,10,0);
		c0.ipady = 0;
		c0.gridwidth = 2;
		c0.gridx = 5;
		c0.gridy = 1;
		drawButton.setIcon(icon);
		playerControl.add(drawButton,c0);
		
		GridBagConstraints c1 = new GridBagConstraints();
		for (int i = 0; i < 8; i++) {
			c1.weightx = 0.5;
			c1.fill = GridBagConstraints.HORIZONTAL;
			c1.gridx = i;
			c1.gridy = 2;
			
			JButton spacer = new JButton();
			spacer.addActionListener(new ActionListener() {
				@Override
			    public void actionPerformed(ActionEvent e) {
					clickBoardCardType = 0;
					clickBoardCard = 0;
					clickBoardActionPerformed = 0;
					clickCardType = 0;
					
				}
			});
			//spacer.setMargin(new Insets(0,0,0,0)); 
			//spacer.setBorderPainted(false);
			//spacer.setBorder(null);
			//spacer.setFocusPainted(false);
			//spacer.setContentAreaFilled(false);
			playerControl.add(spacer,c1);
		}
		
		GridBagConstraints c2 = new GridBagConstraints();
		for (int i = 0; i < 6; i++) {
			c2.fill = GridBagConstraints.HORIZONTAL;
			if (i < 3) {
				c2.gridx = i;
				c2.gridy = 3;
			}
			else {
				c2.gridx = i-3;
				c2.gridy = 4;
			}
			stackButton = new JButton();
			stackButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	JButton jb = (JButton) e.getSource();
			    	GridBagConstraints gbc = playerControlLayout.getConstraints(jb);
			    	System.out.println("location"+gbc.gridx+gbc.gridy);
			    	if (gbc.gridx == 0 && gbc.gridy == 3) {
			    		if (cardStateA[0] == 1) {
				    		clickCardType = cardTypeA[0];
				    		clickStackButton = jb;
				    		clickCardBoxLocation = 1;
				    		clickCardLocation = 0;
				    		clickBoardCard = 2;
			    		}
			    		if (cardStateA[0] == 0) {
						clickBoardCardType = 0;
						clickBoardCard = 0;
						clickBoardActionPerformed = 0;
						clickCardType = 0;
			    		}
			    		System.out.println("card state"+cardStateA[0]);
			    		System.out.println("card type"+cardTypeA[0]);
			    	}
			    	if (gbc.gridx == 1 && gbc.gridy == 3) {
			    		if (cardStateA[1] == 1) {
				    		clickCardType = cardTypeA[1];
				    		clickStackButton = jb;
				    		clickCardBoxLocation = 1;
				    		clickCardLocation = 1;
				    		clickBoardCard = 2;
			    		}
			    		if (cardStateA[1] == 0) {
						clickBoardCardType = 0;
						clickBoardCard = 0;
						clickBoardActionPerformed = 0;
						clickCardType = 0;
			    		}
			    		System.out.println("card state"+cardStateA[1]);
			    		System.out.println("card type"+cardTypeA[1]);
			    	}
			    	if (gbc.gridx == 2 && gbc.gridy == 3) {
			    		if (cardStateA[2] == 1) {
				    		clickCardType = cardTypeA[2];
				    		clickStackButton = jb;
				    		clickCardBoxLocation = 1;
				    		clickCardLocation = 2;
				    		clickBoardCard = 2;
			    		}
			    		if (cardStateA[2] == 0) {
						clickBoardCardType = 0;
						clickBoardCard = 0;
						clickBoardActionPerformed = 0;
						clickCardType = 0;
			    		}
			    		System.out.println("card state"+cardStateA[2]);
			    		System.out.println("card type"+cardTypeA[2]);
			    	}
			    	if (gbc.gridx == 0 && gbc.gridy == 4) {
			    		if (cardStateA[3] == 1) {
				    		clickCardType = cardTypeA[3];
				    		clickStackButton = jb;
				    		clickCardBoxLocation = 1;
				    		clickCardLocation = 3;
				    		clickBoardCard = 2;
			    		}
			    		if (cardStateA[3] == 0) {
						clickBoardCardType = 0;
						clickBoardCard = 0;
						clickBoardActionPerformed = 0;
						clickCardType = 0;
			    		}
			    		System.out.println("card state"+cardStateA[3]);
			    		System.out.println("card type"+cardTypeA[3]);
			    	}
			    	if (gbc.gridx == 1 && gbc.gridy == 4) {
			    		if (cardStateA[4] == 1) {
				    		clickCardType = cardTypeA[4];
				    		clickStackButton = jb;
				    		clickCardBoxLocation = 1;
				    		clickCardLocation = 4;
				    		clickBoardCard = 2;
			    		}
			    		if (cardStateA[4] == 0) {
						clickBoardCardType = 0;
						clickBoardCard = 0;
						clickBoardActionPerformed = 0;
						clickCardType = 0;
			    		}
			    		System.out.println("card state"+cardStateA[4]);
			    		System.out.println("card type"+cardTypeA[4]);
			    	}
			    	if (gbc.gridx == 2 && gbc.gridy == 4) {
			    		if (cardStateA[5] == 1) {
				    		clickCardType = cardTypeA[5];
				    		clickStackButton = jb;
				    		clickCardBoxLocation = 1;
				    		clickCardLocation = 5;
				    		clickBoardCard = 2;
			    		}
			    		if (cardStateA[5] == 0) {
						clickBoardCardType = 0;
						clickBoardCard = 0;
						clickBoardActionPerformed = 0;
						clickCardType = 0;
			    		}
			    		System.out.println("card state"+cardStateA[5]);
			    		System.out.println("card type"+cardTypeA[5]);
			    	}
			    	System.out.println("clickBoardCard is" + clickBoardCard);
				}
			});
			stackButton.setIcon(spacerIcon);
			playerControl.add(stackButton,c2);
		}
		
		GridBagConstraints c3 = new GridBagConstraints();
		for (int i = 0; i < 6; i++) {
			c3.fill = GridBagConstraints.HORIZONTAL;
			if (i < 3) {
				c3.gridx = i+5;
				c3.gridy = 3;
			}
			else {
				c3.gridx = i+2;
				c3.gridy = 4;
			}
			stackButton = new JButton();
			stackButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	JButton jb = (JButton) e.getSource();
			    	GridBagConstraints gbc = playerControlLayout.getConstraints(jb);
			    	System.out.println("location"+gbc.gridx+gbc.gridy);
			    	if (gbc.gridx == 5 && gbc.gridy == 3) {
			    		if (cardStateB[0] == 1) {
				    		clickCardType = cardTypeB[0];
				    		clickStackButton = jb;
				    		clickCardBoxLocation = 2;
				    		clickCardLocation = 0;
				    		clickBoardCard = 2;
			    		}
			    		if (cardStateB[0] == 0) {
						clickBoardCardType = 0;
						clickBoardCard = 0;
						clickBoardActionPerformed = 0;
						clickCardType = 0;
			    		}
			    		System.out.println("card state"+cardStateB[0]);
			    		System.out.println("card type"+cardTypeB[0]);
			    	}
			    	if (gbc.gridx == 6 && gbc.gridy == 3) {
			    		if (cardStateB[1] == 1) {
				    		clickCardType = cardTypeB[1];
				    		clickStackButton = jb;
				    		clickCardBoxLocation = 2;
				    		clickCardLocation = 1;
				    		clickBoardCard = 2;
			    		}
			    		if (cardStateB[1] == 0) {
						clickBoardCardType = 0;
						clickBoardCard = 0;
						clickBoardActionPerformed = 0;
						clickCardType = 0;
			    		}
			    		System.out.println("card state"+cardStateB[1]);
			    		System.out.println("card type"+cardTypeB[1]);
			    	}
			    	if (gbc.gridx == 7 && gbc.gridy == 3) {
			    		if (cardStateB[2] == 1) {
				    		clickCardType = cardTypeB[2];
				    		clickStackButton = jb;
				    		clickCardBoxLocation = 2;
				    		clickCardLocation = 2;
				    		clickBoardCard = 2;
			    		}
			    		if (cardStateB[2] == 0) {
						clickBoardCardType = 0;
						clickBoardCard = 0;
						clickBoardActionPerformed = 0;
						clickCardType = 0;
			    		}
			    		System.out.println("card state"+cardStateB[2]);
			    		System.out.println("card type"+cardTypeB[2]);
			    	}
			    	if (gbc.gridx == 5 && gbc.gridy == 4) {
			    		if (cardStateB[3] == 1) {
				    		clickCardType = cardTypeB[3];
				    		clickStackButton = jb;
				    		clickCardBoxLocation = 2;
				    		clickCardLocation = 3;
				    		clickBoardCard = 2;
			    		}
			    		if (cardStateB[3] == 0) {
						clickBoardCardType = 0;
						clickBoardCard = 0;
						clickBoardActionPerformed = 0;
						clickCardType = 0;
			    		}
			    		System.out.println("card state"+cardStateB[3]);
			    		System.out.println("card type"+cardTypeB[3]);
			    	}
			    	if (gbc.gridx == 6 && gbc.gridy == 4) {
			    		if (cardStateB[4] == 1) {
				    		clickCardType = cardTypeB[4];
				    		clickStackButton = jb;
				    		clickCardBoxLocation = 2;
				    		clickCardLocation = 4;
				    		clickBoardCard = 2;
			    		}
			    		if (cardStateB[4] == 0) {
						clickBoardCardType = 0;
						clickBoardCard = 0;
						clickBoardActionPerformed = 0;
						clickCardType = 0;
			    		}
			    		System.out.println("card state"+cardStateB[4]);
			    		System.out.println("card type"+cardTypeB[4]);
			    	}
			    	if (gbc.gridx == 7 && gbc.gridy == 4) {
			    		if (cardStateB[5] == 1) {
				    		clickCardType = cardTypeB[5];
				    		clickStackButton = jb;
				    		clickCardBoxLocation = 2;
				    		clickCardLocation = 5;
				    		clickBoardCard = 2;
			    		}
			    		if (cardStateB[5] == 0) {
						clickBoardCardType = 0;
						clickBoardCard = 0;
						clickBoardActionPerformed = 0;
						clickCardType = 0;
			    		}
			    		System.out.println("card state"+cardStateB[5]);
			    		System.out.println("card type"+cardTypeB[5]);
			    	}

				}
			});
			stackButton.setIcon(spacerIcon);
			playerControl.add(stackButton,c3);
		}
		
		pane.add(playerControl, BorderLayout.SOUTH);
	}
	
}
