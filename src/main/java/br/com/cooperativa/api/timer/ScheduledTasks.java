package br.com.cooperativa.api.timer;

import br.com.cooperativa.api.model.Associado;
import br.com.cooperativa.api.model.EnumSituacaoPauta;
import br.com.cooperativa.api.model.Pauta;
import br.com.cooperativa.api.model.dto.GeradorCpfDTO;
import br.com.cooperativa.api.model.dto.ValidaCpfDTO;
import br.com.cooperativa.api.model.form.VotoForm;
import br.com.cooperativa.api.repository.AssociadoRepository;
import br.com.cooperativa.api.repository.PautaRepository;
import br.com.cooperativa.api.service.CpfService;
import br.com.cooperativa.api.service.PautaService;
import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    CpfService cpfService;

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    PautaService pautaService;

    @Autowired
    AssociadoRepository associadoRepository;

    @Scheduled(fixedRate = 5000)
    public void aberturaDePauta() {

        Iterable<Associado> all = this.associadoRepository.findAll();

        if (quantidadeAssociados(all) < 50) {
            getGerarAssociado();
            gerarVotoAutomaticamente(all);
        } else {
            gerarVotoAutomaticamente(all);
        }
    }

    private void gerarVotoAutomaticamente(Iterable<Associado> all) {

        Optional<Pauta> optionalPauta = this.pautaRepository.findBySituacao(EnumSituacaoPauta.ABERTA);

        if (optionalPauta.isPresent()) {
            Pauta pauta = optionalPauta.get();

            if (!metodoParaAddUmMinuto(pauta)) {
                pauta.setSituacao(EnumSituacaoPauta.FECHADA);
                this.pautaRepository.save(pauta);
            } else {
                List<Associado> associados = listaAssiciados(all);
                for (Associado associado : associados) {
                    associado = this.associadoRepository.save(associado);
                    String resultado = GerarResultadoSimOuNaoAleatorio();
                    this.pautaService.confirmarVoto(new VotoForm(associado.getCpf(), resultado, pauta.getId()));
                }
            }
        }
    }

    private Associado getGerarAssociado() {
        GeradorCpfDTO geradorCpfDTO = cpfService.gerarCpf();
        Associado associado = new Associado();
        associado.setCpf(geradorCpfDTO.getData().getNumber());
        return associado;
    }

    private String GerarResultadoSimOuNaoAleatorio() {
        Random random = new Random();
        boolean b = random.nextBoolean();
        String resultado = "Não";
        if (b)
            resultado = "Sim";
        return resultado;
    }

    private Boolean metodoParaAddUmMinuto(Pauta pauta) {
        Calendar tempo = Calendar.getInstance();
        tempo.setTime(pauta.getDataAbertura());
        tempo.add(Calendar.MINUTE, 1);
        Date tempoAdd = tempo.getTime();

        if (new Date().before(tempoAdd))
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    private int quantidadeAssociados(Iterable<Associado> all) {
        return IterableUtils.size(all);
    }

    private List<Associado> listaAssiciados(Iterable<Associado> all) {
        return StreamSupport.stream(all.spliterator(), false)
                        .collect(Collectors.toList());
    }
}
