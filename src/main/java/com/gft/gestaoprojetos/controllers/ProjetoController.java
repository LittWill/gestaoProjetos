package com.gft.gestaoprojetos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gft.gestaoprojetos.entities.Projeto;
import com.gft.gestaoprojetos.services.DesenvolvedorService;
import com.gft.gestaoprojetos.services.LinguagemService;
import com.gft.gestaoprojetos.services.ProjetoService;


@Controller
@RequestMapping("projetos")
public class ProjetoController {
	
	@Autowired
	private ProjetoService projetoService;
	
	@Autowired
	private DesenvolvedorService desenvolvedorService;
	
	@Autowired
	private LinguagemService linguagemService;
	
	@RequestMapping(path = "editar")
	public ModelAndView editarProjeto(@RequestParam(required = false) Long id) {
		ModelAndView mv = new ModelAndView("projeto/form.html");
		
		Projeto projeto;
		
		if (id == null) {
			projeto = new Projeto();
		}
		else {
			try {
				projeto = projetoService.obterProjeto(id);
			} catch (Exception e) {
				projeto = new Projeto();
				mv.addObject("mensagem", "Projeto não encontrado!");
			}
		}
		
		mv.addObject("projeto", projeto);
		mv.addObject("linguagens", linguagemService.listarLinguagens());
		mv.addObject("desenvolvedores", desenvolvedorService.listarTodosDesenvolvedores());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarProjeto(@Valid Projeto projeto, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("projeto/form.html");
		
		boolean isProjetoNovo = (projeto.getId() == null);
		String mensagem = null;
		
		if (isProjetoNovo) {
			mv.addObject("projeto", new Projeto());
		}
		if (bindingResult.hasErrors()) {
			mensagem = "Não foi possível salvar! Verifique os campos abaixo.";
			mv.addObject("projeto", projeto);
		}
		else {
			projetoService.salvarProjeto(projeto);
			mensagem = "Projeto salvo com sucesso!";
		}
		
		mv.addObject("mensagem", mensagem);
		mv.addObject("linguagens", linguagemService.listarLinguagens());
		mv.addObject("desenvolvedores", desenvolvedorService.listarTodosDesenvolvedores());
		
		return mv;
	}
	
	@RequestMapping(path = "delete")
	public ModelAndView deletarProjeto(@RequestParam Long id) {
		ModelAndView mv = new ModelAndView("projeto/listar.html");
		
		try{
			projetoService.excluirProjeto(id);
			mv.addObject("mensagem", "Projeto excluído com sucesso!");
		}
		catch(Exception e) {
			mv.addObject("mensagem", "Erro ao excluir projeto!");
		}
		
		return mv;
		
	}
	
	
	@RequestMapping
	public ModelAndView listarProjetos() {
		ModelAndView mv = new ModelAndView("projeto/listar.html");
		mv.addObject("projetos", projetoService.listarProjetos());
		mv.addObject("desenvolvedores", desenvolvedorService.listarTodosDesenvolvedores());
		return mv;
	}
	
	


}
