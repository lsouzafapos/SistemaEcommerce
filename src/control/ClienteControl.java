/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import model.dao.ClienteDAO;
import model.dao.ClienteDaoImpl;
import model.domain.Cliente;
import model.service.ServiceLocator;
import org.jdesktop.observablecollections.ObservableCollections;
import util.ValidacaoException;

/**
 *
 * @author lsouzafa
 */
public final class ClienteControl {
    
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    private Cliente clienteDigitado;
    
    private Cliente clienteSelecionado;
    
    private List<Cliente> clientesTabela;
    
    private final ClienteDAO clienteDao;
    
    public Cliente getClienteDigitado() {
        return clienteDigitado;
    }

    public void setClienteDigitado(Cliente clienteDigitado) {
        Cliente oldClienteDigitado = this.clienteDigitado; 
        this.clienteDigitado = clienteDigitado;
        propertyChangeSupport.firePropertyChange("clienteDigitado", oldClienteDigitado, clienteDigitado);
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
        if(this.clienteSelecionado != null){
            setClienteDigitado(clienteSelecionado);
        }
    }

    public List<Cliente> getClientesTabela() {
        return clientesTabela;
    }

    public void setClientesTabela(List<Cliente> clientesTabela) {
        this.clientesTabela = clientesTabela;
    }
    
    public ClienteControl() {
        clienteDao = ServiceLocator.getClienteDAO();
        clientesTabela = ObservableCollections.observableList(new ArrayList<Cliente>());
        novo();
        pesquisar();
    }

    public void novo() {
       setClienteDigitado(new Cliente());
    }

    public void pesquisar() {
        clientesTabela.clear();
        clientesTabela.addAll(clienteDao.pesquisar(clienteDigitado));
    }
    
    public void salvar() throws ValidacaoException {
        clienteDigitado.validar();
        clienteDao.salvarAtualizar(clienteDigitado);
        novo();
        pesquisar();
    }
    
    public void excluir(){
        clienteDao.excluir(clienteDigitado);
        novo();
        pesquisar();
    }
    
    public void addPropertyChangeListener(PropertyChangeSupport propertyChangeSupport){
        propertyChangeSupport.addPropertyChangeListener(null);
    }
    
    public void removePropertyChangeListener(PropertyChangeSupport propertyChangeSupport){
        propertyChangeSupport.removePropertyChangeListener(null);
    }
}
