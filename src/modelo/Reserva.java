package modelo;

import java.sql.Date;

public class Reserva {

    Integer id;
    Date fechaEntrada;
    Date fechaSalida;
    Float valor;
    String formaDePago;

    
    public Reserva(Date fechaEntrada, Date fechaSalida, Float valor, String string){
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaDePago = string;
    }
    
    public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, Float valor, String formaDePago){
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaDePago = formaDePago;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return this.fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Float getValor() {
        return this.valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getFormaDePago() {
        return this.formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

}

