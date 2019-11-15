
package com.carlosehr.commercialsystem.models;

/**
 *
 * @author Carlos Hoyos Rojas
 */
public class Provider {
    private long provider_id;
    private String name;
    private String contact;
    private String cellphone;
    private String phone;

    public Provider() {
    }

    public Provider(long provider_id) {
        this.provider_id = provider_id;
    }
    
    public Provider(long provider_id, String name, String contact, String cellphone, String phone) {
        this.provider_id = provider_id;
        this.name = name;
        this.contact = contact;
        this.cellphone = cellphone;
        this.phone = phone;
    }

    public long getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(long provider_id) {
        this.provider_id = provider_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
