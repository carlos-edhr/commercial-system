
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.models.Client;
import java.util.List;

/**
 *
 * @author Carlos Hoyos Rojas
 */
public interface IClientDAO {
    public List<Client> listAll();
    public String insert(Client emp);
    public String update(Client emp);
    public String delete(Client emp);
    public Client findById(long idEmployee);
    
}
