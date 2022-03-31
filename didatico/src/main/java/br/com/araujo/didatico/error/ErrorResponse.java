package br.com.araujo.didatico.error;

import java.io.Serializable;
import java.util.List;

public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = -414819451713265522L;

    private String mensagem;
    private List<String> detalhe;

    public List<String> getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(List<String> detalhe) {
        this.detalhe = detalhe;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ErrorResponse(String mensagem) {
        this.mensagem = mensagem;
    }

    public ErrorResponse(String mensagem, List<String> detalhe) {
        super();
        this.mensagem = mensagem;
        this.detalhe = detalhe;
    }

}
