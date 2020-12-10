package br.com.cooperativa.api.excetions;

public class AssociadoNaoHabilitadoParaVotarException extends Throwable {
    public AssociadoNaoHabilitadoParaVotarException(String associadoNaoHabilitado) {
        super(associadoNaoHabilitado);
    }
}
