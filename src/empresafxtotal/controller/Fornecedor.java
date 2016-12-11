
package empresafxtotal.controller;

import empresafxtotal.model.FornecedorDAO;


public class Fornecedor {
    private int pk_fornecedor;
    private String nome;
    private String cpf;
    
    private FornecedorEndereco forneEnd;
  public Fornecedor() {
        
    }
    public Fornecedor(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
     public Fornecedor(int pk_fornecedor, String nome, String cpf) {
        this.pk_fornecedor = pk_fornecedor;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Fornecedor(int pk_fornecedor, String nome, String cpf, FornecedorEndereco forneEnd) {
        this.pk_fornecedor = pk_fornecedor;
        this.nome = nome;
        this.cpf = cpf;
        this.forneEnd = forneEnd;
    }

    
    public int getPk_fornecedor() {
        return pk_fornecedor;
    }

    public void setPk_fornecedor(int pk_fornecedor) {
        this.pk_fornecedor = pk_fornecedor;
        this.forneEnd.setFk_fornecedor(pk_fornecedor);
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

    public FornecedorEndereco getForneEnd() {
        return forneEnd;
    }

    public void setForneEnd(FornecedorEndereco forneEnd) {
        this.forneEnd = forneEnd;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    public void save(){
        FornecedorDAO.create(this);
    }
    
    public void update(){
        FornecedorDAO.update(this);
    }
    
    
    
}
