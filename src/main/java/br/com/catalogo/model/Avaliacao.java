package br.com.catalogo.model;

import java.time.LocalDate;

public class Avaliacao {
    private int id;
    private int itemMidiaId;
    private int nota;
    private String comentario;
    private LocalDate dataAvaliacao;

    public Avaliacao() {}

    public Avaliacao(int itemMidiaId, int nota, String comentario, LocalDate dataAvaliacao) {
        this.itemMidiaId = itemMidiaId;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getItemMidiaId() { return itemMidiaId; }
    public void setItemMidiaId(int itemMidiaId) { this.itemMidiaId = itemMidiaId; }

    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDate getDataAvaliacao() { return dataAvaliacao; }
    public void setDataAvaliacao(LocalDate dataAvaliacao) { this.dataAvaliacao = dataAvaliacao; }
}