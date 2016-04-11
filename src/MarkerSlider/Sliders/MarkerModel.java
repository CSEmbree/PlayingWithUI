package MarkerSlider.Sliders;

import MarkerSlider.Markers.AMarker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cameron on 4/10/2016.
 */
public class MarkerModel {
    protected ArrayList<AMarker> markers;

    public MarkerModel() { this.markers = new ArrayList<AMarker>(); }

    public MarkerModel(List<AMarker> markers) {
        this.markers = new ArrayList<>(markers);
    }

    public void addMarker(AMarker mark) {
        this.markers.add(mark);
    }

    public void addMarkers(List<AMarker> markers) {
        this.markers.addAll(markers);
    }

    public AMarker removeMarker(AMarker desiredMark) {
        AMarker markRemoved = null;

        if(desiredMark != null) {
            getMarker(desiredMark, true);
        }

        return markRemoved;
    }

    public AMarker removeMarker(int index) {
        return this.markers.remove(index);
    }

    public void clearMarkers() {
        this.markers.clear();
    }

    public List<AMarker> getMarkers() {
        return this.markers;
    }

    public AMarker getMarker(AMarker desiredMark) {
        AMarker markFound = null;

        if(desiredMark != null) {
            getMarker(desiredMark, false);
        }

        return markFound;
    }

    private AMarker getMarker(AMarker desiredMark, boolean removeIt) {
        AMarker markFound = null;

        for (AMarker currentMark : this.markers) {
            if (currentMark.equals(desiredMark)) {
                markFound = currentMark;

                if(removeIt) this.markers.remove(currentMark);
            }
        }

        return markFound;
    }

    private AMarker getMarker(int index) {
        AMarker markFound = null;

        if(containsIndex(index)) {
            markFound = this.markers.get(index);
        }

        return  markFound;
    }

    public boolean containsMark(AMarker mark) {
        boolean containsMark = false;

        if(getMarker(mark) != null) {
            containsMark = true;
        }

        return containsMark;
    }

    public boolean containsIndex(int index) {
        return (index >= 0 && index < markers.size());
    }

    @Override
    public String toString() {
        final String elementSeperator = ",";
        final String indexSeperator = "-";

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < this.markers.size(); i++) {
            builder.append(i);
            builder.append(indexSeperator);
            builder.append(this.markers.get(i).toString());

            if(i < this.markers.size() - 1) {
                builder.append(elementSeperator);
            }
        }

        return builder.toString();
    }
}
