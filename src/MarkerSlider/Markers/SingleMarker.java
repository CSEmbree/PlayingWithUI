package MarkerSlider.Markers;

import MarkerSlider.Sliders.MarkerSlider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Cameron on 4/10/2016.
 */
public class SingleMarker extends AMarker {
    private int record;

    private boolean selected;

    private final Icon DEFAULT_MARKER_SELECTED = getDefaultSelectedIcon();
    private final Icon DEFAULT_MARKER_DESELECTED = getDefaultIcon();

    private Icon markerSelected = DEFAULT_MARKER_SELECTED;
    private Icon markerDeselected = DEFAULT_MARKER_DESELECTED;

    public SingleMarker(MarkerSlider slider, int value, int record) {
        super(slider, value);
        init();
        addListeners();


        setRecord(record);
    }

    private void init() {
    }

    private void addListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                handleMouseReleased(e);
            }
        });
    }

    private boolean handleMouseReleased(MouseEvent e) {
        boolean eventHandled = false;

        // TODO temp setting of correct bounds... not sure why not correct at this time FIX ME
        Rectangle markerBounds = this.getBounds();
        markerBounds.setLocation(new Point((int)getLocationOnScreen().getX(), (int)getLocationOnScreen().getY()));

        if( markerBounds.contains(e.getLocationOnScreen()) ){
            setSelected(!selected); // flip from selected to not and vice versa
            this.slider.fireMarkerStateChange(this);
            eventHandled = true;
        }

        // TODO[cse] this should work without the above. Something to do with where the mouse and marker are in the actual screen space
//        if(this.contains(e.getLocationOnScreen())) {
//            setSelected(!selected); // flip from selected to not and vice versa
//            eventHandled = true;
//        }

        slider.repaint();

        return eventHandled;
    }

    public void updateSelection() {
        if(this.selected) {
            setMarkerIcon(markerSelected);
        } else {
            setMarkerIcon(markerDeselected);
        }
    }

    public void setMarkerSelectedIcon(Icon icon) {
        this.markerSelected = icon;
    }

    public void setMarkerDeselectedIcon(Icon icon) {
        this.markerDeselected = icon;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        updateSelection();
    }

    public int getRecord() {
        return this.record;
    }
    public void setRecord(int record) { this.record = record; }


    @Override
    public boolean tryCauseStateChange(MouseEvent e) {
        return handleMouseReleased(e);
    }

    @Override
    public String toString() {
        return "["+super.toString() + "::"+this.record+"]";
    }
}
