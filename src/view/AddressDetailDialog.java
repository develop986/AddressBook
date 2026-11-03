package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import model.Address;

/**
 * アドレス帳の詳細情報を入力および編集するダイアログ.
 */
public class AddressDetailDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField txtName;
    private JTextField txtKana;
    private JTextField txtTel;
    private JTextField txtEmail;
    private JTextField txtZipCode;
    private JTextField txtAddress;

    private JTextArea txtMemo;

    private JButton btnSave;
    private JButton btnCancel;

    private JLabel lblMessage;

    private DialogMode mode;

    private Address address;
    private boolean saved = false;

    /**
     * アドレス詳細ダイアログを生成する.
     *
     * @param owner   親フレーム
     * @param mode    表示モード
     * @param address 編集対象のアドレス情報
     */
    public AddressDetailDialog(final Frame owner, final DialogMode mode, final Address address) {

        super(owner, true);

        this.mode = mode;
        this.address = address;

        initialize();

        if (address != null) {
            loadAddress(address);
        }
    }

    /**
     * アドレス情報を画面へ表示する.
     *
     * @param address 表示対象のアドレス情報
     */
    public void loadAddress(final Address address) {

        txtName.setText(address.getName());
        txtKana.setText(address.getKana());
        txtTel.setText(address.getTel());
        txtEmail.setText(address.getEmail());
        txtZipCode.setText(address.getZipCode());
        txtAddress.setText(address.getAddress());
        txtMemo.setText(address.getMemo());
    }

    /**
     * 画面に入力された内容からアドレス情報を取得する.
     *
     * @return アドレス情報
     */
    public Address getAddress() {

        Address result = address == null ? new Address() : address;

        result.setName(txtName.getText().trim());
        result.setKana(txtKana.getText().trim());
        result.setTel(txtTel.getText().trim());
        result.setEmail(txtEmail.getText().trim());
        result.setZipCode(txtZipCode.getText().trim());
        result.setAddress(txtAddress.getText().trim());
        result.setMemo(txtMemo.getText());

        return result;
    }

    /**
     * 画面を初期化する.
     */
    private void initialize() {

        setTitle(mode == DialogMode.NEW ? "アドレス新規登録" : "アドレス編集");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(getOwner());

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));

        setContentPane(contentPane);

        JPanel pnlCenter = new JPanel();
        pnlCenter.setLayout(new BorderLayout(10, 10));

        contentPane.add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlForm = new JPanel();
        pnlForm.setBorder(new TitledBorder("基本情報"));

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[] { 120, 400 };
        pnlForm.setLayout(gbl);

        int row = 0;

        txtName = new JTextField(30);
        addField(pnlForm, "氏名", txtName, row++);

        txtKana = new JTextField(30);
        addField(pnlForm, "フリガナ", txtKana, row++);

        txtTel = new JTextField(30);
        addField(pnlForm, "電話番号", txtTel, row++);

        txtEmail = new JTextField(30);
        addField(pnlForm, "メールアドレス", txtEmail, row++);

        txtZipCode = new JTextField(30);
        addField(pnlForm, "郵便番号", txtZipCode, row++);

        txtAddress = new JTextField(30);
        addField(pnlForm, "住所", txtAddress, row++);

        pnlCenter.add(pnlForm, BorderLayout.NORTH);

        JPanel pnlMemo = new JPanel(new BorderLayout(5, 5));
        pnlMemo.setBorder(new TitledBorder("備考"));

        txtMemo = new JTextArea();
        txtMemo.setLineWrap(true);
        txtMemo.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(txtMemo);

        pnlMemo.add(scrollPane, BorderLayout.CENTER);

        pnlCenter.add(pnlMemo, BorderLayout.CENTER);

        JPanel pnlBottom = new JPanel(new BorderLayout());

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnSave = new JButton(mode == DialogMode.NEW ? "登録" : "保存");

        btnCancel = new JButton("キャンセル");

        pnlButton.add(btnSave);
        pnlButton.add(btnCancel);

        pnlBottom.add(pnlButton, BorderLayout.NORTH);

        lblMessage = new JLabel(" ");
        lblMessage.setHorizontalAlignment(SwingConstants.LEFT);

        pnlBottom.add(lblMessage, BorderLayout.SOUTH);

        contentPane.add(pnlBottom, BorderLayout.SOUTH);

        btnSave.addActionListener(e -> {

            saved = true;

            dispose();
        });

        btnCancel.addActionListener(e -> {

            saved = false;

            dispose();
        });
    }

    /**
     * 入力項目を追加する.
     *
     * @param panel     配置先パネル
     * @param labelText ラベル文字列
     * @param field     入力欄
     * @param row       行番号
     */
    private void addField(final JPanel panel, final String labelText, final JTextField field,
            final int row) {

        GridBagConstraints lblGbc = new GridBagConstraints();

        lblGbc.gridx = 0;
        lblGbc.gridy = row;
        lblGbc.anchor = GridBagConstraints.WEST;
        lblGbc.insets = new Insets(5, 5, 5, 5);

        panel.add(new JLabel(labelText), lblGbc);

        GridBagConstraints txtGbc = new GridBagConstraints();

        txtGbc.gridx = 1;
        txtGbc.gridy = row;
        txtGbc.weightx = 1.0;
        txtGbc.fill = GridBagConstraints.HORIZONTAL;
        txtGbc.insets = new Insets(5, 5, 5, 5);

        panel.add(field, txtGbc);
    }

    /**
     * 保存ボタンが押下されたかを返す.
     *
     * @return 保存済みの場合 true
     */
    public boolean isSaved() {
        return saved;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JTextField getTxtKana() {
        return txtKana;
    }

    public JTextField getTxtTel() {
        return txtTel;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtZipCode() {
        return txtZipCode;
    }

    public JTextField getTxtAddress() {
        return txtAddress;
    }

    public JTextArea getTxtMemo() {
        return txtMemo;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JLabel getLblMessage() {
        return lblMessage;
    }
}