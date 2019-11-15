/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.models.Provider;
import java.util.List;

/**
 *
 * @author Carlos Hoyos Rojas
 */
public interface IProviderDAO {
    public List<Provider> listAll();
    public String insert(Provider prov);
    public String update(Provider prov);
    public String delete(Provider prov);
    public Provider findById(long idProvider);
    
}
