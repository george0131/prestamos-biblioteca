package com.serfinanzas.prestamos.web;

import com.serfinanzas.prestamos.rest.domain.BookInput;
import com.serfinanzas.prestamos.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookCommandWebController extends CommandWebController {

    final BookService bookService;

    public BookCommandWebController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book/new")
    public String createBook(BookInput input, RedirectAttributes attributes) {

        bookService.createBook(input);

        attributes.addFlashAttribute("message", "Libro creado con éxito.");

        return "redirect:/loans";

    }
}
