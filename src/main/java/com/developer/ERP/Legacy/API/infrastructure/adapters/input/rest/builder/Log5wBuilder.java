package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.builder;

import com.developer.ERP.Legacy.API.infrastructure.adapters.config.JsonConfiguration;
import org.springframework.util.Assert;
import org.slf4j.Logger;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;

public class Log5wBuilder {

    public static class ProntoParaLogar  {
        private Map<String, String> params = new LinkedHashMap<>();

        public ProntoParaLogar (String method, String whatIsHappening, Map<String, String> info) {

            params.put("metodo:", method);
            params.put("oqueEstaAcontecendo:", whatIsHappening);
            params.putAll(info);

        }

        public ProntoParaLogar adicionaInformacao(String chave, Object informacao) {
            Assert.hasText(chave, "A chave n pode ser vazia");
            Assert.notNull(informacao, "A info para logar n pode ser nula");
            Assert.isNull(params.get(chave), "A chave ja foi adicionada");
            params.put(chave, informacao.toString());
            return this;
        }

        public String debug(@NotNull Logger logger) {
            String json = JsonConfiguration.json(this.params);
            logger.debug(json);
            return json;
        }

        public String info(@NotNull Logger logger) {
            String json = JsonConfiguration.json(this.params);
            logger.info(json);
            return json;
        }

        public String error(@NotNull Logger logger) {
            String json = JsonConfiguration.json(this.params);
            logger.error(json);
            return json;
        }

        public String mensagem() {
            return JsonConfiguration.json(this.params);
        }
    }

    public static class OQueEstaAcontecendo {
        private String method;
        private String oQueEstaAcontecendo;
        private Map<String, String> infosAdicionais = new LinkedHashMap<>();

        public OQueEstaAcontecendo(String method, String oQueEstaAcontecendo) {
            this.method = method;
            this.oQueEstaAcontecendo = oQueEstaAcontecendo;
        }

        public ProntoParaLogar adicionaInfo(String chave, Object info) {
            Assert.hasText(chave, "A chave não pode ser vazia");
            Assert.notNull(info,"A informacao para logar nao pode ser nula");
            Assert.isNull(infosAdicionais.get(chave), "A chave já foi adicionada");

            infosAdicionais.put(chave, info.toString());
            return new ProntoParaLogar(method, oQueEstaAcontecendo, infosAdicionais);
        }
    }

    public static class Metodo {
        private String metodo;

        public Metodo(String metodo) {
            Assert.hasText(metodo, "O método não pode ser vazio");
            this.metodo = metodo;
        }

        public OQueEstaAcontecendo oQueEstaAcontecendo(String oQueEstaAcontecendo) {
            Assert.hasText(oQueEstaAcontecendo, "O momento não pode ser vazio");
            return new OQueEstaAcontecendo(metodo, oQueEstaAcontecendo);
        }

        public static Metodo metodo(String metodo) {
            return new Metodo(metodo);
        }

        public static Metodo metodo() {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            return new Metodo(stackTrace[2].getMethodName());
        }
    }
}
