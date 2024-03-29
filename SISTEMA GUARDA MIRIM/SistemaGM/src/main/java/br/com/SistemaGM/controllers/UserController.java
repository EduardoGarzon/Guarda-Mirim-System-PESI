package br.com.SistemaGM.controllers;

import br.com.SistemaGM.Enums.Tipo;
import br.com.SistemaGM.dao.CoordenadorDao;
import br.com.SistemaGM.dao.MonitorDao;
import br.com.SistemaGM.model.Coordenador;
import br.com.SistemaGM.model.Monitor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
    int check =0;

    @Autowired
    private MonitorDao usuariorepositorio;
    @Autowired
    private CoordenadorDao usuariorepositorio2;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("telalogin/TelaLogin");
        return mv;
    }

    @PostMapping("/")
    public ModelAndView buscarUsuario(@RequestParam(name = "usernumber", required = false) String usernumber,
                                      @RequestParam(name = "keynumber", required = false) String keynumber) {
        ModelAndView mv = new ModelAndView();
        Monitor monitor = usuariorepositorio.findByCPF(usernumber);
        Coordenador coordenador = usuariorepositorio2.findByCPF(usernumber);
        if (monitor != null && Objects.equals(monitor.getSenha(), keynumber)) {
            mv.setViewName("telahome/TelaHome");
            mv.addObject("monitor", monitor);
        } else if (coordenador != null && Objects.equals(coordenador.getSenha(), keynumber)) {
            mv.setViewName("telahome/TelaHome");
            mv.addObject("coordenador", coordenador);
        } else {
            mv.setViewName("telalogin/TelaLogin");
            mv.addObject("error", "Usuário ou senha incorretos"); // Pode adicionar uma mensagem de erro caso deseje mostrar na página de login
        }

        return mv;
    }

    // telausuarios/TELAOPCOES
    // BOTAO USUARIOS EM HEADER FRAGMENTOS
    @GetMapping("telaOpcoesUser")
    public ModelAndView telaOpcoesUsuarios() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("telausuarios/telaOpcoes");
        mv.addObject("monitor", new Monitor());
        return mv;
    }

    // telausuarios/TELAOPCOES LEVA PARA telausuarios/FORMCADASTRO
    // BOTAO CADASTRAR EM telausuarios/TELAOPCOES
    // CHAMA TELA PARA SELECIONAR O TIPO DE USUARIO A SER CADASTRADO
    @GetMapping("/selecionarTipoUsuario")
    public ModelAndView selecionarTipoUsuario() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("telausuarios/formCadastro");
        return mv;
    }


    // telausuarios/FORMCADASTRO LEVA PARA telausuarios/INSERTMONITOR OU telausuarios/INSERTCOORDENADOR
    // REDIRECIONA PARA PAGINA DE CADASTRO DE MONITOR OU COORDENADOR
    @PostMapping("/selecionarTipoUsuario")
    public ModelAndView processarSelecaoTipoUsuario(@RequestParam("tipoUsuario") String tipoUsuario) {
        ModelAndView mv = new ModelAndView();
        if (tipoUsuario.equals("MONITOR")) {
            mv.addObject("monitor", new Monitor()); // Envia obj para view
            mv.setViewName("telausuarios/insertMonitor");     // Redireciona para a página de cadastro do Monitor
        } else if (tipoUsuario.equals("COORDENADOR")) {
            mv.addObject("coordenador", new Coordenador());
            mv.setViewName("telausuarios/insertCoordenador"); // Redireciona para a página de cadastro do Coordenador
        } else {
            // Opção inválida, você pode tratar esse caso aqui
            mv.setViewName("telausuarios/formCadastro");
        }
        return mv;
    }


    // telausuarios/INSERTMONITOR LEVA PARA telausuarios/USUARIOSCADASTRADOS
    // BOTAO SALVAR EM telausuarios/INSERTMONITOR
    @PostMapping("insertUser")
    public ModelAndView inserirUsuario(@Valid Monitor monitor,BindingResult br) {
        ModelAndView mv = new ModelAndView();
            if (br.hasErrors()) {
                mv.setViewName("telausuarios/insertMonitor");
                mv.addObject("monitor");
            } else {
                mv.setViewName("redirect:/usuariosCadastrados");
                monitor.setTipo(Tipo.MONITOR);
                usuariorepositorio.save(monitor);
            }
        return mv;
    }

    // telausuarios/insertcoordenador LEVA PARA telausuarios/USUARIOSCADASTRADOS
    // BOTAO SALVAR EM telausuarios/insertcoordenador
    @PostMapping("insertUser2")
    public ModelAndView inserirUsuario2(@Valid Coordenador coordenador,BindingResult br) {
        ModelAndView mv = new ModelAndView();
            if (br.hasErrors()) {
                mv.setViewName("telausuarios/insertCoordenador");
                mv.addObject("coordenador");
            } else {
                mv.setViewName("redirect:/usuariosCadastrados");
                coordenador.setTipo(Tipo.COORDENADOR);
                usuariorepositorio2.save(coordenador);
            }
        return mv;
    }


    // telausuarios/TABELAUSER
    // TELA DOS USUARIOS CADASTRADOS NO SISTEMA
    // CHAMADO APOS O CADASTRO DE UM USUARIO MONITOR OU COORDENADOR
    @GetMapping("usuariosCadastrados")
    public ModelAndView listagemUsuarios() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("telausuarios/tabelaUser");
        mv.addObject("Users", usuariorepositorio.findAll());
        mv.addObject("Coordenadores", usuariorepositorio2.findAll());
        return mv;
    }

    // TELACADASTRO/TELAOPCOES PARA telausuarios/TABELAUSER
    // BOTAO EXIBIR EM telausuarios/TELAOPCOES
    @PostMapping("/usuariosCadastrados")
    public ModelAndView usuariosCadastrados() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("telausuarios/tabelaUser");
        return mv;
    }


    // telausuarios/TABELAUSER LEVA PARA telausuarios/ALTERARUSER
    // CHAMADO EM EDITAR EM telausuarios/TABELAUSER
    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("telausuarios/alterarUser");
        Monitor monitor = usuariorepositorio.getOne(id);
        mv.addObject("monitor", monitor);
        return mv;
    }

    // telausuarios/ALTERARUSER LEVA PARA telausuarios/ALTERARUSER OU telausuarios/TABELAUSER
    // CHAMADO EM FORM telausuarios/ALTERARUSER
    @PostMapping("/alterar")
    public ModelAndView alterar(@Valid Monitor monitor, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        if (br.hasErrors()) {
            mv.setViewName("telausuarios/alterarUser");
            mv.addObject("monitor");
        } else {
            usuariorepositorio.save(monitor);
            mv.setViewName("redirect:/usuariosCadastrados");
        }
        return mv;
    }

    // telausuarios/TABELAUSER LEVA PARA telausuarios/alterarcoordenador
    // CHAMADO EM EDITAR EM telausuarios/TABELAUSER
    @GetMapping("/alterar2/{id}")
    public ModelAndView alterar2(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("telausuarios/alterarCoordenador");
        Coordenador coordenador = usuariorepositorio2.getOne(id);
        mv.addObject("coordenador", coordenador);
        return mv;
    }

    // telausuarios/alterarcoordenador LEVA PARA telausuarios/alterarcoordenador OU telausuarios/TABELAUSER
    // CHAMADO EM FORM telausuarios/alterarcoordenador
    @PostMapping("/alterar2")
    public ModelAndView alterar2(@Valid Coordenador coordenador, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        if (br.hasErrors()) {
            mv.setViewName("telausuarios/alterarCoordenador");
            mv.addObject("coordenador");
        } else {
            usuariorepositorio2.save(coordenador);
            mv.setViewName("redirect:/usuariosCadastrados");
        }
        return mv;
    }

    // telausuarios/TABELAUSER
    // DELETA USUARIO EM telausuarios/TABELAUSER DELETAR
    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable("id") Integer id) {
        usuariorepositorio.deleteById(id);
        return "redirect:/usuariosCadastrados";
    }

    // telausuarios/TABELAUSER
    // DELETA USUARIO EM telausuarios/TABELAUSER DELETAR
    @GetMapping("/excluir2/{id}")
    public String excluirUsuario2(@PathVariable("id") Integer id) {
        usuariorepositorio2.deleteById(id);
        return "redirect:/usuariosCadastrados";
    }

    // PESQUISAR USUARIO PELO NOME
    @PostMapping("pesquisarUsuario")
    public ModelAndView pesquisarUsuarios(@RequestParam(required = false) String nome) {
        ModelAndView mv = new ModelAndView();
        List<Monitor> listaMonitores;
        if (nome == null || nome.trim().isEmpty()) {
            listaMonitores = usuariorepositorio.findAll();
        } else {
            listaMonitores = usuariorepositorio.findByNomeContainingIgnoreCase(nome);
        }
        mv.addObject("ListaDeMonitores", listaMonitores);
        mv.setViewName("telausuarios/pesquisaResultado");
        return mv;
    }

}
