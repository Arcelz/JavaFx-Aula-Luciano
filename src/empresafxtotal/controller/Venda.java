/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.VendaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Usuario-PC
 */
public class Venda {
    private int numero;
    private Date data;
    
    private Cliente cliente;
    private Funcionario vendedor;
    private ArrayList<VendaItem> itens;
    private int pkVenda;

    public Venda() {
    }
    
    public void addIten(VendaItem vi){
        if (itens==null){
            itens=new ArrayList<>();
        }
        itens.add(vi);
    }

    public Venda(Cliente cliente,Funcionario funcionario,int numero,Date data) {
        this.numero = numero;
        this.data = data;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public Venda(int numero, Date data, Cliente cliente, Funcionario vendedor, ArrayList<VendaItem> itens) {
        this.numero = numero;
        this.data = data;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.itens = itens;
    }

    public Venda(int numero, Date data, Cliente cliente, Funcionario vendedor, ArrayList<VendaItem> itens, int pkVenda) {
        this.numero = numero;
        this.data = data;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.itens = itens;
        this.pkVenda = pkVenda;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Funcionario vendedor) {
        this.vendedor = vendedor;
    }

    public ArrayList<VendaItem> getItens() {
        return itens;
    }

    public void setItens(ArrayList<VendaItem> itens) {
        this.itens = itens;
    }

    public int getPkVenda() {
        return pkVenda;
    }

    public void setPkVenda(int pkVenda) {
        this.pkVenda = pkVenda;
        this.itens.forEach((a)->a.setFkVenda(pkVenda));
    }

    @Override
    public String toString() {
return "Vemda: "+numero;    }
    
    public void save() throws SQLException{
        VendaDAO.create(this);
    }
    public void update() throws SQLException{
        VendaDAO.update(this);
    }
    public void deletar() throws SQLException{
        VendaDAO.delete(this);
    }
    
    
    
}
