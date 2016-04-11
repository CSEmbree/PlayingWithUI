package MarkerSlider.Sliders;

import MarkerSlider.Markers.AMarker;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

/**
 * Created by Cameron on 4/10/2016.
 */
public class MarkerSliderUI extends BasicSliderUI {

    protected JSlider slider;


    public MarkerSliderUI(JSlider slider) {
        super(slider);
        this.slider = slider;
    }

    public void addMarker(AMarker mark) {
        //TODO add UI listeners ?
    }

    public void removeMarker(AMarker mark) {
        //TODO remove UI listeners ?
    }

    public int getThumbX() {
        return xPositionForValue(this.slider.getValue());
    }

    public int getThumbY() {
//        return yPositionForValue(this.slider.getValue());
        return 0;
    }

    public int getYPositionForValue(int value) {
        int yPos = -1;

        if(isValueInBounds(value)) {
//            yPos = yPositionForValue(value);
            yPos = 0;
        }

        return yPos;
    }

    public int getXPositionForValue(int value) {
        int xPos = -1;

        if(isValueInBounds(value)) {
            xPos = xPositionForValue(value);
        }

        return xPos;
    }

    private boolean isValueInBounds(int value) {
        return (value >= this.slider.getMinimum() && value <= this.slider.getMaximum());
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);

        if(c instanceof MarkerSlider) {
            paintAllMarkers(g);
        }
    }

    public void paintAllMarkers(Graphics g) {
        Component[] components = this.slider.getComponents();

        if(components != null && components.length > 0) {
            updateAllMarkerUIPositions();

            for(int i = 0; i < components.length; i++) {
                // repaint all the AMarkers JComponents installed in this UI
                if(components[i] instanceof AMarker) {
                    paintMarker( g, (AMarker) components[i]);
                }
            }
        }
    }

    public void paintMarker(Graphics g, AMarker mark) {
        mark.paint(g);
    }

    public void updateAllMarkerUIPositions() {
        Component[] components = this.slider.getComponents();

        if(components != null) {
            for(int i = 0; i < components.length; i++) {
                // update all the AMarker JComponents installed in this UI
                if(components[i] instanceof AMarker) {
                    updateMarkerUIPosition( (AMarker) components[i]);
                }
            }
        }
    }

    public void updateMarkerUIPosition(JComponent c) {
        if(c != null && c instanceof AMarker) {
            AMarker aMarker = (AMarker) c;

            int xPosForValue = getXPositionForValue(aMarker.getValue());
            int yPosForValue = getYPositionForValue(aMarker.getValue());

            aMarker.updateLocation(xPosForValue, yPosForValue);
        }
    }
}
