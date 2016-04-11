package MarkerSlider.Markers;

import MarkerSlider.Sliders.MarkerSlider;
import MarkerSlider.Sliders.MarkerSliderUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Cameron on 1/30/2016.
 */
abstract public class AMarker extends JComponent {
    protected int markerValue;
    protected Icon markerIcon;

    protected MarkerSlider slider;


    public AMarker() { init(); }

    public AMarker(MarkerSlider slider, int value) {
        this(value);
        this.slider = slider;
    }

    private AMarker(int value) {
        init();
        setValue(value);
    }

    private void init() {
        this.slider = null;
        this.markerValue = -1;
        setMarkerIcon(getDefaultIcon());
    }

    public Icon getDefaultIcon() {
        return UIManager.getDefaults().getIcon("Slider.horizontalThumbIcon");
    }

    public Icon getDefaultSelectedIcon() {
        return UIManager.getDefaults().getIcon("Slider.verticalThumbIcon");
    }

    public boolean tryCauseStateChange(MouseEvent e) {
        return false;
    }


    public int getValue() { return this.markerValue; }
    public void setValue(int value) { this.markerValue = value; }


    public Icon getMarkerIcon() { return this.markerIcon; }
    public void setMarkerIcon(Icon icon) {
        this.markerIcon = icon;
        Dimension markerDimension = new Dimension(this.markerIcon.getIconWidth(), this.markerIcon.getIconHeight());

        // update size
        setPreferredSize(markerDimension);
        setSize(markerDimension);

        // update location/position and bounds
        if(this.slider != null && this.slider.getUI() != null) {
            ((MarkerSliderUI) this.slider.getUI()).updateMarkerUIPosition(this);
        }
    }

    public void updateLocation(int xPositionForValue, int yPositionForValue) {
        setLocation(xPositionForValue - (this.markerIcon.getIconWidth()/2), yPositionForValue);
        setBounds(getX(), getY(), this.markerIcon.getIconWidth(), this.markerIcon.getIconHeight());
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        markerIcon.paintIcon(this, g, this.getX(), this.getY());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        markerIcon.paintIcon(this, g, this.getX(), this.getY());
    }

    @Override
    public String toString() {
        return ""+this.markerValue;
    }
}
