
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author urmikapoor
 */

    import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JWindow;
import javax.swing.JPanel;

import com.jgoodies.animation.Animation;
import com.jgoodies.animation.AnimationEvent;
import com.jgoodies.animation.AnimationListener;
import com.jgoodies.animation.Animations;
import com.jgoodies.animation.Animator;
import com.jgoodies.animation.swing.animations.BasicTextAnimation;
import com.jgoodies.animation.swing.components.BasicTextLabel;
public class SplashScreen extends JWindow{
	BasicTextLabel label;
	public void Apresentacao()
	{
		JPanel content=(JPanel)getContentPane();
		content.setBackground(Color.WHITE);
		content.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		 label=new BasicTextLabel("");
		label.setFont(new Font("Tahoma",Font.BOLD,40));
		content.add(label);
		int width=500;
		int height=300;
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
		int x=(screen.width-width)/2;
		int y=(screen.height-height)/2;
		content.setBounds(x,y,width,height);
		content.setVisible(true);
		Animation animation=createAnimation();
		
		AnimationListener a1=new AnimationListener(){
			public void animationStopped(AnimationEvent arg0){
				
			}
			public void animationStarted(AnimationEvent arg0){
				synchronized("A")
				{"A".notify();}
			}
			
			};
		animation.addAnimationListener(a1);
		Animator animator=new Animator(animation,30);
		animator.start();
		synchronized("A"){
			try{
				"A".wait();
				
			}
			catch(InterruptedException e){
			}
			}
		}
		private Animation createAnimation(){
			Animation a1=BasicTextAnimation.defaultSpace(label, 5000, "App", Color.BLUE);
			Animation a2=BasicTextAnimation.defaultScale(label, 5000, "API", Color.GREEN);
			Animation a3=BasicTextAnimation.defaultFade(label, 5000, "Animate", Color.ORANGE);
			Animation a4=BasicTextAnimation.defaultSpace(label, 5000, "awww", Color.BLACK);
			Animation allseq=Animations.sequential(new Animation[]{
					Animations.pause(1000),a1,Animations.pause(1000),a2,Animations.pause(1000),a3,Animations.pause(1000),a4});
			return Animations.parallel(allseq,a1);
		}
		public static void main(String args[])
		{
			SplashScreen a = new SplashScreen();
			a.Apresentacao();
			System.exit(0);
			
		}
		

    
}
