package br.com.cooperativa.api.service;

import br.com.cooperativa.api.consumer.GeradorCpfUseCase;
import br.com.cooperativa.api.consumer.ValidaCpfUseCase;
import br.com.cooperativa.api.excetions.AssociadoNaoExistente;
import br.com.cooperativa.api.excetions.AssociadoNaoHabilitadoParaVotarException;
import br.com.cooperativa.api.model.Associado;
import br.com.cooperativa.api.model.Pauta;
import br.com.cooperativa.api.model.Voto;
import br.com.cooperativa.api.model.dto.ResultadoVotacaoDTO;
import br.com.cooperativa.api.model.dto.ValidaCpfDTO;
import br.com.cooperativa.api.model.dto.VotoDTO;
import br.com.cooperativa.api.model.form.VotoForm;
import br.com.cooperativa.api.repository.AssociadoRepository;
import br.com.cooperativa.api.repository.PautaRepository;
import br.com.cooperativa.api.repository.VotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PautaService {

    private static final Logger log = LoggerFactory.getLogger(PautaService.class);

    @Autowired
    ValidaCpfUseCase validaCpfUseCase;

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    AssociadoRepository associadoRepository;

    public VotoDTO confirmarVoto(VotoForm form) {

        try {
            Voto voto = null;
            Optional<Pauta> optionalPauta = this.pautaRepository.findById(form.getIdPauta());

            if (optionalPauta.isPresent()) {
                Pauta pauta = optionalPauta.get();
                ValidaCpfDTO executa = this.validaCpfUseCase.executa(form.getCpf());
                if (executa.getStatus().equals("ABLE_TO_VOTE")) {
                    Optional<Associado> optionalAssociado = associadoRepository.findByCpf(form.getCpf());
                    if (optionalAssociado.isPresent()) {
                        Associado associado = optionalAssociado.get();
                        voto = form.converte(pauta, associado);
                        voto = this.votoRepository.save(voto);
                        Optional<Voto> optionalVoto = this.votoRepository.findById(voto.getId());
                        voto = optionalVoto.get();
                    } else {
                        throw new AssociadoNaoExistente("Associado não cadastrado");
                    }
                } else {
                    throw new AssociadoNaoHabilitadoParaVotarException("Associado não habilitado.");
                }
            }
            return new VotoDTO(voto);
        } catch (Exception | AssociadoNaoHabilitadoParaVotarException | AssociadoNaoExistente e ){
            log.error("Erro durante o voto. Mensagem: "+e.getMessage());
            return null;
        }
    }

    public ResultadoVotacaoDTO verificaSituacaoVotacao(Long id) {
        Optional<Pauta> pautaOptional = this.pautaRepository.findById(id);

        try {
            ResultadoVotacaoDTO resultadoVotacaoDTO = null;
            if (pautaOptional.isPresent()){
                Pauta pauta = pautaOptional.get();
                int sim = pauta.getVotos().stream().filter(voto -> voto.getVoto().equals("Sim")).collect(Collectors.toList()).size();
                int nao = pauta.getVotos().stream().filter(voto -> voto.getVoto().equals("Não")).collect(Collectors.toList()).size();
                int total = sim + nao;
                resultadoVotacaoDTO = new ResultadoVotacaoDTO(sim, nao, total);
            }
            return resultadoVotacaoDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
