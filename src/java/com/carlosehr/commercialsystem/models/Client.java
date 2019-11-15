
package com.carlosehr.commercialsystem.models;

/**
 *
 * @author Carlos Hoyos
 */
public class Client {
    private long clientId;
    private String idCard;
    private String companyName;
    private String contactName;
    private String address;
    private String fax;
    private String email;
    private String cellphone;
    private String phone;

    public Client() {
    }

    public Client(long clientId) {
        this.clientId = clientId;
    }

    public Client(long clientId, String idCard, String companyName, String contactName, String address, String fax, String email, String cellphone, String phone) {
        this.clientId = clientId;
        this.idCard = idCard;
        this.companyName = companyName;
        this.contactName = contactName;
        this.address = address;
        this.fax = fax;
        this.email = email;
        this.cellphone = cellphone;
        this.phone = phone;
    }
    
    

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    
}
