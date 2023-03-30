package com.example.lanchonete.model;

import java.io.Serializable;

public class lanche implements Serializable {
    public String nome;
    public double preco;
    public String ingre;
    public Double teor;
    public String tipo;

    public lanche(){

    }

public lanche(String nome, double preco, String ingre, Double teor, String tipo){
      this.nome = nome;
      this.preco = preco;
      this.ingre = ingre;
      this.teor = teor;
      this.tipo = tipo;
}

    public lanche(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getIngre() {
        return ingre;
    }

    public void setIngre(String ingre) {
        this.ingre = ingre;
    }

    public Double getTeor() {
        return teor;
    }

    public void setTeor(Double teor) {
        this.teor = teor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
