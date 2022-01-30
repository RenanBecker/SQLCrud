package com.example.sqlcrud;

public class PessoasModelo {

    private Integer id;
    private String nome;
    private Integer idade;
    private boolean ehVizinho;




    // construtores
    public PessoasModelo(Integer id, String nome, Integer idade, boolean ehVizinho) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.ehVizinho = ehVizinho;
    }

    public PessoasModelo() {
    }

    // toString


    @Override
    public String toString() {
        return "PessoasModelo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", ehVizinho=" + ehVizinho +
                '}';
    }

    // getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public boolean isEhVizinho() {
        return ehVizinho;
    }

    public void setEhVizinho(boolean ehVizinho) {
        this.ehVizinho = ehVizinho;
    }
}
