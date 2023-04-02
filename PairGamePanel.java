/**
 * @(#)PairGamePanel.java
 *
 * PairGame java panel
 *
 * @author dinhnguyenngoc@gmail.com
 * @version 1.00 2007/06/27
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

interface defImage1 {
	final ImageIcon defimg = new ImageIcon("images/cupbutton.gif");
}

public class PairGamePanel extends JPanel implements ActionListener,defImage1 {
	
	private int w,h;
	
	private JButton btnArr[][];
	private ImageIcon JButtonIconArr[][];
	private ImageIcon IconArr[];
	
	private int countWin=0;
	private int countClicked=1;
	private Timer timer,timerNosame;
	private boolean chkTimer=true;
	
	private int curX1,curX2,curY1,curY2;
	private String strWin="Anonymous";
	private long start,stop;
	private long bestTime;
	private long timeWin=1;
	private boolean isBestTime = false;
	
	public PairGamePanel(int w,int h,long bestTime) {
		this.w = w;
		this.h = h;
		this.bestTime = bestTime;
		init();
		this.setLayout(new GridLayout(w,h));
		initJButtonArr();
		initIconArr();
		
		for(int i=0;i<w;i++)
			for(int j=0;j<h;j++)
				btnArr[i][j].addActionListener(this);
	}
	
	public void init() {
		IconArr = new ImageIcon[18];
		btnArr=new JButton[w][h];
		JButtonIconArr = new ImageIcon[w][h];
		start = System.currentTimeMillis();
	}
	
	// Khoi tao mang ImageIcon tam thoi
	public void initIconArr() {
		IconArr[0] = new ImageIcon("images/gem_green.gif");
		IconArr[1] = new ImageIcon("images/gem_orange.gif");
		IconArr[2] = new ImageIcon("images/gem_purple.gif");
		IconArr[3] = new ImageIcon("images/gem_red.gif");
		IconArr[4] = new ImageIcon("images/gem_yellow.gif");
		IconArr[5] = new ImageIcon("images/gem_white.gif");
		IconArr[6] = new ImageIcon("images/greenshd.gif");
		IconArr[7] = new ImageIcon("images/redshd.gif");
		IconArr[8] = new ImageIcon("images/nav_start_here.gif");
		IconArr[9] = new ImageIcon("images/nav_connected.gif");
		IconArr[10] = new ImageIcon("images/qmark.gif");
		IconArr[11] = new ImageIcon("images/secret_crown.gif");
		IconArr[12] = new ImageIcon("images/secret_scepter.gif");
		IconArr[13] = new ImageIcon("images/nav_best.gif");
		IconArr[14] = new ImageIcon("images/nav_connected_down.gif");
		IconArr[15] = new ImageIcon("images/nav_start_here_down.gif");
		IconArr[16] = new ImageIcon("images/nav_unlock.gif");
		IconArr[17] = new ImageIcon("images/secret_scepter.gif");
		
		swapJButtonArr();

	}
	
	// Khoi tao nhan cac JButton deu la hinh defImage
	public void initJButtonArr() {
		// Hide JButton by defimg		
		for(int i=0;i<w;i++)
			for(int j=0;j<h;j++) {
				btnArr[i][j] = new JButton(defimg);
				add(btnArr[i][j]);
			}
	}
	
	// Hoan doi nut trong ma tran chinh
	public void swapJButtonArr() {
		// dua tat ca icon vao JButtonArr
		int k=0;
		int mod=(w*h)/2;
		if(mod>18)
			mod = 18;
		
		for(int i=0;i<w;i++)
			for(int j=0;j<h;j++) {
				JButtonIconArr[i][j] = IconArr[k%mod];
				k++;
			}
		// Hoan doi
		ImageIcon tmp = new ImageIcon();
		for(int i=0;i<w;i++)
			for(int j=0;j<h;j++) {
				int x = getRand(w);
				int y = getRand(h);
				tmp = JButtonIconArr[i][j];
				JButtonIconArr[i][j]=JButtonIconArr[x][y];
				JButtonIconArr[x][y]=tmp;
			}
	}
	
	public int getRand(int seed) {
		return (int)(Math.round((Math.random()*1000000000))%seed);
	}
	
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
//		if(!(src instanceof JButton))
//			return;
		int i,j;
		// Neu nhan icon da mo ra thi khong lam gi ca
		for(i=0;i<w;i++)
			for(j=0;j<h;j++)
			{ 
				if(src == btnArr[i][j])
				{
					if(btnArr[i][j].getIcon() != defimg || chkTimer == false)
						return;
					else
						if(countClicked == 1) {
							curX1 = i;
							curY1 = j;
							btnArr[i][j].setIcon(JButtonIconArr[i][j]);
							countClicked = 2;
						}
						else if(countClicked == 2)
							{
								curX2 = i;
								curY2 = j;
								btnArr[i][j].setIcon(JButtonIconArr[i][j]);
								countClicked = 1;
								
								if(CheckPair()==true){
    							countWin++;
    							chkTimer=false;
    							timer =new Timer(400,this);
    							timer.start();
    							}
    							timerNosame=new Timer(400,this);
								chkTimer=false;     						
    							timerNosame.start();
    						}
					
				}
			}
		if(src.equals(timer)){
				timer.stop();
				btnArr[curX1][curY1].setVisible(false);
    			btnArr[curX2][curY2].setVisible(false);
    			chkTimer=true;
    			
    			if(countWin==(w*h)/2)			// WIN
    			{    				
    				this.setBackground(Color.red);
    				stop = System.currentTimeMillis();
    				long timeWinTmp = stop - start;
   			   		JOptionPane.showMessageDialog(this,"YOU WIN WITH TIME: "+timeWinTmp/1000+" seconds");
   			 
   			   		if(bestTime>timeWinTmp) {
   			   			strWin = JOptionPane.showInputDialog(this,"What's your name?",getWinPeople());
   			   			if(strWin != null)
   			   				isBestTime = true;
   			   			else {
   			   				strWin = "Cancel";
   			   			}
   			   		}
   			   		if(!strWin.equals("Cancel")) {
   			   			// Neu chon Yes ma khong nhap
   			   			if(strWin.length() == 0)
   			   				strWin = "Anonymous";
   			   			timeWin = timeWinTmp;
   			   		}
   			   		else {
   			   			strWin = "Anonymous";
   			   			timeWin = 1000;
   			   		}
    			   	this.setBackground(Color.white);
    			}		
	   		}
	   		if(src.equals(timerNosame)){
	   			timerNosame.stop();
	   			btnArr[curX1][curY1].setIcon(defimg);
    			btnArr[curX2][curY2].setIcon(defimg);
    			chkTimer=true; 
      		}
	}
	
	public String getWinPeople() {
		return strWin;
	}
	
	public long getWinTime() {
		return timeWin;
	}
	
	public boolean isBestTime() {
		return isBestTime;
	}
	
	private boolean CheckPair(){
    	if(btnArr[curX1][curY1].getIcon().equals(btnArr[curX2][curY2].getIcon())&& btnArr[curX1][curY1].getIcon()!=defimg)
    		return true;
    	return false;
    }
}
