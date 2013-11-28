package view;

import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class HintedPasswordField extends JPasswordField
{
    private final JTextField hintField;

    public HintedPasswordField (int columns, String hint)
    {
        super (columns);

        //setBorder (new RoundedRectableBorder(false, true));

        hintField = new JTextField (hint);
        hintField.setBorder(BorderFactory.createEmptyBorder (5, 5, 5, 5));
    }

    @Override
    protected void paintComponent (Graphics g)
    {
        super.paintComponent (g);

        if (getPassword ().length == 0)
        {
            hintField.setBounds (getBounds ());
            hintField.setForeground (getDisabledTextColor());
            hintField.setOpaque (false);
            hintField.paint (g);
        }
    }
}