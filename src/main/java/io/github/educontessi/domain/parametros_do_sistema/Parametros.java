package io.github.educontessi.domain.parametros_do_sistema;


import io.github.educontessi.domain.helpers.util.LoadProperties;

public class Parametros {

    private Parametros(){
        throw new IllegalStateException("Utility class");
    }

    public static final boolean EXCLUIR_DEFINITIVO = excluirDefinifivo();

    public static boolean excluirDefinifivo(){
        return Boolean.parseBoolean(LoadProperties.getProperty("portifolio.excluir-definitivo"));
    }

}
