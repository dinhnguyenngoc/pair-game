/**
 * @(#)PairGameFrame.java
 *
 * PairGame java frame
 *
 * @author dinhnguyenngoc@gmail.com
 * @version 1.00 2007/06/27
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class PairGameFrame extends JFrame implements ActionListener,ItemListener {
    
    /**
     * Declare Variable
     *
     */
    	private JMenuBar menuBar = new JMenuBar();
    	// Menu File
        private JMenu menuFile = new JMenu();									// File
        private JMenuItem menuFileNew = new JMenuItem();						// New
        private JMenuItem menuFileBestTime = new JMenuItem();					// Best Time
        private JMenuItem menuFileClose = new JMenuItem();
        private JMenuItem menuFileExit = new JMenuItem();						// Exit
        // Setting
        private JMenu menuSettings = new JMenu();								// Settings
        private JCheckBoxMenuItem menuFileBeginer = new JCheckBoxMenuItem();	// Beginner
        private JCheckBoxMenuItem menuFileIntermediate = new JCheckBoxMenuItem();//Intermediate
        private JCheckBoxMenuItem menuFileExpert = new JCheckBoxMenuItem();		// Expert
        private JMenuItem menuFileCustom = new JMenuItem();						// Custom...
        // Help
        private JMenu menuHelp = new JMenu();									// Help
        private JMenuItem menuHelpContents = new JMenuItem();					// Contents
        private JMenuItem menuHelpAbout = new JMenuItem();						// About PairGame
        // Menu Popup
        private JPopupMenu popup = new JPopupMenu();							// Popup
        private JMenu menuPopupNew = new JMenu();								// New
        private JMenuItem menuNewBeginer = new JMenuItem();						// Beginner
        private JMenuItem menuNewIntermediate = new JMenuItem();				// Intermediate
        private JMenuItem menuNewExpert = new JMenuItem();						// Expert
		private JMenuItem menuPopupAbout = new JMenuItem();						// About PairGame                     
        
        Container contentPanel = this.getContentPane();
        
        private PairGamePanel appPanelCustom;
        
        // Best Time Option
        private String strBestTime = "Name: Anonymous\nBest Time: 0 milliseconds";
        private long timeWin=100000;
        
        private int w=2,h=2;
        
        private boolean chkTimeWin=false;
    
    public void init() {
    	
    	contentPanel.setLayout(new BorderLayout());
    	// File
    	menuFile.setText("File");				
        menuFileNew.setText("New");			
        menuFileBestTime.setText("Best Time...          ");
        menuFileClose.setText("Close");	
        menuFileExit.setText("Exit");		
        // Settings
        menuSettings.setText("Settings");		
        menuFileBeginer.setText("Beginner"); 
        menuFileBeginer.setState(true);
        menuFileIntermediate.setText("Intermediate        ");
        menuFileExpert.setText("Expert");
        menuFileCustom.setText("Custom...");
		// Help		    
		menuHelp.setText("Help");			
		menuHelpContents.setText("Contents");
		menuHelpAbout.setText("About PairGame       ");
        // Popup
        menuPopupNew.setText("New");
        menuNewBeginer.setText("Beginner");
        menuNewIntermediate.setText("Intermediate       ");
        menuNewExpert.setText("Expert");
        menuPopupAbout.setText("About PairGame     ");
        
        // Mnumonic and Tooltip
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuFileNew.setMnemonic(KeyEvent.VK_N);	
        menuFileNew.setToolTipText("Create a new game with current settings");
        menuFileBestTime.setToolTipText("The best time of the game");
        menuFileExit.setToolTipText("Exit the game");
        menuSettings.setMnemonic(KeyEvent.VK_S);
        menuFileCustom.setMnemonic(KeyEvent.VK_C);
        menuFileCustom.setToolTipText("Setting the size of the game");
        menuHelp.setMnemonic(KeyEvent.VK_H);
    }
    
    /**
     * The constructor
     */  
	public PairGameFrame() {
        
        setCursor(java.awt.Cursor.HAND_CURSOR);
        init();
        
        // Add action listener.for the menu button
        menuFileExit.addActionListener
        (
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PairGameFrame.this.windowClosed();
                }
            }
        ); 
     
        // File	
        menuFile.add(menuFileNew);
		menuFile.add(menuFileBestTime);    
		menuFile.add(menuFileClose);
		menuFile.addSeparator();
	    menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        // Setting
        menuSettings.add(menuFileBeginer);
        menuSettings.add(menuFileIntermediate);
        menuSettings.add(menuFileExpert);
        menuSettings.addSeparator();
        menuSettings.add(menuFileCustom);
        menuBar.add(menuSettings);
        // Popup
        menuPopupNew.add(menuNewBeginer);
        menuPopupNew.add(menuNewIntermediate);
        menuPopupNew.add(menuNewExpert);
        popup.add(menuPopupNew);
        popup.add(menuPopupAbout);
        // Help
        menuHelp.add(menuHelpContents);
        menuHelp.add(menuHelpAbout);
        menuBar.add(menuHelp);
        
        setTitle("Pair Game");
        setJMenuBar(menuBar);
        this.setLocation(150,50);
        setSize(400, 400);
        setResizable(false);
        
        // Add Action Listener
        menuFileNew.addActionListener(this);
        menuFileClose.addActionListener(this);
        menuFileCustom.addActionListener(this);
        menuFileBeginer.addActionListener(this);
        menuFileIntermediate.addActionListener(this);
        menuFileExpert.addActionListener(this);
        menuFileBestTime.addActionListener(this);
        menuHelpContents.addActionListener(this);
        menuHelpAbout.addActionListener(this);
        menuPopupAbout.addActionListener(this);
        menuNewBeginer.addActionListener(this);
        menuNewIntermediate.addActionListener(this);
        menuNewExpert.addActionListener(this);
        menuFileBeginer.addItemListener(this);
        menuFileIntermediate.addItemListener(this);
        menuFileExpert.addItemListener(this);
        
        addMouseListener(new MouseAdapter()
		{
			public void mouseReleased(MouseEvent e){
				if(e.isPopupTrigger())
					popup.show(e.getComponent(),e.getX(),e.getY());
				
			}
		});
        
        // Add window listener.
        this.addWindowListener
        (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    PairGameFrame.this.windowClosed();
                }
            }
        );  
    }
    
    
    /**
     * Shutdown procedure when run as an application.
     */
    protected void windowClosed() {
    	
    	// Check if it is safe to close the application
    	int exit = JOptionPane.showConfirmDialog(this,"Do you want to exit?","Exit",JOptionPane.YES_NO_OPTION);
 		if(exit == 0)
        // Exit application.
        System.exit(0);
        else
        	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
    public void setWidth(int w) {
    	this.w = w;
    }
    
    public void setHeight(int h) {
    	this.h = h;
    }
     
    // Ham kiem tra Best Time
    public void ProcessBestTime() {
    	// Neu day la lan dau tien choi game
    	if(chkTimeWin==false)
    		return;

   		if(appPanelCustom.isBestTime()) {
   			timeWin = appPanelCustom.getWinTime();
			strBestTime = "Name: "+appPanelCustom.getWinPeople()+"\n"+"Best Time: "+timeWin+" milliseconds";
   		}
    }
    
    public void ProcessmenuFileNew() {
    	// If Panel exited, remove
		if(appPanelCustom != null)
		{
			// Xet xem van de remove co toi uu khong, co that su remove khong, (xem them phan close)
			ProcessBestTime();
			this.remove(appPanelCustom);
		}		
		// Khoi tao moi Panel
		if(menuFileBeginer.getState()) {
			appPanelCustom = new PairGamePanel(4,4,timeWin);			
			this.setLocation(300,150);
			this.setSize(400,400);
			chkTimeWin = true;
		}
		else
			if(menuFileIntermediate.getState()){
				appPanelCustom = new PairGamePanel(6,6,timeWin);
				this.setLocation(300,150);
				this.setSize(500,500);
				chkTimeWin = true;
			}
			else
				if(menuFileExpert.getState()) {
					appPanelCustom = new PairGamePanel(8,8,timeWin);
					this.setLocation(300,150);
					this.setSize(800,600);
					chkTimeWin = true;
				}
				else		// custom
				{
					appPanelCustom = new PairGamePanel(w,h,timeWin);
					if(h<10) {
    					this.setLocation(300,150);
    				}
    				else
    				{
    					this.setLocation(300,0);
    					int i=w*80,j=h*80;
    					if(i<j)
    						j=i;
    					else
    						i=j;
						this.setSize(i,j);
    				}
					chkTimeWin = true;
				}
		contentPanel.add(appPanelCustom,BorderLayout.CENTER);
    }
    
    public void ProcessmenuFileCustom() {
    	String strMsg = "Game board width is invalid";
    	boolean chkInput = true;
    	String strWidth="",strHeight="";
		do { 
			strWidth = JOptionPane.showInputDialog(this,"Game board width (from 2 to 12 rows)");
			if(strWidth != null);
			else {
				strWidth = "";
				chkInput = false;
			}
	    	if(strWidth.matches("[2-9]") || strWidth.matches("[1][012]") && chkInput == true)
	    		chkInput = false;
	    	if(chkInput)
	    		JOptionPane.showMessageDialog(this,strMsg);
		}
		while(chkInput);
		chkInput = true;
		do {
			strMsg = "Game board height is invalid";
			strHeight = JOptionPane.showInputDialog(this,"Game board height (from 2 to 10 rows)");
			if(strHeight != null);
			else {
				strHeight = "";
				chkInput = false;
			}
			if(strHeight.matches("[2-9]") || strHeight.matches("[1][012]") && chkInput==true) {
				w = Integer.parseInt(strWidth);
   				h = Integer.parseInt(strHeight);
   				if((w*h)%2==0)
					chkInput = false;
				else {
					strMsg = "Game board size is invalid";
				}
			}
	    	if(chkInput)
	    		JOptionPane.showMessageDialog(this,strMsg);
		}
		while(chkInput);
    	if(!strWidth.equals(""))
   			w = Integer.parseInt(strWidth);
   		if(!strHeight.equals(""))
   			h = Integer.parseInt(strHeight);
   		
   		menuFileBeginer.setState(false);
   		menuFileIntermediate.setState(false);
		menuFileExpert.setState(false);
 	}

    public void ProcessmenuHelp() {
    	JOptionPane.showMessageDialog(this,"Java Pair Game\n"+"Version 2.1\n"+"Developed by dinhnguyenngoc@gmail.com\n");
    }
    
    public void ProcessmenuHelpContents() {
    	JOptionPane.showMessageDialog(this,"Just to click and find the same picture pairs");
    }
    
    public void actionPerformed(ActionEvent evt) {
    	Object src = evt.getSource();
    	if(src.equals(menuFileNew))
    		ProcessmenuFileNew();
    	else
    		if(src.equals(menuFileCustom))
    			ProcessmenuFileCustom();	
    		else
    			if(src.equals(menuHelpAbout))
    				ProcessmenuHelp();
    			else
    				if(src.equals(menuFileBestTime)) {
 						ProcessBestTime();
 						JOptionPane.showMessageDialog(this,strBestTime);					
    				}
    				else
    					if(src.equals(menuHelpContents)) {
    						ProcessmenuHelpContents();
    					}
    	if(src.equals(menuFileClose)) {
    		if(appPanelCustom != null)
    			this.remove(appPanelCustom);
    		this.repaint();
    		this.validate();
    	}
    	
    	if(src.equals(menuPopupAbout))
    		ProcessmenuHelp();
    	else
    		if(src.equals(menuNewBeginer)) {
    			menuFileBeginer.setState(true);
    			menuFileIntermediate.setState(false);
    			menuFileExpert.setState(false);
    			ProcessmenuFileNew();
    		}
    		else
    			if(src.equals(menuNewIntermediate)) {
    				menuFileBeginer.setState(false);
    				menuFileIntermediate.setState(true);
    				menuFileExpert.setState(false);
    				ProcessmenuFileNew();
    			}
    			else
    				if(src.equals(menuNewExpert)) {
    					menuFileBeginer.setState(false);
    					menuFileIntermediate.setState(false);
    					menuFileExpert.setState(true);
    					ProcessmenuFileNew();
    				}
    	this.validate();
    }
    
    public void itemStateChanged(ItemEvent evt) {
    	Object src = evt.getSource();
    	if(src.equals(menuFileBeginer)) {
    		if(menuFileBeginer.getState()==true) {
    			menuFileIntermediate.setState(false);
    			menuFileExpert.setState(false);
    		}
    	}
    	if(src.equals(menuFileIntermediate)) {
    		if(menuFileIntermediate.getState()==true) {
    			menuFileBeginer.setState(false);
    			menuFileExpert.setState(false);
    		}
    	}
    	if(src.equals(menuFileExpert)) {
    		if(menuFileExpert.getState() == true) {
    			menuFileBeginer.setState(false);
    			menuFileIntermediate.setState(false);
    		}
    	}
    }
}