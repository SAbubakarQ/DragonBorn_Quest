// Bismillah-AbubakarQ
/*
Abubakar Qasim
This is the corrected version of Assignment 5 and all of its parts
*/

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	// Member Variabless df
	Model model;
	View view;
	Controller controller;

	public Game()
	{
		// JFRame attributes
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		view.addMouseListener(controller);
		view.addMouseMotionListener(controller);

		this.addKeyListener(controller);
		this.setTitle("Abubakar's World! - DRAGONBORN'S QUEST!");
		this.setSize(1000, 650);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); // Sets screen to center of monitor
		this.setVisible(true);
	}

	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates Screen

			// Go to sleep for 12 milliseconds
			try
			{
				Thread.sleep(25);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public static void main(String[] args)
	{
		// Class instance or Object
		Game g = new Game();
		g.run();
	}
}
