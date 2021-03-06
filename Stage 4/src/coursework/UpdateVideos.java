package coursework;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UpdateVideos extends JFrame implements ActionListener {

    JTextField trackNo = new JTextField(2);
    JTextField trackRating = new JTextField(2);
    TextArea information = new TextArea(6, 50);
    JButton enter = new JButton("enter");

    public UpdateVideos() {
        setLayout(new BorderLayout());
        setBounds(100, 100, 500, 200);
        setTitle("Create video playlist");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel top = new JPanel();
        top.add(new JLabel("Enter Video Number:"));
        top.add(trackNo);
        top.add(new JLabel("Enter Video Rating:"));
        top.add(trackRating);
        top.add(enter);
        enter.addActionListener(this);
        add("North", top);
        JPanel middle = new JPanel();
        information.setText("");
        middle.add(information);
        add("Center", middle);

        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter) {
            String key = trackNo.getText();
            String rating = trackRating.getText();
            if (isNumeric(key) && isNumeric(rating)) {
                String name = VideoData.getName(key);
                if (name == null) {
                    information.setText("No such video number");
                } else {
                    try {
                        int rating_value = Integer.parseInt(rating);
                        information.setText(name + " - " + VideoData.getDirector(key));
                        information.append("\nRating: "
                                + stars(rating_value));
                        information.append("\nPlay count: " + VideoData.getPlayCount(key));

                        VideoData.setRating(key, rating_value);

                    } catch (NumberFormatException ex) {
                        trackRating.setText("");
                        information.setText("Rating is invalid");
                    } catch (NullPointerException ex) {
                        trackRating.setText("");
                        information.setText("Rating is invalid");
                    }
                }
            } else {
                information.setText("please insert a valid number");
            }
            trackNo.setText("");
            trackRating.setText("");
        }
    }

    private String stars(int rating) {
        String stars = "";
        for (int i = 0; i < rating; ++i) {
            stars += "*";
        }
        return stars;
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
