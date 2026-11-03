package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.Address;

public class AddressListFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtSearchName;

    private JTable tblAddress;

    private JButton btnSearch;
    private JButton btnClear;
    private JButton btnNew;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnExit;

    private JLabel lblMessage;

    public AddressListFrame() {

        initialize();
    }

    private void initialize() {

        setTitle("アドレス帳");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));

        setContentPane(contentPane);

        createSearchPanel();
        createTablePanel();
        createBottomPanel();
        
        btnNew.addActionListener(e -> openNewDialog());
    }
    
    private void openNewDialog() {

        AddressDetailDialog dialog =
                new AddressDetailDialog(
                        this,
                        DialogMode.NEW,
                        null);

        dialog.setVisible(true);

        if (dialog.isSaved()) {

            Address address = dialog.getAddress();

            System.out.println(address.getName());

        }
    }
    
    private void createSearchPanel() {

        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlSearch.setBorder(new TitledBorder("検索条件"));

        pnlSearch.add(new JLabel("氏名"));

        txtSearchName = new JTextField(20);
        pnlSearch.add(txtSearchName);

        btnSearch = new JButton("検索");
        pnlSearch.add(btnSearch);

        btnClear = new JButton("全件表示");
        pnlSearch.add(btnClear);

        contentPane.add(pnlSearch, BorderLayout.NORTH);
    }

    private void createTablePanel() {

        String[] columns = {
                "氏名",
                "フリガナ",
                "電話番号",
                "メールアドレス"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblAddress = new JTable(model);
        tblAddress.setRowHeight(24);

        JScrollPane scrollPane = new JScrollPane(tblAddress);
        scrollPane.setBorder(new TitledBorder("連絡先一覧"));

        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    private void createBottomPanel() {

        JPanel pnlBottom = new JPanel(new BorderLayout());

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnNew = new JButton("新規");
        btnEdit = new JButton("編集");
        btnDelete = new JButton("削除");
        btnExit = new JButton("終了");

        pnlButton.add(btnNew);
        pnlButton.add(btnEdit);
        pnlButton.add(btnDelete);
        pnlButton.add(btnExit);

        pnlBottom.add(pnlButton, BorderLayout.NORTH);

        lblMessage = new JLabel("準備完了");
        lblMessage.setHorizontalAlignment(SwingConstants.LEFT);
        lblMessage.setBorder(new EmptyBorder(5, 5, 5, 5));

        pnlBottom.add(lblMessage, BorderLayout.SOUTH);

        contentPane.add(pnlBottom, BorderLayout.SOUTH);
    }

    public JTable getTblAddress() {
        return tblAddress;
    }

    public JTextField getTxtSearchName() {
        return txtSearchName;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public JButton getBtnNew() {
        return btnNew;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public JLabel getLblMessage() {
        return lblMessage;
    }
}
