import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MarkerSlider.Listeners.MarkerListener;
import MarkerSlider.Markers.AMarker;
import MarkerSlider.Markers.SingleMarker;
import MarkerSlider.Sliders.MarkerSlider;

/**
 * Created by Cameron on 1/30/2016.
 */
public class SwingSliderExample extends JPanel {
    private MarkerSlider slider;
    private JButton btnOk;
    private int count;

    public SwingSliderExample() {
        super(true);
        initComponents();

        addListeners();
    }

    private void addListeners() {
        this.btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Adding a Mark.");
                addMark();
            }
        });

        this.slider.addMarkerListener(new MarkerListener() {
            @Override
            public void fireMarkerStateChanged(AMarker mark) {
                System.out.println("A marker changed state: " + mark);
            }
        });
    }

    private void addMark() {
        SingleMarker mark = new SingleMarker(this.slider, this.slider.getValue(), this.count++);
        this.slider.addMarker(mark);

        this.slider.setValue(this.slider.getValue()+ (int)(0.1 * (double) this.slider.getValue()));

        printMarkers();
        slider.repaint();
    }



    private void printMarkers() {
        System.out.println("Markers... " + this.slider);
    }

    private void initComponents() {
        this.count = 0;
        this.setLayout(new BorderLayout());
        this.slider = new MarkerSlider();

        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMinimum(0);
        slider.setMaximum(100);

        // We'll just use the standard numeric labels for now...
        slider.setLabelTable(slider.createStandardLabels(10));
        add(slider, BorderLayout.NORTH);


        this.btnOk = new JButton("OK");
        add(btnOk, BorderLayout.SOUTH);
    }

    public static void main(String s[]) {
        JFrame frame = new JFrame("Slider Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new SwingSliderExample());
        frame.pack();
        frame.setVisible(true);
    }

}
