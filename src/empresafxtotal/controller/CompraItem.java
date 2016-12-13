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
public class CompraItem {
    private int qtd;
    private double valorUnitario;
    private Produto produto;
    

    
    private int fkCompra;
    private int pkCompraItem;

  
   

    public CompraItem() {
    }

    public CompraItem(int fkCompra,Produto produto,int qtd,double valorUnitario ) {
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.produto = produto;
        this.pkCompraItem = pkCompraItem;
    }

    public CompraItem(int qtd, double valorUnitario, Produto produto) {
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.produto = produto;
    }

    public CompraItem(int fkCompra,Produto produto,int qtd,double valorUnitario, int pkCompraItem) {
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.produto = produto;
        this.fkCompra = fkCompra;
        this.pkCompraItem = pkCompraItem;
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

    public int getFkCompra() {
        return fkCompra;
    }

    public void setFkCompra(int fkCompra) {
        this.fkCompra = fkCompra;
    }

    public int getPkCompraItem() {
        return pkCompraItem;
    }

    public void setPkCompraItem(int pkCompraItem) {
        this.pkCompraItem = pkCompraItem;
    }

  

    @Override
    public String toString() {
        return "VendaItem{" + "qtd=" + qtd + ", valorUnitario=" + valorUnitario + ", produto=" + produto + ", fkCompra=" + fkCompra + ", pkCompraItem=" + pkCompraItem + '}';
    }
    
    
}
