package MarkerSlider.Sliders;

import MarkerSlider.Listeners.MarkerListener;
import MarkerSlider.Markers.AMarker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cameron on 1/30/2016.
 */
public class MarkerSlider extends JSlider {

    private MarkerModel markerModel;
    private MarkerSliderUI markerSliderUI = new MarkerSliderUI(this);

    private List<MarkerListener> markerListeners = new ArrayList<>();


    public MarkerSlider() {
        init();
    }

    private void init() {
        addListeners();
        markerModel = new MarkerModel();
        setUI(markerSliderUI);
    }

    private void addListeners() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // do nothing - block
            }

            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // do nothing - block
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // do nothing - block
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // do nothing - block
            }
        } );
    }

    private void handleMouseEvent(MouseEvent e) {
        this.markerSliderUI.updateAllMarkerUIPositions();
        checkFireMouseEvents(e);
    }

    private boolean checkFireMouseEvents(MouseEvent e) {
        boolean markerEventOccured = false;

        for(AMarker mark : this.markerModel.getMarkers()) {
            if(mark.tryCauseStateChange(e)) {

                for(MarkerListener listener : markerListeners) {
                    listener.fireMarkerStateChanged(mark);
                }

                markerEventOccured = true;
            }
        }

        return markerEventOccured;
    }

    public void fireMarkerStateChange(AMarker mark) {
        for(MarkerListener listener : markerListeners) {
            listener.fireMarkerStateChanged(mark);
        }
    }

    public void addMarkerListener(MarkerListener listener) {
        this.markerListeners.add(listener);
    }


    public void addMarker(AMarker mark) {
        if(mark != null) {
            markerModel.addMarker(mark);    // keep track in model
            markerSliderUI.addMarker(mark); // keep track in UI
            this.add(mark);                 // keep track in Slider
        }
    }

    public AMarker removeMarker(AMarker desiredMark) {
        AMarker markRemoved = null;

        if(desiredMark != null) {
            markRemoved = markerModel.removeMarker(desiredMark); // remove from model
            markerSliderUI.removeMarker(markRemoved);            // remove from UI
            this.remove(markRemoved);                            // remove from Slider
        }

        return markRemoved;
    }

    public List<AMarker> getMarkers() {
        return this.markerModel.getMarkers();
    }

    public AMarker getMarker(AMarker desiredMark) {
        return this.markerModel.getMarker(desiredMark);
    }

    public void clearMarkers() {
        this.markerModel.clearMarkers();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public String toString() {
        return this.markerModel.toString();
    }
}
