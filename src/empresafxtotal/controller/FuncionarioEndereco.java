/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.FuncionarioEnderecoDAO;


public class FuncionarioEndereco {
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    
    private int pk_endereco;
    private int fk_funcionario;
    public FuncionarioEndereco() {
   
    }
    public FuncionarioEndereco(String logradouro, String bairro, String cidade, String estado, String pais, String cep) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
    }

    public FuncionarioEndereco(String logradouro, String bairro, String cidade, String estado, String pais, String cep, int pk_endereco, int fk_funcionario) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
        this.pk_endereco = pk_endereco;
        this.fk_funcionario = fk_funcionario;
    }

    
    
    
    
    
    
    
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getPk_endereco() {
        return pk_endereco;
    }

    public void setPk_endereco(int pk_endereco) {
        this.pk_endereco = pk_endereco;
    }

    public int getFk_funcionario() {
        return fk_funcionario;
    }

    public void setFk_funcionario(int fk_funcionario) {
        this.fk_funcionario = fk_funcionario;
    }

    @Override
    public String toString() {
        return "FuncionarioEndereco{" + "logradouro=" + logradouro + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", pais=" + pais + ", cep=" + cep + ", pk_endereco=" + pk_endereco + ", fk_funcionario=" + fk_funcionario + '}';
    }
    
    
    
    
    
}
