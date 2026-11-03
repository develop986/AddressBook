package model;

/**
 * アドレス帳で管理する連絡先情報を保持するモデル.
 */
public class Address {

    private Long id;

    private String name;
    private String kana;
    private String tel;
    private String email;
    private String zipCode;
    private String address;
    private String memo;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getKana() {
        return kana;
    }

    public void setKana(final String kana) {
        this.kana = kana;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(final String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(final String memo) {
        this.memo = memo;
    }

}