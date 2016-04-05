package mainPack;

import java.awt.EventQueue;

import presentation.MainFrame;



public class JDBCMySQLMain {
	
	
	public static void main(String[] args) {  
		
      
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame startF= new MainFrame();
					startF.run();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
	}
    }
	
	
	


