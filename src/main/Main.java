package main;

import javax.swing.SwingUtilities;

import view.AddressListFrame;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            AddressListFrame frame = new AddressListFrame();
            frame.setVisible(true);

        });

    }

}