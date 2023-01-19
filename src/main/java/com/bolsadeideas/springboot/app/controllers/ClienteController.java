package com.bolsadeideas.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.dao.service.IClienteService;
import com.bolsadeideas.springboot.app.models.dao.service.IUploadFileService;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadFileService;

	@Autowired
	private MessageSource messageSource;

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@SuppressWarnings("null")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(value="/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename){
		
		Resource recurso = null;
		
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+"\"" )
				.body(recurso);
		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_OTRO')")
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash, Locale locale) {
		
		Cliente cliente = clienteService.findByIdWithFactura(id);// clienteService.findOne(id);
		
		if(cliente == null) {
			flash.addFlashAttribute("error", messageSource.getMessage("text.cliente.flash.db.error", null, locale));
			return "redirect:/listar";
		}
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo",  messageSource.getMessage("text.cliente.detalle.titulo", null, locale).concat(" ").concat(cliente.getNombre()) );
		
		return "ver";
	}

	@GetMapping("/listar-rest")
	@ResponseBody
	public List<Cliente> listarRest() {
		return clienteService.findAll();
	}

	@GetMapping({"/listar", "/"})
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model,
	HttpServletRequest request,
	Locale locale) {

		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<Cliente>("/listar", clientes);
		
		model.addAttribute("titulo", messageSource.getMessage("text.cliente.listar.titulo", null, locale));
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/form")
	public String crear(Map<String, Object> model, Locale locale) {
		Cliente cliente = new Cliente();
		model.put("titulo",  messageSource.getMessage("text.cliente.form.titulo", null, locale));
		model.put("cliente", cliente);
		return "form";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash, Locale locale) {
		Cliente cliente = null;

		if (id > 0) {
			cliente = clienteService.findOne(id);
			if(cliente == null) {
				flash.addFlashAttribute("error",  messageSource.getMessage("text.cliente.flash.db.error", null, locale));
				return "redirect:/listar";
			}
			
		} else {
			flash.addFlashAttribute("error",  messageSource.getMessage("text.cliente.flash.id.error", null, locale));
			return "redirect:/listar";
		}

		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", messageSource.getMessage("text.cliente.form.titulo.editar", null, locale));

		return "form";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, 
		@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status,
		Locale locale) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", messageSource.getMessage("text.cliente.form.titulo", null, locale));
			return "form";
		}
		
		if(!foto.isEmpty()) {
			
			if(cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null)
				this.uploadFileService.delete(cliente.getFoto());
			
			
			String uniqueFileName = null;
			
			try {
				uniqueFileName = uploadFileService.copy(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			flash.addFlashAttribute("info", messageSource.getMessage("text.cliente.flash.foto.subir.success", null, locale));
			
			cliente.setFoto(uniqueFileName);		
		}
		
		String mensajeFlash = (cliente.getId() != null) ? messageSource.getMessage("text.cliente.flash.editar.success", null, locale):messageSource.getMessage("text.cliente.flash.crear.success", null, locale);

		clienteService.save(cliente);
		status.setComplete();
		
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash, Locale locale) {
		
		if (id > 0) {
			Cliente cliente = clienteService.findOne(id);

			clienteService.delete(id);
			flash.addFlashAttribute("success", messageSource.getMessage("text.cliente.flash.eliminar.success", null, locale));

			if (uploadFileService.delete(cliente.getFoto())) {
				String mensajeFotoEliminar = String.format(messageSource.getMessage("text.cliente.flash.foto.eliminar.success", null, locale), cliente.getFoto());
				flash.addFlashAttribute("info", mensajeFotoEliminar);
			}

		}
		
		return "redirect:/listar";
	}
	
	/*private boolean hasRole(String role){

		SecurityContext context = SecurityContextHolder.getContext();
		if(context == null)	return false;

		Authentication auth = context.getAuthentication();
		if(auth == null) return false;

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		return authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}*/

}
