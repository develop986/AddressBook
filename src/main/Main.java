package main;

import javax.swing.SwingUtilities;

import view.AddressListFrame;

/**
 * アドレス帳アプリケーションの起動クラス.
 */
public class Main {

    /**
     * アプリケーションを起動する.
     *
     * @param args コマンドライン引数
     */
    public static void main(final String[] args) {

        SwingUtilities.invokeLater(() -> {

            AddressListFrame frame = new AddressListFrame();
            frame.setVisible(true);
        });
    }
}