package br.com.cooperativa.api.timer;

import br.com.cooperativa.api.model.EnumSituacaoPauta;
import br.com.cooperativa.api.repository.PautaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    PautaRepository pautaRepository;

    public void aberturaDePauta() {
            this.pautaRepository.findBySituacao(EnumSituacaoPauta.ABERTA);
    }
}
