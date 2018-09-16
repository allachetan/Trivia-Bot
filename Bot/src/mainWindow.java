import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

public class mainWindow {

	private JFrame frame;
	String question;
	String [] answers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel bestAnswer = new JLabel("Question:");
		bestAnswer.setBounds(22, 81, 348, 22);
		frame.getContentPane().add(bestAnswer);
		
		JLabel m1 = new JLabel("A.)");
		m1.setBounds(22, 132, 175, 22);
		frame.getContentPane().add(m1);
		
		JLabel m2 = new JLabel("B.)");
		m2.setBounds(22, 167, 175, 22);
		frame.getContentPane().add(m2);
		
		JLabel m3 = new JLabel("C.)");
		m3.setBounds(22, 202, 175, 22);
		frame.getContentPane().add(m3);
		
		JButton screenShotButton = new JButton("Screen Shot");
		screenShotButton.setBounds(22, 13, 133, 43);
		frame.getContentPane().add(screenShotButton);
		
		JButton selectButton = new JButton("Area of Screen Shot");
		selectButton.setBounds(207, 13, 163, 25);
		frame.getContentPane().add(selectButton);
		
		JLabel lblStatus = new JLabel("Status: no area selected");
		lblStatus.setBounds(207, 52, 163, 16);
		frame.getContentPane().add(lblStatus);
		
		JLabel m1Color = new JLabel("");
		m1Color.setBounds(209, 132, 175, 22);
		frame.getContentPane().add(m1Color);
		
		JLabel m2Color = new JLabel("");
		m2Color.setBounds(209, 170, 175, 22);
		frame.getContentPane().add(m2Color);
		
		JLabel m3Color = new JLabel("");
		m3Color.setBounds(209, 205, 175, 22);
		frame.getContentPane().add(m3Color);
		selectButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				try {
					String status = ScreenCaptureRectangle.getAreaOfIntrest();
					if(status != null)
						lblStatus.setText("Status: " + status);
					else
						lblStatus.setText("Status: failed");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		screenShotButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				
				try {
					
					String fileName = scrnsht.getScreenShot();
					String text = ocrEngine.getText(fileName);
					question = text.substring(0, text.indexOf("?"));
					question = question.replaceAll("\n", "");
					answers = (text.substring(text.indexOf("?"))).split("\n");
					
					bestAnswer.setText(question);
					m1.setText(answers[1]);
					m2.setText(answers[2]);
					m3.setText(answers[3]);
					
					
					long r1 = search.results(question, answers[1]);
					long r2 = search.results(question, answers[2]);
					long r3 = search.results(question, answers[3]);
					String greatest = "r1";
					if(r2 > r1 && r2 > r3)
						greatest = "r2";
					else if(r3 > r1 && r3 > r2)
						greatest = "r3";
					if(greatest == "r1")
						m1Color.setText("CORRECT");
					else if(greatest == "r2")
						m2Color.setText("CORRECT");
					else if(greatest == "r3")
						m3Color.setText("CORRECT");
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		
	}
}
