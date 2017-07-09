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
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ZoomBox extends JPanel {
	
	ZoomBox zoomBox;
	ZoomArea area;
	JPanel chooseZoomPanel;
	static BufferedImage image;
	
	 public static void setImage( BufferedImage image) {
	        ZoomBox.image = image;
	 }
	
	public ZoomBox() {
		
		zoomBox = this;
		this.setLayout(new BorderLayout());
		
		ZoomArea area = new ZoomArea();
		ImageZoom zoom = new ImageZoom(area);
	    JPanel chooseZoomPanel = zoom.getUIPanel();
	    //chooseZoomPanel.setPreferredSize(new Dimension ( (int) this.getPreferredSize().getWidth()/2, (int) this.getPreferredSize().getHeight()));
	    area.setPreferredSize(new Dimension ( (int) this.getPreferredSize().getWidth()*6/7, (int) this.getPreferredSize().getHeight()));
	    //chooseZoomPanel.setPreferredSize(new Dimension((int) pWidth/16, (int) (pHeight*3)/4));
	    //chooseZoomPanel.setMaximumSize(chooseZoomPanel.getPreferredSize());
	    area.setMaximumSize(area.getPreferredSize());
	    this.add(chooseZoomPanel, BorderLayout.WEST);
	    this.add(area, BorderLayout.CENTER);
	    this.add(new JScrollPane(area));
	}
}


 class ZoomArea extends JPanel {
	 
	BufferedImage image;
    double scale;
  
    public ZoomArea() {
        scale = 1.0;
        setBackground(Color.black);
        try {
			image = ImageIO.read(getClass().getResource("img/token/red_token.png"));
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
  
}
  
class ImageZoom {
    ZoomArea zoomArea;
  
    public ImageZoom(ZoomArea ip){
        zoomArea = ip;
    }
  
    public JPanel getUIPanel() {
    	
        SpinnerNumberModel model = new SpinnerNumberModel(1.0, 0.1, 1.4, .01);
        final JSpinner spinner = new JSpinner(model);
        spinner.setPreferredSize(new Dimension(45, spinner.getPreferredSize().height));
        spinner.addChangeListener(new ChangeListener() {
        	
            public void stateChanged(ChangeEvent e) {
                float scale = ((Double)spinner.getValue()).floatValue();
                zoomArea.setScale(scale);
            }
        });
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("scale"));
        panel.add(spinner);
        return panel;
    }
}
