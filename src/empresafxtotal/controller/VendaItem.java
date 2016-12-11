/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

/**
 *
 * @author Usuario-PC
 */
public class VendaItem {
    private int qtd;
    private double valorUnitario;
    private Produto produto;
    

    
    private int fkVenda;
    private int pkVendaItem;

  
   

    public VendaItem() {
    }

    public VendaItem(int fkVenda,Produto produto,int qtd,double valorUnitario ) {
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.produto = produto;
        this.pkVendaItem = pkVendaItem;
    }

    public VendaItem(int qtd, double valorUnitario, Produto produto) {
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.produto = produto;
    }

    public VendaItem(int qtd, double valorUnitario, Produto produto, int fkVenda, int pkVendaItem) {
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.produto = produto;
        this.fkVenda = fkVenda;
        this.pkVendaItem = pkVendaItem;
    }
    

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getFkVenda() {
        return fkVenda;
    }

    public void setFkVenda(int fkVenda) {
        this.fkVenda = fkVenda;
    }

    public int getPkVendaItem() {
        return pkVendaItem;
    }

    public void setPkVendaItem(int pkVendaItem) {
        this.pkVendaItem = pkVendaItem;
    }

    @Override
    public String toString() {
        return "VendaItem{" + "qtd=" + qtd + ", valorUnitario=" + valorUnitario + ", produto=" + produto + ", fkVenda=" + fkVenda + ", pkVendaItem=" + pkVendaItem + '}';
    }
    
    
}
