package konto;

import javax.persistence.*;
import java.io.*;

@Entity
@NamedQuery(name = "finnAntallKontoer", query = "select count(k) from Konto k")
public class Konto implements Serializable {

    @Id
    private String kontonr;
    private double saldo;
    private String name;
    @Version
    private int lass;

    public Konto(){}
    public Konto(String kontonr, double saldo, String name){
        this.kontonr = kontonr;
        this.saldo = saldo;
        this.name = name;
    }

    public String getKontonr(){
        return kontonr;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getName() {
        return name;
    }

    public void setKontonr(String kontonr) {
        this.kontonr = kontonr;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void trekk(double belop){
        double nySaldo = this.saldo - belop;
        if(nySaldo >= 0){
            this.saldo = nySaldo;
        }
    }

    public void leggTil(double belop){
        this.saldo = this.saldo + belop;
    }

    public String toString(){
        return "Kontonr: " + kontonr + ". Saldo: " + saldo + ". Navn: " + name + ".";
    }
}
