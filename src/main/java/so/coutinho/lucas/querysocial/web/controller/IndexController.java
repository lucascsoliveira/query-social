package so.coutinho.lucas.querysocial.web.controller;

import javax.faces.bean.SessionScoped;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(ContextUrls.INDEX)
@SessionScoped
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadForm(ModelMap model) {

//		preProc(model);
        return "index";
    }

//	private void preProc(ModelMap model)
//	{
//		Usuario usuario = new Usuario();
//		
//		model.addAttribute("MensagemInicial", "Bem vindo JEDI!");
//		
//		model.addAttribute("usuario", usuario);
//	}
//
//	@RequestMapping(value = "/autenticarUsuario", method = RequestMethod.POST)
//	public String addUsuario(@ModelAttribute Usuario usuario,
//			ModelMap model) {
//		
//		if (usuario.getLogin().equals("LEDS") && usuario.getSenha().equals("1234"))
//		{
//			
//			usuario.setNome("JEDI");
//			model.addAttribute("usuario", usuario);
//			
//			return "result";	
//		}
//			return "redirect:/Mensagem/ErrorLogin";
//		
//	}
//	
//	@RequestMapping(value="/Mensagem/{mensagem}",method = RequestMethod.GET)
//	public String loadForm(ModelMap model,@PathVariable String mensagem) {
//
//		preProc(model);
//		if (mensagem.equals("ErrorLogin"))
//		{
//			model.addAttribute("mensagem", "Usuario Invalido");	
//		}
//		
//		return "index";
//	}
}
