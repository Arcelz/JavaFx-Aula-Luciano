/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.CompraDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Usuario-PC
 */
public class Compra {
    private int numero;
    private Date data;
    
    private Fornecedor fornecedor;
    private ArrayList<CompraItem> itens;
    private int pkCompra;

    public Compra() {
    }
    
    public void addIten(CompraItem vi){
        if (itens==null){
            itens=new ArrayList<>();
        }
        itens.add(vi);
    }

    public Compra(Fornecedor fornecedor,int numero,Date data) {
        this.numero = numero;
        this.data = data;
        this.fornecedor = fornecedor;
    }

    public Compra(int numero, Date data, Fornecedor fornecedor, ArrayList<CompraItem> itens) {
        this.numero = numero;
        this.data = data;
        this.fornecedor=fornecedor;
        this.itens = itens;
    }

    public Compra(int numero, Date data, Fornecedor fornecedor, ArrayList<CompraItem> itens, int pkCompra) {
        this.numero = numero;
        this.data = data;
        this.fornecedor = fornecedor;
        this.itens = itens;
        this.pkCompra = pkCompra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
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

   

    public ArrayList<CompraItem> getItens() {
        return itens;
    }

    public void setItens(ArrayList<CompraItem> itens) {
        this.itens = itens;
    }

    public int getPkCompra() {
        return pkCompra;
    }

    public void setPkCompra(int pkCompra) {
        this.pkCompra = pkCompra;
        this.itens.forEach((a)->a.setFkCompra(pkCompra));
    }

    @Override
    public String toString() {
return "Vemda: "+numero;    }
    
    public void save() throws SQLException{
        CompraDAO.create(this);
    }
    public void update() throws SQLException{
        CompraDAO.update(this);
    }
    public void deletar() throws SQLException{
        CompraDAO.delete(this);
    }
    
    
    
}
