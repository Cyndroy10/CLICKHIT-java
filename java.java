
package javaapplication1;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.time.Clock;
import java.time.Duration;
import java.lang.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

public class java extends Applet implements ActionListener,Runnable {
     
    int s=0, hour, minute, second, millisecond, flag=0, flag1=0, x, y; 
    Label display;
    Timer timer, timer1;
    String disp; 
    Button b1, start;
    boolean on; 
    Random r = new Random();
   
     public void init() {
         
          new Thread(this, "StopWatch").start();
         
          //Stopwatch
          on = false; 
          hour = minute = second = millisecond = 0;
          
          //Buttons
          b1 = new Button("O");
          start = new Button("Start"); 
          add(start);
          add(b1);
          start.setBounds(1000,10,50,20);
          b1.addActionListener(this);
          start.addActionListener((ActionListener) this); 
          
          //Backgorund
          setLayout(null);
          setBackground(Color.CYAN);
          
          // Label 
	  display = new Label(); 
          disp = "00 : 00 : 00 : 000"; 
          display.setText(disp);
          add(display);
          display.setBounds(1200,10,100,20);
          
         
              start();
          
    }
     public void start() {
         
         //Game-timer
         /*timer1 = new Timer();
          timer1.schedule( new TimerTask()
              {
               public void run()
                {
                    stop();
                   timer1.cancel();
                }
              },10000);
          */
         timer = new Timer();
             timer.schedule(new TimerTask()
              {
               public void run()
                {
                  x=r.nextInt(1000);
                  y=r.nextInt(500);
                  draw();
                }
              },0,1000);
     
     }
     public void paint(Graphics g)
     {
          if(flag==1)
          {
            Font f=new Font("Times New Roman",Font.BOLD,22);
            g.setFont(f);
            g.setColor(Color.BLACK);
            g.drawString("Score:-"+s,10,20);
          }
           if(flag1==1)
          {
            Font f=new Font("Times New Roman",Font.BOLD,22);
            g.setFont(f);
            g.setColor(Color.BLACK);
            g.drawString("GAME OVER...........Score:-"+s,10,20);
          }
     }
     public void actionPerformed(ActionEvent e)
     {
        if (e.getSource() == b1){
            flag=1;
            s++;
            repaint();
            b1.setVisible(false);
        }
         
        if (e.getSource() == start) { 
	    // stopwatch and game is on 
       
	    on = true; 
            new Thread(this, "StopWatch").start(); 
        } 
     }
     public void draw()
     {
         if(flag1==0)
         {
         b1.setBounds(x,y,40,40);
         b1.setVisible(true);
         }
         else
             b1.setVisible(false);
     }
     public void stop()
     {
         flag1=1;
         flag=0;
          on = false; 
         repaint();
         JOptionPane.showMessageDialog(null,"GAME OVER");
       System.exit(0);
     }
      
      public void update() 
	{ 
		millisecond++; 
		if (millisecond == 1000) { 
			millisecond = 0; 
			second++; 
			if (second == 10) { 
				stop();
				
			} 
		} 
	} 
      public void changeLabel() 
	{ 

		// Properly formatting the display of the timer 
		if (hour < 10) 
			disp = "0" + hour + " : "; 
		else
			disp = hour + " : "; 

		if (minute < 10) 
			disp += "0" + minute + " : "; 
		else
			disp += minute + " : "; 

		if (second < 10) 
			disp += "0" + second + " : "; 
		else
			disp += second + " : "; 

		if (millisecond < 10) 
			disp += "00" + millisecond; 
		else if (millisecond < 100) 
			disp += "0" + millisecond; 
		else
			disp += millisecond; 

		display.setText(disp); 
	} 
      public void run() 
	{ 

		// while the stopwatch is on 
		while (on) { 
			try { 
				// pause 1 millisecond 
				Thread.sleep(1); 
				// update the time 
				update(); 
				// changeLabel 
				changeLabel(); 
			} 
			catch (InterruptedException e) { 
				System.out.println(e); 
			} 
		} 
	} 

    // TODO overwrite start(), stop() and destroy() methods
}
