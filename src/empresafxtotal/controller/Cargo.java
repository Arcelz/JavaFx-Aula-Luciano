/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.CargoDAO;


public class Cargo {
   private int pk_cargo;
   private String nome;
   private String descricao;

    public Cargo() {
    }
   
  

    public Cargo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        
    }

    public Cargo(int pk_cargo, String nome, String descricao) {
        this.pk_cargo = pk_cargo;
        this.nome = nome;
        this.descricao = descricao;
       
    }
   
   

    public int getPk_cargo() {
        return pk_cargo;
    }

    public void setPk_cargo(int pk_cargo) {
        this.pk_cargo = pk_cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return nome;
    }
    public void save(){
        CargoDAO.create(this);
    }
    public void update(){
        CargoDAO.update(this);
    }

  

 
   
   
   
    
    
}
