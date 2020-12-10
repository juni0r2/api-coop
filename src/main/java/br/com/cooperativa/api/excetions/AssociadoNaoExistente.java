package br.com.cooperativa.api.excetions;

public class AssociadoNaoExistente extends Throwable {
    public AssociadoNaoExistente(String associadoNaoCadastrado) {
        super(associadoNaoCadastrado);
    }
}
