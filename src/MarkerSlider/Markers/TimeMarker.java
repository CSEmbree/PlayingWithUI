package MarkerSlider.Markers;

import MarkerSlider.Sliders.MarkerSlider;

/**
 * Created by Cameron on 1/30/2016.
 */
public class TimeMarker extends AMarker {

    private Object record;

    public TimeMarker(MarkerSlider slider, int value, Object record) {
        super(slider, value);
        setRecord(record);
    }



    public Object getRecord() {
        return this.record;
    }

    public void setRecord(Object record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "["+super.toString() + "::"+this.record+"]";
    }
}
