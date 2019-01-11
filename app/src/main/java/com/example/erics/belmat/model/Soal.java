package com.example.erics.belmat.model;

public class Soal {
    private int idSoal;
    private String kategori;
    private String soal;
    private String jawab;

    public Soal(String kategori, String soal, String jwbn){
        super();
        this.idSoal = idSoal;
        this.kategori = kategori;
        this.soal = soal;
        this.jawab = jwbn;
    }

    public int getIdSoal() {
        return idSoal;
    }

    public void setIdSoal(int idSoal) {
        this.idSoal = idSoal;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getJawab() {
        return jawab;
    }

    public void setJawab(String jawab) {
        this.jawab = jawab;
    }
}