/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.FuncionarioDAO;


public class Funcionario {
    private int pk_funcionario;
    private int fk_cargo; 
   
    
    private String nome;
    private String cpf;
    
    private Cargo cargo;
    
    private FuncionarioEndereco funcEndereco;

    
    public Funcionario() {
    
    }

    public Funcionario(String nome, String cpf, int fk_cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.fk_cargo=fk_cargo;
    } public Funcionario(int pk_funcionario, String nome, String cpf, int fk_cargo) {
        this.nome = nome;
        this.pk_funcionario = pk_funcionario;
        this.cpf = cpf;
        this.fk_cargo=fk_cargo;
    }

    public Funcionario(int pk_funcionario, int fk_cargo, String nome, String cpf, Cargo cargo, FuncionarioEndereco funcEndereco) {
        this.pk_funcionario = pk_funcionario;
        this.fk_cargo = fk_cargo;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.funcEndereco = funcEndereco;
    }

  

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

   
    public FuncionarioEndereco getFuncEndereco() {
        return funcEndereco;
    }

    public void setFuncEndereco(FuncionarioEndereco funcEndereco) {
        this.funcEndereco = funcEndereco;
        
    }

 
    
    

    public int getPk_funcionario() {
        return pk_funcionario;
    }

    public void setPk_funcionario(int pk_funcionario) {
        this.pk_funcionario = pk_funcionario;
        this.funcEndereco.setFk_funcionario(pk_funcionario);
    }

    public int getFk_cargo() {
        return fk_cargo;
    }

    public void setFk_cargo(int fk_cargo) {
        this.fk_cargo = fk_cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return nome;
    }
    public void save(){
        FuncionarioDAO.create(this);
    }
    public void update(){
        FuncionarioDAO.update(this);
    }
    
    
  
    
    
    
    
}
