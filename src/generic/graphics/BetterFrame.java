package generic.graphics;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by 254397 on 1/25/2017.
 */
public class BetterFrame extends Frame implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	public int mouseX, mouseY;
	private boolean clicking;
	private int mouseButton = - 1;

	public BetterFrame(String title, Dimension size) {
		super(title);
		setSize(size);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F1){
            if(DigitalTimer.window == MasterClock.CLOCKSTATE.CLOCK){
                DigitalTimer.switchState = true;
                DigitalTimer.window = MasterClock.CLOCKSTATE.STOPWATCH;
            }
            else{
                DigitalTimer.switchState = true;
                DigitalTimer.window = MasterClock.CLOCKSTATE.CLOCK;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_F2){
            if(DigitalTimer.window == MasterClock.CLOCKSTATE.CLOCK){
                DigitalTimer.switchState = true;
                DigitalTimer.window = MasterClock.CLOCKSTATE.TIMER;
            }
            else{
                DigitalTimer.switchState = true;
                DigitalTimer.window = MasterClock.CLOCKSTATE.CLOCK;
            }
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		clicking = true;
		mouseButton = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clicking = false;
		mouseButton = - 1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public boolean isClicking() {
		return clicking;
	}

	public int getMouseButton() {
		return mouseButton;
	}
}
