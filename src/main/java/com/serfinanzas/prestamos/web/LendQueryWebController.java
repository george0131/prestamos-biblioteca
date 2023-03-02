package com.serfinanzas.prestamos.web;

import com.serfinanzas.prestamos.persistence.domain.Book;
import com.serfinanzas.prestamos.persistence.domain.Lend;
import com.serfinanzas.prestamos.rest.query.Query;
import com.serfinanzas.prestamos.service.BookService;
import com.serfinanzas.prestamos.service.LendService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class LendQueryWebController {

    private final LendService service;
    private final BookService bookService;

    public LendQueryWebController(LendService service, BookService bookService) {
        this.service = service;
        this.bookService = bookService;
    }

    @GetMapping({"/", "/loans"})
    public String getLendView(Model model, Query query) {

        Page<Lend> loans = service.getAll(query.getPageRequest());

        model.addAttribute("loanPage", loans);
        model.addAttribute("loans", loans.getContent());
        int totalPages = loans.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }

            return "views/lend/all";
    }

    @GetMapping(value = "/lend/new")
    public String getCreationLendView(Model model) {

        List<Book> books = bookService.findAll();

        model.addAttribute("books", books);

        return "views/lend/new";

    }

    @GetMapping(value = "/book/new")
    public String getCreationBookView(Model model) {

        return "views/book/new";

    }
}
