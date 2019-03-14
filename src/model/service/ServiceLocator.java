/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import model.dao.ClienteDAO;
import model.dao.ClienteDaoImpl;

/**
 *
 * @author lsouzafa
 */
public class ServiceLocator {

    public static ClienteDAO getClienteDAO() {
        return new ClienteDaoImpl();
    }
    
}
