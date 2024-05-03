package br.com.dcco;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.modelcore.util.AgendamentoRelatorioHelper;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import br.com.sankhya.ws.ServiceContext;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BotaoAcaoGuma  implements AcaoRotinaJava {

    @Override
    public void doAction(ContextoAcao contexto) throws Exception {

        // Pegar o Relatorio: 708 - Teste
        BigDecimal nuRfe = new BigDecimal(708);

        List<Object> lstParam = new ArrayList<Object>();


        byte[] pdfBytes = null;
        String chave = "chave.pdf";

        try
        {
            EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
            // Lista dos Parametros


            // Criar um arquivo pdf do relatorio formatado 708 -- teste
            pdfBytes = AgendamentoRelatorioHelper.getPrintableReport(nuRfe, lstParam,BigDecimal.ZERO, dwfFacade);

            // Service Context
            ServiceContext ctx = new ServiceContext(null);
            ctx.makeCurrent();

            // Data Hora
            DateFormat df = new SimpleDateFormat("ddHHmmss");
            Date agora = Calendar.getInstance().getTime();
            Integer TransactionID = Integer.parseInt(df.format(agora));

            // Dados do usuario
            AuthenticationInfo auth = new AuthenticationInfo("SUP", BigDecimal.ZERO, BigDecimal.ONE, TransactionID);
            auth.makeCurrent();

            // Gerenciador de Impressora


        }
        catch(Exception e)
        {

        }



        //Gera a configuração da impressora

        contexto.setMensagemRetorno("Chegou aqui");

    }
}
