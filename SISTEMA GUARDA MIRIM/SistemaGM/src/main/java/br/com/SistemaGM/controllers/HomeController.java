package br.com.SistemaGM.controllers;

import br.com.SistemaGM.model.Monitor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // Identificando classe como Controller
public class HomeController {

    /*@GetMapping("/index") // Mapear uma requisição HTTP GET para a raiz do contexto do aplicativo ("/")
    public ModelAndView index() {
        /*
        *   Método que será executado quando a requisição GET for feita para a raiz.
        *   Ele retorna um objeto do tipo ModelAndView.
        *   O ModelAndView é uma classe do Spring que permite adicionar dados ao modelo e
        *   especificar a visualização que será renderizada.

        ModelAndView mv = new ModelAndView();
        mv.setViewName("telahome/TelaHome");
        return mv;
    }*/
}
