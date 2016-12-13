/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.ProdutoDAO;
import java.sql.SQLException;


/**
 *
 * @author Matheus
 */
public class Produto {
    private int pk_produto;
    private String nome;
    private int estoqueMinino;
    private int qtdEstoque;

    public Produto() {
     
    }
    public Produto(String nome, int estoqueMinino, int qtdEstoque) {
        this.nome = nome;
        this.estoqueMinino = estoqueMinino;
        this.qtdEstoque = qtdEstoque;
    }

    public Produto(int pk_produto, String nome, int estoqueMinino, int qtdEstoque) {
        this.pk_produto = pk_produto;
        this.nome = nome;
        this.estoqueMinino = estoqueMinino;
        this.qtdEstoque = qtdEstoque;
    }

    
    
    public int getPk_produto() {
        return pk_produto;
    }

    public void setPk_produto(int pk_produto) {
        this.pk_produto= pk_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome=nome;
    }

    public int getEstoqueMinino() {
        return estoqueMinino;
    }

    public void setEstoqueMinino(int estoqueMinino) {
        this.estoqueMinino=estoqueMinino;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque=qtdEstoque;
    }

    @Override
    public String toString() {
return nome;    }

   
    
    public void save() throws SQLException{
        ProdutoDAO.create(this);
    }
    public void update() throws SQLException{
        ProdutoDAO.update(this);
    }
   
}
