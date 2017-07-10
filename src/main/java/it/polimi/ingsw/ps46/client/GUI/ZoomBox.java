package it.polimi.ingsw.ps46.client.GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ZoomBox extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7119556971095074230L;
	
	private static ZoomBox zoomBox = null;
	private ImageZoom imageZoom;
	public static void setImage( BufferedImage image) {
		System.out.println("Mostriamo una carta!");
		ZoomBox zoomBox = getZoomBox();
		zoomBox.imageZoom.setImage(image);
	}
	
	public static ZoomBox getZoomBox() {
		if (zoomBox == null)
			zoomBox = new ZoomBox();
		return zoomBox;
	}
	
	private ZoomBox() {
		
		this.setLayout(new BorderLayout());
		
		ZoomArea zoomArea = new ZoomArea();
		imageZoom = new ImageZoom(zoomArea);
		JPanel chooseZoomPanel = imageZoom.getUIPanel();
		//chooseZoomPanel.setPreferredSize(new Dimension ( (int) this.getPreferredSize().getWidth()/2, (int) this.getPreferredSize().getHeight()));
		zoomArea.setPreferredSize(new Dimension ( (int) this.getPreferredSize().getWidth()*6/7, (int) this.getPreferredSize().getHeight()));
		//chooseZoomPanel.setPreferredSize(new Dimension((int) pWidth/16, (int) (pHeight*3)/4));
		//chooseZoomPanel.setMaximumSize(chooseZoomPanel.getPreferredSize());
		zoomArea.setMaximumSize(zoomArea.getPreferredSize());
		this.add(chooseZoomPanel, BorderLayout.WEST);
		this.add(zoomArea, BorderLayout.CENTER);
		this.add(new JScrollPane(zoomArea));
	}
}


class ZoomArea extends JPanel {
	/**
	*/
	private static final long serialVersionUID = -7215416494077532904L;
	private BufferedImage image;
	private double scale;

	public ZoomArea() {
		scale = 0.35;
		setBackground(Color.black);
		try {
        	image = ImageIO.read(getClass().getResource("img/leaders_card/leaders_b_c_00.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D)g;
    	g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
    			RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		int w = getWidth();
		int h = getHeight();
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();
		double x = (w - scale * imageWidth)/2;
		double y = (h - scale * imageHeight)/2;
		AffineTransform at = AffineTransform.getTranslateInstance(x,y);
		at.scale(scale, scale);
		g2.drawRenderedImage(image, at);
	}
  

	public Dimension getPreferredSize() {		
	    int w = (int)(scale * image.getWidth());
	    int h = (int)(scale * image.getHeight());
	    return new Dimension(w, h);
	}
  
	public void setScale(double s) {
		scale = s;
		revalidate();      // update the scroll pane
		repaint();
	}
    
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

}
  
class ImageZoom {
    private ZoomArea zoomArea;
    private JSpinner spinner;
    
    public ImageZoom(ZoomArea ip){
        zoomArea = ip;
    }
  
    public JPanel getUIPanel() {
    	
		SpinnerNumberModel model = new SpinnerNumberModel(0.35, 0.1, 1.4, .01);
		spinner = new JSpinner(model);
		spinner.setPreferredSize(new Dimension(45, spinner.getPreferredSize().height));
		spinner.addChangeListener(new ChangeListener() {
        	
			public void stateChanged(ChangeEvent e) {
				double scale = (Double)spinner.getValue();
				zoomArea.setScale(scale);
			}
        });
        
		JPanel panel = new JPanel();
		panel.add(new JLabel("scale"));
		panel.add(spinner);
		return panel;
    }
    
    public void setImage(BufferedImage image) {
		System.out.println("Ora la metto!");
		zoomArea.setImage(image);
		double scaleX, scaleY;
		scaleX = (double)(zoomArea.getWidth()-spinner.getWidth())/image.getWidth();
		scaleY = (double)zoomArea.getHeight()/image.getHeight();
		System.out.println("La scalaX " + scaleX);
		System.out.println("La scalaY " + scaleY);
		if (scaleX > 0 && scaleY > 0)
			spinner.setValue(Math.min(scaleX, scaleY));
		zoomArea.repaint();
	}
}
