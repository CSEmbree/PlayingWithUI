package MarkerSlider.Markers;

import MarkerSlider.Sliders.MarkerSlider;
import MarkerSlider.Sliders.MarkerSliderUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Cameron on 4/10/2016.
 */
public class RangeMarker extends AMarker {

    Icon markerStartIcon;
    Icon markerStopIcon;

    int markerStopValue;

    public RangeMarker() {
        super();
        init();
    }

    public RangeMarker(MarkerSlider slider, int start, int stop) {
        super(slider, start);

        if(start >= stop) {
            throw new ExceptionInInitializerError("Invalid Range Condition: Start ("+markerValue+") > Stop ("+markerStopValue+").");
        }

        this.markerStopValue = stop;
    }

    private void init() {
        this.markerStopValue = 0;
        this.markerStartIcon = super.getDefaultIcon();
        this.markerStartIcon = super.getDefaultIcon();
    }

    private void localRepaint(Graphics g) {
        int x, y, xStart, xStop;

        // paint the start Icon
        xStart = this.getX() - (markerStartIcon.getIconWidth()/2);

        x = xStart;
        y = this.getY();
        markerStartIcon.paintIcon(this, g, x, y);



        // paint the stop Icon
        int xDeltaToStop = ( ((MarkerSliderUI) slider.getUI()).getXPositionForValue(markerStopValue) - markerStopIcon.getIconWidth()/2) - xStart;
        xStop = xStart + xDeltaToStop;

        x = xStop;
        y = this.getY();
        markerStopIcon.paintIcon(this, g, x, y);


        // paint the area between start and stop (XStart -> XStop);
    }

    @Override
    public boolean tryCauseStateChange(MouseEvent e) {
        //TODO[cse]: check for things like start/stop selected or range selected.
        return super.tryCauseStateChange(e);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        localRepaint(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        localRepaint(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.markerStartIcon.getIconWidth(), this.markerStartIcon.getIconHeight());
    }

    @Override
    public String toString() {
        return ""+this.markerStartIcon;
    }
}
