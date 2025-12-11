import java.awt.*;
import java.awt.event.*;

class BallGame extends Frame implements KeyListener,ActionListener{
	int x=290,y=50;
	int rx=250;
	int speed=3;
	boolean bx=true,by=true,message=false;
	Button b;
	BallGame(){
		super("Ball Game");
		setSize(600,800);
		setLocationRelativeTo(null);
		setLayout(null);
		setBackground(Color.black); 
		b=new Button("Restart");
		b.setBounds(250,450,100,30);
		add(b);
		b.setVisible(false);
		b.addActionListener(this);
		
		addKeyListener(this);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		setFocusable(true);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		x=290; y=50; rx=250;
		message=false; bx=true; by=false;
		b.setVisible(false);
		requestFocus();
		repaint();
	}
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e){
		if(rx<=0) rx+=10;
		if(rx>=500) rx-=10;
		switch(e.getKeyCode()){
			case KeyEvent.VK_LEFT,KeyEvent.VK_A:
				rx-=10; break;
			case KeyEvent.VK_RIGHT,KeyEvent.VK_D:
				rx+=10; break;
		}
		repaint();
	}
	
	public void paint(Graphics g){
		g.setColor(Color.yellow);
		g.fillOval(x,y,20,20);
		g.fillRoundRect(rx,700,100,5,5,5);
		if(message){
			g.setColor(Color.darkGray);
			g.fillRoundRect(100,300,400,200,50,50);
			g.setColor(Color.red);
			g.setFont(new Font("Serif",Font.BOLD,30));
			g.drawString(":: You are Failed ::",180,350);
			g.drawString("Try Again !!",230,400);
			b.setVisible(true);
			return;
		}
		if(rx>=(x-100) && rx<=x && y==680)
			by=true;
		if(by){
			try{ Thread.sleep(speed); }catch(Exception e){}
			y-=2;
			if(y==30)
				by=false;
		}
		else if(!by){
			try{ Thread.sleep(speed); }catch(Exception e){}
			y+=2;
			if(y==800){
				message=true;
				repaint();
			}
		}
		if(bx){
			try{ Thread.sleep(speed); }catch(Exception e){}
			x-=2;
			if(x==0)
				bx=false;
		}
		else if(!bx){
			try{ Thread.sleep(speed); }catch(Exception e){}
			x+=2;
			if(x==580)
				bx=true;
		}
		repaint();
	}

	public static void main(String[] args){
		new BallGame();
	}
}