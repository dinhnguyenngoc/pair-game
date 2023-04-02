/**
 * @(#)PairGameApp.java
 *
 * PairGame application
 *
 * @author dinhnguyenngoc@gmail.com
 * @version 1.00 2007/07/05
 */
import javax.swing.*;
 
 interface LookAFeel {
 	
 	// Possible Look & Feels
    public static final String mac      =
            "com.sun.java.swing.plaf.mac.MacLookAndFeel";
    public static final String metal    =
            "javax.swing.plaf.metal.MetalLookAndFeel";
    public static final String motif    =
            "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    public static final String windows  =
            "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public static final String gtk  =
            "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
 }
public class PairGameApp implements LookAFeel {
    
    private int intFlatfom=1;
    private static String strFlatfom = new String();
    private boolean chkInput = true;
    
    public void init() {
   		
    	do {
			String strMsg = "Flatfom is invalid";
			strFlatfom = javax.swing.JOptionPane.showInputDialog(null,"Please enter Look and Feel option: \n1. Mac\n2. Windows\n3. Motif\n4. Gtk\nOnly input the number","1");
			if(strFlatfom != null);
			else {
				strFlatfom = "";
				chkInput = false;
			}
			if(strFlatfom.matches("[1-4]") && chkInput == true) {
				chkInput = false;	
			}
	    	if(chkInput)
	    		javax.swing.JOptionPane.showMessageDialog(null,strMsg);
		}
		while(chkInput);
		if(strFlatfom.equals(""))
			return;
		intFlatfom = Integer.parseInt(strFlatfom);
    	if(intFlatfom == 1)
    		strFlatfom = mac;
    	else
    		if(intFlatfom == 2)
    			strFlatfom = windows;
    		else
    			if(intFlatfom == 3) {
    				strFlatfom = motif;
    			}
    			else
    				if(intFlatfom == 4)
    					strFlatfom = gtk;
    }
    
    public static void main(String[] args) {
    	
    	new PairGameApp().init();
    	
    	try {
    		UIManager.setLookAndFeel(strFlatfom);
    	}
    	catch(Exception e){}

    	PairGameFrame frame = new PairGameFrame();
    	frame.setLocation(300,150);
		frame.ProcessmenuFileNew();
    	frame.setVisible(true);
    }
}
